package com.fishpay.service;

import com.fishpay.dto.RefundRequest;
import com.fishpay.dto.RefundResponse;
import com.fishpay.entity.Payment;
import com.fishpay.repository.PaymentRepository;
import com.fishpay.repository.RefundRepository;
import com.fishpay.util.RefundStatus;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RefundService {
    private final RefundRepository refundRepository;
    private final PaymentRepository paymentRepository;
    private final RazorpayClient razorpayClient;

    public RefundService(RefundRepository refundRepository, PaymentRepository paymentRepository, RazorpayClient razorpayClient){
        this.refundRepository = refundRepository;
        this.paymentRepository = paymentRepository;
        this.razorpayClient = razorpayClient;
    }

    public RefundResponse createRefund (RefundRequest request) throws RazorpayException {
        //first find that payment
        Payment payment = paymentRepository.findByPaymentId(request.getPaymentId());

        //now call razorpay refund api and for that we need a json object
        JSONObject refundRequest = new JSONObject();
        refundRequest.put("amount",request.getAmount().multiply(new BigDecimal("100")).intValue());
        //now call the razorpay refund api by passing the above json obj
        Refund razorpayRefund = razorpayClient.payments.refund(payment.getPaymentId(),refundRequest);
        System.out.println("razorpayRefund - " + razorpayRefund);
        System.out.println("razorpay amount data type -  "+ razorpayRefund.get("amount").getClass());
        //Now create the db refund obj and save all the required details
        com.fishpay.entity.Refund refund = new com.fishpay.entity.Refund();
        refund.setRefundId(razorpayRefund.get("id").toString());
        refund.setPaymentId(razorpayRefund.get("payment_id").toString());
        refund.setOrderId(payment.getOrderId());
        refund.setAmount(BigDecimal.valueOf((Integer) razorpayRefund.get("amount")).divide(new BigDecimal("100")));
        refund.setCurrency(razorpayRefund.get("currency").toString());
        refund.setStatus(
                RefundStatus.valueOf(razorpayRefund.get("status").toString().toUpperCase())
        );
        refund.setReason(request.getReason());
        //now save this above refund record into Refund entity in DB
        com.fishpay.entity.Refund savedRefund =  refundRepository.save(refund);

        //now set required data by the RefundResponse in order to return the response
        RefundResponse response = new RefundResponse();
        response.setRefundId(savedRefund.getRefundId());
        response.setPaymentId(savedRefund.getPaymentId());
        response.setOrderId(savedRefund.getOrderId());
        response.setAmount(savedRefund.getAmount());
        response.setCurrency(savedRefund.getCurrency());
        response.setStatus(savedRefund.getStatus());
        response.setReason(savedRefund.getReason());
        response.setCreatedAt(savedRefund.getCreatedAt());

        return response;
    }

    public RefundResponse refundStatus (String refundId) {
        //fetch the refund using refundId
        com.fishpay.entity.Refund refund = refundRepository.findByRefundId(refundId);

        //now create the RefundResponse obj
        RefundResponse response = new RefundResponse();

        //now fetch all the recent data along with status
        response.setRefundId(refund.getRefundId());
        response.setStatus(refund.getStatus());
        response.setPaymentId(refund.getPaymentId());
        response.setOrderId(refund.getOrderId());
        response.setAmount(refund.getAmount());
        response.setCurrency(refund.getCurrency());
        response.setReason(refund.getReason());
        response.setCreatedAt(refund.getCreatedAt());

        //now return the response
        return response;
    }
}
