package com.fishpay.repository;

import com.fishpay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository
        extends JpaRepository<Payment, Long>{
    Payment findByOrderId(String orderId);
}
