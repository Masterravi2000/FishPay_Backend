package com.fishpay.repository;

import com.fishpay.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository
        extends JpaRepository<Refund, Long>{
    Refund findByRefundId (String refundId);
}
