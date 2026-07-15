package com.fishpay.dto;

import java.math.BigDecimal;
import java.util.List;

public class PaymentHistoryResponse {
    private Long totalPayments;
    private Long totalSuccessfulPayments;
    private Long totalFailedPayments;
    private BigDecimal totalAmountPaid;

    private List<PaymentHistoryItemResponse> payments;

    private int page;
    private int totalPages;

    public PaymentHistoryResponse () {
    }

    public PaymentHistoryResponse (Long totalPayments, Long totalSuccessfulPayments, Long totalFailedPayments, BigDecimal totalAmountPaid, List<PaymentHistoryItemResponse> payments, int page, int totalPages) {
        this.totalPayments = totalPayments;
        this.totalSuccessfulPayments = totalSuccessfulPayments;
        this.totalFailedPayments = totalFailedPayments;
        this.totalAmountPaid = totalAmountPaid;
        this.payments = payments;
        this.page = page;
        this.totalPages = totalPages;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Long getTotalSuccessfulPayments() {
        return totalSuccessfulPayments;
    }

    public void setTotalSuccessfulPayments(Long totalSuccessfulPayments) {
        this.totalSuccessfulPayments = totalSuccessfulPayments;
    }

    public Long getTotalFailedPayments() {
        return totalFailedPayments;
    }

    public void setTotalFailedPayments(Long totalFailedPayments) {
        this.totalFailedPayments = totalFailedPayments;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public List<PaymentHistoryItemResponse> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentHistoryItemResponse> payments) {
        this.payments = payments;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
