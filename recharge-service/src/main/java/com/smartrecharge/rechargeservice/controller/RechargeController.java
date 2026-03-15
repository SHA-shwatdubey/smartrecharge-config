package com.smartrecharge.rechargeservice.controller;

import com.smartrecharge.rechargeservice.dto.RechargeRequestDto;
import com.smartrecharge.rechargeservice.dto.RechargeResponseDto;
import com.smartrecharge.rechargeservice.service.RechargeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recharges")
@RequiredArgsConstructor
public class RechargeController {

    private final RechargeService rechargeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RechargeResponseDto createRecharge(@Valid @RequestBody RechargeRequestDto requestDto) {
        return rechargeService.createRecharge(requestDto);
    }

    @GetMapping
    public List<RechargeResponseDto> getAllRecharges() {
        return rechargeService.getAllRecharges();
    }

    @GetMapping("/{id}")
    public RechargeResponseDto getRechargeById(@PathVariable("id") Long rechargeId) {
        return rechargeService.getRechargeById(rechargeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<RechargeResponseDto> getRechargesByCustomerId(@PathVariable Long customerId) {
        return rechargeService.getRechargesByCustomerId(customerId);
    }
}

