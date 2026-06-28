package com.fishpay.dto;

import java.math.BigDecimal;
import java.util.List;

public class GenerateInvoiceRequest {

    private String paymentId;
    private String orderId;
    private Long userId;
    private String paymentMethod;
    private String paymentStatus;
    private List<ProductDto> products;
    private BigDecimal deliveryCharges;
    private BigDecimal totalAmount;

    public GenerateInvoiceRequest() {
    }

    public GenerateInvoiceRequest(String paymentId, String orderId, Long userId,
                                  String paymentMethod, String paymentStatus,
                                  BigDecimal amount, String currency,
                                  List<ProductDto> products,
                                  BigDecimal deliveryCharges,
                                  BigDecimal totalAmount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.products = products;
        this.deliveryCharges = deliveryCharges;
        this.totalAmount = totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public BigDecimal getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(BigDecimal deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}