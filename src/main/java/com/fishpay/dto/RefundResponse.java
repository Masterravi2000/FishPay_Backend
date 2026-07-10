package com.fishpay.dto;

import com.fishpay.util.RefundStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RefundResponse {
    private String refundId;
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private String currency;
    private RefundStatus status;
    private String reason;
    private LocalDateTime createdAt;

    public RefundResponse () {
    }

    public RefundResponse (String refundId, String paymentId, String orderId, BigDecimal amount, String currency, RefundStatus status, String reason, LocalDateTime createdAt) {
        this.refundId = refundId;
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    //Getter
    public String getRefundId () {
        return refundId;
    }
    //Setter
    public void setRefundId (String refundId) {
        this.refundId = refundId;
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
    public RefundStatus getStatus () {
        return status;
    }
    //Setter
    public void setStatus (RefundStatus status) {
        this.status = status;
    }

    //Getter
    public String getReason () {
        return reason;
    }
    //Setter
    public void setReason (String reason) {
        this.reason = reason;
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
