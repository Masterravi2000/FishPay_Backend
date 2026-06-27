package com.fishpay.dto;

import java.math.BigDecimal;

public class CreateOrderResponse {
    private String id;
    private BigDecimal amount;
    private String currency;
    private String status;

    public CreateOrderResponse (){
    }

    public CreateOrderResponse(String id, BigDecimal amount, String currency, String status){
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
    }

    // Getter
    public String getId() {
        return id;
    }

    // Setter
    public void setId(String id){
        this.id = id;
    }

    // Getter
    public BigDecimal getAmount() {
        return amount;
    }

    // Setter
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // Getter
    public String getCurrency() {
        return currency;
    }

    // Setter
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Getter
    public String getStatus() {
        return status;
    }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }
}
