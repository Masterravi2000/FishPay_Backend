package com.fishpay.dto;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceHistoryItemResponse {
    private String invoiceNumber;
    private String orderId;
    private LocalDateTime orderTime;
    private BigDecimal totalAmount;
    private String invoiceUrl;
    private boolean viewed;

    public InvoiceHistoryItemResponse () {
    }

    public InvoiceHistoryItemResponse (String invoiceNumber, String orderId, LocalDateTime orderTime, BigDecimal totalAmount, String invoiceUrl, boolean viewed) {
        this.invoiceNumber = invoiceNumber;
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
        this.invoiceUrl = invoiceUrl;
        this.viewed = viewed;
    }

    //Getter
    public String getInvoiceNumber () {
        return invoiceNumber;
    }
    //Setter
    public void setInvoiceNumber (String invoiceNumber){
        this.invoiceNumber = invoiceNumber;
    }

    //Getter
    public String getOrderId () {
        return orderId;
    }
    //Setter
    public void setOrderId (String orderId) {
        this.orderId = orderId;
    }

    //Getter
    public LocalDateTime getOrderTime () {
        return orderTime;
    }
    //Setter
    public void setOrderTime (LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    //Getter
    public BigDecimal getTotalAmount () {
        return totalAmount;
    }
    //Setter
    public void setTotalAmount (BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    //Getter
    public String getInvoiceUrl () {
        return invoiceUrl;
    }
    //Setter
    public void setInvoiceUrl (String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    //Getter
    public boolean isViewed () {
        return viewed;
    }
    //Setter
    public void setViewed (boolean viewed) {
        this.viewed = viewed;
    }
}
