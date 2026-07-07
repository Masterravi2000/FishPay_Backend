package com.fishpay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishpay.dto.*;
import com.fishpay.entity.Payment;
import com.fishpay.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PaymentService {
    @Value("${spring.razorpay.key-secret}")
    private String keySecret;
    @Value("${spring.razorpay.webhook-secret}")
    private String webhookSecret;
    private final PaymentRepository paymentRepository;
    private final RazorpayClient razorpayClient;
    private final InvoiceService invoiceService;

    public PaymentService(PaymentRepository paymentRepository, RazorpayClient razorpayClient, InvoiceService invoiceService){
        this.paymentRepository = paymentRepository;
        this.razorpayClient = razorpayClient;
        this.invoiceService = invoiceService;
    }

    //createOrder method
    public CreateOrderResponse createOrder(CreateOrderRequest orderRequest) throws RazorpayException {
        //extract the values from the memory already saved via dto
        BigDecimal amount = orderRequest.getAmount();
        String currency = orderRequest.getCurrency();

        //now create a json object because razorpay expects json obj and put required fields and their values
        JSONObject orderRequestJson = new JSONObject();
        orderRequestJson.put("amount", amount.multiply(BigDecimal.valueOf(100)));
        orderRequestJson.put("currency", currency);

        //now call razorpay to create the order
        Order order = razorpayClient.orders.create(orderRequestJson);

        //create and return CreateOrderResponse
        return new CreateOrderResponse(
                order.get("id").toString(),
                amount,
                currency,
                order.get("status").toString()
        );
    }

    public Payment savePayment (SavePaymentRequest request) {
        //extract the value from memory already saved via SavePaymentRequest dto
        String paymentId = request.getPaymentId();
        String orderId = request.getOrderId();
        BigDecimal amount = request.getAmount();
        String currency = request.getCurrency();
        String status = request.getStatus();
        String paymentMethod = request.getPaymentMethod();
        Long userId = request.getUserId();

        //Now create a payment entity object and populate via setter
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setCurrency(currency);
        payment.setStatus(status);
        payment.setPaymentMethod(paymentMethod);
        payment.setUserId(userId);

        //now save the payment object
        paymentRepository.save(payment);
        return payment;
    }

    public GenerateInvoiceResponse verifySignature(VerifyPaymentRequest request) throws RazorpayException {
        //extract the value from the memory already saved via dto
        String paymentId = request.getRazorpayPaymentId();
        String orderId = request.getRazorpayOrderId();
        String signature = request.getRazorpaySignature();

        //now create a json obj and put these 3 required details into it because  razorpay expects a json obj
        JSONObject attribute = new JSONObject();
        attribute.put("razorpay_payment_id", paymentId);
        attribute.put("razorpay_order_id", orderId);
        attribute.put("razorpay_signature", signature);

        //now give this json object called attribute in razorpay-signature-verification function provided by razorpay via utils
        Utils.verifyPaymentSignature(attribute,keySecret);

        //Fetch payment details from Razorpay using paymentId
        com.razorpay.Payment razorpayPayment = razorpayClient.payments.fetch(paymentId);
        //Extract the actual payment method
        String paymentMethod = razorpayPayment.get("method").toString();

        //creating the object SavePaymentRequest dto for savePayment method required fields and data
        SavePaymentRequest savePaymentRequest = new SavePaymentRequest();
        //now populating the newly created empty savePaymentRequest object via setters
        savePaymentRequest.setPaymentId(paymentId);
        savePaymentRequest.setOrderId(orderId);
        savePaymentRequest.setAmount(request.getAmount());
        savePaymentRequest.setCurrency(request.getCurrency());
        savePaymentRequest.setStatus("SUCCESS");
        savePaymentRequest.setPaymentMethod(paymentMethod);
        savePaymentRequest.setUserId(request.getUserId());
        //now call the savePayment method for saving the payment
        savePayment(savePaymentRequest);

        //Create a GenerateInvoiceRequest object and populate it
        GenerateInvoiceRequest generateInvoiceRequest = new GenerateInvoiceRequest();
        generateInvoiceRequest.setPaymentId(paymentId);
        generateInvoiceRequest.setOrderId(orderId);
        generateInvoiceRequest.setUserId(request.getUserId());
        generateInvoiceRequest.setPaymentMethod(paymentMethod);
        generateInvoiceRequest.setPaymentStatus("SUCCESS");
        generateInvoiceRequest.setProducts(request.getProducts());
        generateInvoiceRequest.setDeliveryCharges(request.getDeliveryCharges());
        generateInvoiceRequest.setTotalAmount(request.getTotalAmount());

        //now call the generateInvoice() by passing generateInvoiceRequest object and save its output into the response
        GenerateInvoiceResponse response = invoiceService.generateInvoice(generateInvoiceRequest);

        return response;
    }

    public void handleWebhook(String signature, String payload) {
        try {
            System.out.println("Webhook received");
            //now verify the webhook by taking the verification function from utils same as verify signature
            Utils.verifyWebhookSignature(payload, signature, webhookSecret);
            System.out.println("Webhook signature verified successfully");

            //now create a JsonNode a tree like structure not JSON Obj nor Java Obj
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(payload);

            String event = root.get("event").asText();

            if (event.equals("payment.captured")) {
                //get the payment entity from webhook payload sent by razorpay
                JsonNode payment = root.get("payload").get("payment").get("entity");
                //now extract the required data
                String paymentId = payment.get("id").asText();
                String orderId = payment.get("order_id").asText();
                String status = payment.get("status").asText();
                String paymentMethod = payment.get("method").asText();

                //Create a variable for using it fetching the payment entry from the payment table
                Payment dbPayment = null;
                //now start the delay and retry loop for fetching the payment entry
                for (int i = 0;i < 5; i++) {
                    dbPayment = paymentRepository.findByOrderId(orderId);
                    //check is this payment entry is available or not
                    if (dbPayment != null) {
                        System.out.println("payment entry found successfully headed for reconciliation");
                        //then break this loop and go for  reconciliation
                        break;
                    }
                    System.out.println("payment entry not found at this point retry initiated");
                    //else delay the next retry of this loop
                    Thread.sleep(500);
                }

                if (dbPayment == null) {
                    return;
                }

                //now check if status matches or not if not then change
                if(!status.equals(dbPayment.getStatus())) {
                    dbPayment.setStatus(status);
                }

                //now check weather payment method is matching or not if not then set the payment method
                if(dbPayment.getPaymentMethod() == null) {
                    dbPayment.setPaymentMethod(paymentMethod);
                }

                //now save the changes into the db
                paymentRepository.save(dbPayment);
                System.out.println("Reconciliation completed");

            } else if (event.equals("payment.failed")) {
                //handle failed payment
            } else if (event.equals("order.paid")) {
                //handle order paid
            } else if (event.equals("refund.processed")) {
                // handle refund
            }

            System.out.println(event);
        } catch (RazorpayException | IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
