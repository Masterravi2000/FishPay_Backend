package com.fishpay.dto;

import java.math.BigDecimal;
import java.util.List;

public class RefundHistoryResponse {
    private Long totalRefunds;
    private BigDecimal totalRefundAmount;
    private Long totalRefundsInProgress;
    private List<RefundHistoryItemResponse> refunds;
    private int page;
    private int totalPage;

    public RefundHistoryResponse () {
    }

    public RefundHistoryResponse (Long totalRefunds, BigDecimal totalRefundAmount, Long totalRefundsInProgress, List<RefundHistoryItemResponse> refunds, int page, int totalPage) {
        this.totalRefunds = totalRefunds;
        this.totalRefundAmount = totalRefundAmount;
        this.totalRefundsInProgress = totalRefundsInProgress;
        this.refunds = refunds;
        this.page = page;
        this.totalPage = totalPage;
    }

    //Getter
    public Long getTotalRefunds () {
        return totalRefunds;
    }
    //Setter
    public void setTotalRefunds (Long totalRefunds) {
        this.totalRefunds = totalRefunds;
    }

    //Getter
    public BigDecimal getTotalRefundAmount () {
        return totalRefundAmount;
    }
    //Setter
    public void setTotalRefundAmount (BigDecimal totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

    //Getter
    public Long getTotalRefundsInProgress () {
        return totalRefundsInProgress;
    }
    //Setter
    public void setTotalRefundsInProgress (Long totalRefundsInProgress) {
        this.totalRefundsInProgress = totalRefundsInProgress;
    }

    //Getter
    public List<RefundHistoryItemResponse> getRefunds () {
        return refunds;
    }
    //Setter
    public void setRefunds (List<RefundHistoryItemResponse> refunds) {
        this.refunds = refunds;
    }

    //Getter
    public int getPage () {
        return page;
    }
    //Setter
    public void setPage (int page) {
        this.page = page;
    }

    //Getter
    public int getTotalPage () {
        return totalPage;
    }
    //Setter
    public void setTotalPage (int totalPage) {
        this.totalPage = totalPage;
    }
}
