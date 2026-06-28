package com.fishpay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private String paymentId;
    private BigDecimal amount;
    private String currency;
    private String status;
    private Long userId;
    private String paymentMethod;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //No-args constructor
    public Payment () {
    }

    //Getter
    public Long getId () {
        return id;
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
    public void setAmount (BigDecimal amount){
        this.amount = amount;
    }

    //Getter
    public String getCurrency () {
        return currency;
    }
    //Setter
    public void setCurrency (String currency){
        this.currency = currency;
    }

    //Getter
    public String getStatus () {
        return status;
    }
    //Setter
    public void setStatus (String status){
        this.status = status;
    }

    //Getter
    public Long getUserId () {
        return userId;
    }
    //Setter
    public void setUserId (Long userId){
        this.userId = userId;
    }

    //Getter
    public String getPaymentMethod () {
        return paymentMethod;
    }
    //Setter
    public void setPaymentMethod (String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
