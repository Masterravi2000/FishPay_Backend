package com.fishpay.service;

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
import java.math.BigDecimal;

@Service
public class PaymentService {
    @Value("${spring.razorpay.key-secret}")
    private String keySecret;
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
}
