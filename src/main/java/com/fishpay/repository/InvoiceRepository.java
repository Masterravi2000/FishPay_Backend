package com.fishpay.repository;

import com.fishpay.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long>{
    Invoice findByPaymentId(String paymentId);
    Page<Invoice> findAllByOrderByOrderTimeDesc(Pageable pageable);
    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i")
    BigDecimal getTotalAmount();
}
