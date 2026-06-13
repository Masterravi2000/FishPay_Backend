package com.fishpay.controllers;

import com.fishpay.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
}
