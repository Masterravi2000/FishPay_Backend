package com.fishpay.dto;

public class VerifySignatureRequest {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    public VerifySignatureRequest () {
    }

    public VerifySignatureRequest (String razorpayPaymentId, String razorpayOrderId, String razorpaySignature){
        this.razorpayPaymentId = razorpayPaymentId;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpaySignature = razorpaySignature;
    }

    //Getter
    public String getRazorpayPaymentId () {
        return razorpayPaymentId;
    }
    //Setter
    public void setRazorpayPaymentId (String razorpayPaymentId){
        this.razorpayPaymentId = razorpayPaymentId;
    }

    //Getter
    public String getRazorpayOrderId () {
        return razorpayOrderId;
    }
    //Setter
    public void setRazorpayOrderId (String razorpayOrderId){
        this.razorpayOrderId = razorpayOrderId;
    }

    //Getter
    public String getRazorpaySignature () {
        return razorpaySignature;
    }
    //Setter
    public void setRazorpaySignature (String razorpaySignature){
        this.razorpaySignature = razorpaySignature;
    }
}
