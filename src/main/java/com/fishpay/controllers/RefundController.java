package com.fishpay.controllers;

import com.fishpay.dto.RefundRequest;
import com.fishpay.dto.RefundResponse;
import com.fishpay.service.RefundService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/refunds")
public class RefundController {
    private final RefundService refundService;

    public RefundController(RefundService refundService){
        this.refundService = refundService;
    }

    @PostMapping("/refund")
    public RefundResponse refund (@RequestBody RefundRequest refundRequest) throws RazorpayException {
        return refundService.createRefund(refundRequest);
    }

    @GetMapping("/refund/{refundId}")
    public RefundResponse refundStatus (@PathVariable String refundId) {
        return refundService.refundStatus(refundId);
    }
}
