package com.fishpay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;
    private String invoiceUrl;

    private String paymentId;
    private String orderId;
    private Long userId;

    private String paymentMethod;
    private String paymentStatus;

    private BigDecimal deliveryCharges;
    private BigDecimal totalAmount;

    private boolean viewed = false;

    @CreationTimestamp
    private LocalDateTime orderTime;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //empty No-Args constructor
    public Invoice () {
    }

    //Getter
    public Long getId (){
        return id;
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
    public String getInvoiceUrl () {
        return invoiceUrl;
    }
    //Setter
    public void setInvoiceUrl (String invoiceUrl){
        this.invoiceUrl = invoiceUrl;
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
    public String getOrderId (){
        return orderId;
    }
    //Setter
    public void setOrderId (String orderId) {
        this.orderId = orderId;
    }

    //Getter
    public Long getUserId () {
        return userId;
    }
    //Setter
    public void setUserId (Long userId) {
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

    //Getter
    public String getPaymentStatus () {
        return paymentStatus;
    }
    //Setter
    public void setPaymentStatus (String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    //Getter
    public BigDecimal getDeliveryCharges () {
        return deliveryCharges;
    }
    //Setter
    public void setDeliveryCharges (BigDecimal deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
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
    public boolean isViewed () {
        return viewed;
    }
    //Setter
    public void setViewed (boolean viewed) {
        this.viewed = viewed;
    }

    //Getter
    public LocalDateTime getOrderTime () {
        return orderTime;
    }

    //Getter
    public LocalDateTime getUpdatedAt () {
        return updatedAt;
    }
}
