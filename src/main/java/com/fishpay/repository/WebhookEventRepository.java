package com.fishpay.repository;

import com.fishpay.entity.WebhookEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookEventRepository
        extends JpaRepository<WebhookEvent, Long>{
}
