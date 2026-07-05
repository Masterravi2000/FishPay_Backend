package com.fishpay.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GenerateInvoiceResponse {
    private String invoiceNumber;
    private String invoiceUrl;

    private String paymentId;
    private String orderId;

    private String paymentMethod;
    private String paymentStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime orderTime;

    private BigDecimal deliveryCharges;
    private BigDecimal totalAmount;

    private List<ProductDto> products;

    //No-Args constructor
    public GenerateInvoiceResponse () {
    }

    //now create the main constructor
    public GenerateInvoiceResponse (String invoiceNumber, String invoiceUrl, String paymentId, String orderId, String paymentMethod, String paymentStatus, LocalDateTime orderTime, BigDecimal deliveryCharges, BigDecimal totalAmount, List<ProductDto> products){
        this.invoiceNumber = invoiceNumber;
        this.invoiceUrl = invoiceUrl;
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderTime = orderTime;
        this.deliveryCharges = deliveryCharges;
        this.totalAmount = totalAmount;
        this.products = products;
    }

    //Getter
    public String getInvoiceNumber () {
        return invoiceNumber;
    }
    //Setter
    public void setInvoiceNumber (String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
    public LocalDateTime getOrderTime () {
        return orderTime;
    }
    //Setter
    public void setOrderTime (LocalDateTime orderTime) {
        this.orderTime = orderTime;
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
    public List<ProductDto> getProducts () {
        return products;
    }
    //Setter
    public void setProducts (List<ProductDto> products){
        this.products = products;
    }

}
