package com.fishpay.controllers;

import com.fishpay.dto.InvoiceStatusResponse;
import com.fishpay.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoice-status/{paymentId}")
    public InvoiceStatusResponse getInvoiceStatus(@PathVariable String paymentId) {
        return invoiceService.invoiceStatusResponse(paymentId);
    }
}
