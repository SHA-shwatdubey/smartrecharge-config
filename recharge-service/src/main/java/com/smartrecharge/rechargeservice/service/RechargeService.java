package com.smartrecharge.rechargeservice.service;

import com.smartrecharge.rechargeservice.dto.RechargeRequestDto;
import com.smartrecharge.rechargeservice.dto.RechargeResponseDto;
import java.util.List;

public interface RechargeService {

    RechargeResponseDto createRecharge(RechargeRequestDto requestDto);

    List<RechargeResponseDto> getAllRecharges();

    RechargeResponseDto getRechargeById(Long rechargeId);

    List<RechargeResponseDto> getRechargesByCustomerId(Long customerId);
}

