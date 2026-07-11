package com.fishpay.dto;

import com.fishpay.util.RefundStatus;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RefundHistoryItemResponse {
    private String refundId;
    private String orderId;
    private BigDecimal amount;
    private RefundStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;

    public RefundHistoryItemResponse () {
    }

    public RefundHistoryItemResponse (String refundId, String orderId, BigDecimal amount, RefundStatus status, LocalDateTime requestedAt, LocalDateTime completedAt) {
        this.refundId = refundId;
        this.orderId = orderId;
        this. amount = amount;
        this.status = status;
        this.requestedAt = requestedAt;
        this.completedAt = completedAt;
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
    public RefundStatus getStatus () {
        return status;
    }
    //Setter
    public void setStatus (RefundStatus status) {
        this.status = status;
    }

    //Getter
    public LocalDateTime getRequestedAt () {
        return requestedAt;
    }
    //Setter
    public void setRequestedAt (LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    //Getter
    public LocalDateTime getCompletedAt () {
        return completedAt;
    }
    //Setter
    public void setCompletedAt (LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
