package com.smartrecharge.processingservice.service.impl;

import com.smartrecharge.processingservice.dto.RechargeEventDto;
import com.smartrecharge.processingservice.dto.RechargeTransactionResponseDto;
import com.smartrecharge.processingservice.entity.RechargeTransaction;
import com.smartrecharge.processingservice.exception.ResourceNotFoundException;
import com.smartrecharge.processingservice.repository.RechargeTransactionRepository;
import com.smartrecharge.processingservice.service.ProcessingService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessingServiceImpl implements ProcessingService {

    private final RechargeTransactionRepository rechargeTransactionRepository;

    @Override
    public void processRechargeEvent(RechargeEventDto eventDto) {
        RechargeTransaction transaction = RechargeTransaction.builder()
                .rechargeId(eventDto.getRechargeId())
                .customerId(eventDto.getCustomerId())
                .mobileNumber(eventDto.getMobileNumber())
                .operatorName(eventDto.getOperatorName())
                .amount(eventDto.getAmount())
                .processingStatus("PROCESSED")
                .remarks("Recharge processed successfully")
                .processedTime(LocalDateTime.now())
                .build();

        rechargeTransactionRepository.save(transaction);
        log.info("Processed recharge event and saved transaction for rechargeId={}", eventDto.getRechargeId());
    }

    @Override
    public List<RechargeTransactionResponseDto> getAllTransactions() {
        return rechargeTransactionRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    @Override
    public RechargeTransactionResponseDto getTransactionById(Long transactionId) {
        RechargeTransaction transaction = rechargeTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));
        return toResponseDto(transaction);
    }

    @Override
    public List<RechargeTransactionResponseDto> getTransactionsByRechargeId(Long rechargeId) {
        return rechargeTransactionRepository.findByRechargeId(rechargeId).stream().map(this::toResponseDto).toList();
    }

    private RechargeTransactionResponseDto toResponseDto(RechargeTransaction transaction) {
        return RechargeTransactionResponseDto.builder()
                .transactionId(transaction.getTransactionId())
                .rechargeId(transaction.getRechargeId())
                .customerId(transaction.getCustomerId())
                .mobileNumber(transaction.getMobileNumber())
                .operatorName(transaction.getOperatorName())
                .amount(transaction.getAmount())
                .processingStatus(transaction.getProcessingStatus())
                .remarks(transaction.getRemarks())
                .processedTime(transaction.getProcessedTime())
                .build();
    }
}

