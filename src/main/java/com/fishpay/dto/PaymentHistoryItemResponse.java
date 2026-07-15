package com.fishpay.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentHistoryItemResponse {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String paymentMethod;
    private Boolean refunded;
    private LocalDateTime createdAt;

    public PaymentHistoryItemResponse () {
    }

    public PaymentHistoryItemResponse (String paymentId, String orderId, BigDecimal amount, String currency, String status, String paymentMethod, Boolean refunded, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.refunded = refunded;
        this.createdAt = createdAt;
    }

    //Getter
    public String getPaymentId () {
        return paymentId;
    }
    //Setter
    public void setPaymentId (String paymentId) {
        this.paymentId = paymentId;
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
    public BigDecimal getAmount () {
        return amount;
    }
    //Setter
    public void setAmount (BigDecimal amount) {
        this.amount = amount;
    }

    //Getter
    public String getCurrency () {
        return currency;
    }
    //Setter
    public void setCurrency (String currency) {
        this.currency = currency;
    }

    //Getter
    public String getStatus () {
        return status;
    }
    //Setter
    public void setStatus (String status) {
        this.status = status;
    }

    //Getter
    public String getPaymentMethod () {
        return paymentMethod;
    }
    //Setter
    public void setPaymentMethod (String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    //Getter
    public Boolean getRefunded () {
        return refunded;
    }
    //Setter
    public void setRefunded (Boolean refunded) {
        this.refunded = refunded;
    }

    //Getter
    public LocalDateTime getCreatedAt () {
        return createdAt;
    }
    //Setter
    public void setCreatedAt (LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
