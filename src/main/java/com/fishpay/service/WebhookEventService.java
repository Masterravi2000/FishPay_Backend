package com.fishpay.service;

import com.fishpay.repository.WebhookEventRepository;
import org.springframework.stereotype.Service;

@Service
public class WebhookEventService {
    private final WebhookEventRepository webhookEventRepository;

    public WebhookEventService(WebhookEventRepository webhookEventRepository){
        this.webhookEventRepository = webhookEventRepository;
    }
}
