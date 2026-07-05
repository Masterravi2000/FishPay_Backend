package com.fishpay.dto;

public class InvoiceStatusResponse {
    private String paymentId;
    private String invoiceUrl;

    public InvoiceStatusResponse () {
    }

    public InvoiceStatusResponse (String paymentId, String invoiceUrl){
        this.paymentId = paymentId;
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
    public String getInvoiceUrl () {
        return invoiceUrl;
    }
    //Setter
    public void setInvoiceUrl (String invoiceUrl){
        this.invoiceUrl = invoiceUrl;
    }
}
