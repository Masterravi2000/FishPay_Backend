package com.fishpay.service;

import com.fishpay.dto.CreateOrderRequest;
import com.fishpay.dto.CreateOrderResponse;
import com.fishpay.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RazorpayClient razorpayClient;

    public PaymentService(PaymentRepository paymentRepository, RazorpayClient razorpayClient){
        this.paymentRepository = paymentRepository;
        this.razorpayClient = razorpayClient;
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
}
