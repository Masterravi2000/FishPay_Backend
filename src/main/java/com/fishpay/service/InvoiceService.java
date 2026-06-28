package com.fishpay.service;

import com.fishpay.dto.GenerateInvoiceRequest;
import com.fishpay.dto.ProductDto;
import com.fishpay.entity.Invoice;
import com.fishpay.repository.InvoiceRepository;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemService invoiceItemService;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceItemService invoiceItemService){
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemService = invoiceItemService;
    }

    public Invoice generateInvoice (GenerateInvoiceRequest request) {
        //first Extract the data from memory already saved via dto via getter
        String paymentId = request.getPaymentId();
        String orderId = request.getOrderId();
        Long userId = request.getUserId();
        String paymentMethod = request.getPaymentMethod();
        String paymentStatus = request.getPaymentStatus();
        BigDecimal deliveryCharges = request.getDeliveryCharges();
        BigDecimal totalAmount = request.getTotalAmount();
        List<ProductDto> products = request.getProducts();

        //now generate invoiceNumber
        String invoiceNumber = "INV-" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                +"_"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        //now create the invoice object and populate it
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setPaymentId(paymentId);
        invoice.setOrderId(orderId);
        invoice.setUserId(userId);
        invoice.setPaymentMethod(paymentMethod);
        invoice.setPaymentStatus(paymentStatus);
        invoice.setDeliveryCharges(deliveryCharges);
        invoice.setTotalAmount(totalAmount);

        //Now save the invoice object into its corresponding table
        Invoice savedInvoice =  invoiceRepository.save(invoice);

        //now call the saveInvoiceItems function here for saving the product details into invoice_items Table
        invoiceItemService.saveInvoiceItems(savedInvoice.getId(),products);
        return savedInvoice;
    }
}
