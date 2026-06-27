package com.fishpay.dto;

import java.math.BigDecimal;

public class CreateOrderRequest {

    private BigDecimal amount;
    private String currency;

    //Create an empty object first
    public CreateOrderRequest(){
    }

    //Then create the constructor for showcasing the obj initial state along with its fields and initial/previous values
    public CreateOrderRequest(BigDecimal amount, String currency){
        this.amount = amount;
        this.currency = currency;
    }

    //Getter
    public BigDecimal getAmount() {
        return amount;
    }

    //Setter
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    //Getter
    public String getCurrency(){
        return currency;
    }

    //Setter
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
