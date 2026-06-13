package com.fishpay.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {
    @GetMapping("/api/v1/health")
    public String health(){
        return "FishPay is running";
    }
}

