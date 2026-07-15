package com.fishpay.repository;

import com.fishpay.entity.Refund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface RefundRepository
        extends JpaRepository<Refund, Long>{
    Refund findByRefundId (String refundId);
    @Query("""
    SELECT COALESCE(SUM(r.amount), 0)
    FROM Refund r
    WHERE r.status = com.fishpay.util.RefundStatus.PROCESSED""")
    BigDecimal getTotalAmount();
    @Query("""
    SELECT COUNT(r)
    FROM Refund r
    WHERE r.status IN (
        com.fishpay.util.RefundStatus.PENDING,
        com.fishpay.util.RefundStatus.PROCESSING
    )
    """)
    Long getTotalRefundsUnderProgress();
}
