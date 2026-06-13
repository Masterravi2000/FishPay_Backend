package com.fishpay.service;

import com.fishpay.repository.RefundRepository;
import org.springframework.stereotype.Service;

@Service
public class RefundService {
    private final RefundRepository refundRepository;

    public RefundService(RefundRepository refundRepository){
        this.refundRepository = refundRepository;
    }
}
