package com.fishpay.dto;
import java.util.List;
import java.math.BigDecimal;

public class VerifyPaymentRequest {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    private Long userId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;

    private List<ProductDto> products;
    private BigDecimal deliveryCharges;
    private BigDecimal totalAmount;

    //empty object
    public VerifyPaymentRequest () {
    }

    //constructor
    public VerifyPaymentRequest (String razorpayPaymentId, String razorpayOrderId, String razorpaySignature, Long userId, BigDecimal amount, String currency, String paymentMethod, List<ProductDto> products, BigDecimal deliveryCharges, BigDecimal totalAmount ){
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpaySignature = razorpaySignature;

        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;

        this.products = products;
        this.deliveryCharges = deliveryCharges;
        this.totalAmount = totalAmount;
    }

    //Getter
    public String getRazorpayPaymentId () {
        return razorpayPaymentId;
    }
    //Setter
    public void setRazorpayPaymentId (String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    //Getter
    public String getRazorpayOrderId () {
        return razorpayOrderId;
    }
    //Setter
    public void setRazorpayOrderId (String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    //Getter
    public String getRazorpaySignature () {
        return razorpaySignature;
    }
    //Setter
    public void setRazorpaySignature (String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
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
    public String getPaymentMethod () {
        return paymentMethod;
    }
    //Setter
    public void setPaymentMethod (String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    //Getter
    public List<ProductDto> getProducts () {
        return products;
    }
    //Setter
    public void setProducts (List<ProductDto> products) {
        this.products = products;
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
}
