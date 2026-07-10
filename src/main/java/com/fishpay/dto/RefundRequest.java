package com.fishpay.dto;

import java.math.BigDecimal;

public class RefundRequest {
    private String paymentId;
    private BigDecimal amount;
    private String reason;

    public RefundRequest () {
    }

    public RefundRequest (String paymentId, BigDecimal amount, String reason) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.reason = reason;
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
    public BigDecimal getAmount () {
        return amount;
    }
    //Setter
    public void setAmount (BigDecimal amount) {
        this.amount = amount;
    }

    //Getter
    public String getReason () {
        return reason;
    }
    //Setter
    public void setReason (String reason) {
        this.reason = reason;
    }
}
