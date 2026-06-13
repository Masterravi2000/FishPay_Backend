package com.fishpay.controllers;

import com.fishpay.service.WebhookEventService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookEventController {
    private final WebhookEventService webhookEventService;

    public WebhookEventController(WebhookEventService webhookEventService){
        this.webhookEventService = webhookEventService;
    }
}
