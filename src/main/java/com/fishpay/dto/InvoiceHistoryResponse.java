package com.fishpay.dto;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceHistoryResponse {
    private Long totalInvoice;
    private BigDecimal totalAmountSpent;
    private List<InvoiceHistoryItemResponse> invoices;

    public InvoiceHistoryResponse () {
    }

    public InvoiceHistoryResponse (Long totalInvoice, BigDecimal totalAmountSpent, List<InvoiceHistoryItemResponse> invoices) {
        this.totalInvoice = totalInvoice;
        this.totalAmountSpent = totalAmountSpent;
        this.invoices = invoices;
    }

    //Getter
    public Long getTotalInvoice () {
        return totalInvoice;
    }
    //Setter
    public void setTotalInvoice (Long totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    //Getter
    public BigDecimal getTotalAmountSpent () {
        return totalAmountSpent;
    }
    //Setter
    public void setTotalAmountSpent (BigDecimal totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    //Getter
    public List<InvoiceHistoryItemResponse> getInvoices () {
        return invoices;
    }
    //Setter
    public void setInvoices (List<InvoiceHistoryItemResponse> invoices) {
        this.invoices = invoices;
    }
}
