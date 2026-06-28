package com.fishpay.controllers;

import com.fishpay.dto.CreateOrderRequest;
import com.fishpay.dto.CreateOrderResponse;
import com.fishpay.dto.VerifyPaymentRequest;
import com.fishpay.service.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest orderRequest) throws RazorpayException {
        return paymentService.createOrder(orderRequest);
    }

    @PostMapping("/verify-signature")
    public boolean verifySignature(@RequestBody VerifyPaymentRequest request) throws RazorpayException {
        return paymentService.verifySignature(request);
    }
}
