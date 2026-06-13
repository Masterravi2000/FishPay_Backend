package com.fishpay.controllers;

import com.fishpay.service.RefundService;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefundController {
    private final RefundService refundService;

    public RefundController(RefundService refundService){
        this.refundService = refundService;
    }
}
