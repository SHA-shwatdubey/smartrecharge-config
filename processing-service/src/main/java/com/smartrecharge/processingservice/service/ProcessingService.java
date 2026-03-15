package com.smartrecharge.processingservice.service;

import com.smartrecharge.processingservice.dto.RechargeEventDto;
import com.smartrecharge.processingservice.dto.RechargeTransactionResponseDto;
import java.util.List;

public interface ProcessingService {

    void processRechargeEvent(RechargeEventDto eventDto);

    List<RechargeTransactionResponseDto> getAllTransactions();

    RechargeTransactionResponseDto getTransactionById(Long transactionId);

    List<RechargeTransactionResponseDto> getTransactionsByRechargeId(Long rechargeId);
}

