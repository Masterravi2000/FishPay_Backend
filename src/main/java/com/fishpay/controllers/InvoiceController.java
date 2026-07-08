package com.fishpay.controllers;

import com.fishpay.dto.InvoiceHistoryResponse;
import com.fishpay.dto.InvoiceStatusResponse;
import com.fishpay.service.InvoiceService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/history")
    public ResponseEntity<InvoiceHistoryResponse> getInvoiceHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(invoiceService.getInvoiceHistory(page,size));
    }
}
