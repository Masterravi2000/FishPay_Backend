package com.fishpay.repository;

import com.fishpay.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long>{
    Invoice findByPaymentId(String paymentId);
}
