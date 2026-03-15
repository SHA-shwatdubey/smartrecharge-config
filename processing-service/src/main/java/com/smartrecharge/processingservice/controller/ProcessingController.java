package com.smartrecharge.processingservice.controller;

import com.smartrecharge.processingservice.dto.RechargeTransactionResponseDto;
import com.smartrecharge.processingservice.service.ProcessingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class ProcessingController {

    private final ProcessingService processingService;

    @GetMapping
    public List<RechargeTransactionResponseDto> getAllTransactions() {
        return processingService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public RechargeTransactionResponseDto getTransactionById(@PathVariable("id") Long transactionId) {
        return processingService.getTransactionById(transactionId);
    }

    @GetMapping("/recharge/{rechargeId}")
    public List<RechargeTransactionResponseDto> getTransactionsByRechargeId(@PathVariable Long rechargeId) {
        return processingService.getTransactionsByRechargeId(rechargeId);
    }
}

