package com.smartrecharge.rechargeservice.service.impl;

import com.smartrecharge.rechargeservice.client.CustomerClient;
import com.smartrecharge.rechargeservice.dto.CustomerResponseDto;
import com.smartrecharge.rechargeservice.dto.RechargeEventDto;
import com.smartrecharge.rechargeservice.dto.RechargeRequestDto;
import com.smartrecharge.rechargeservice.dto.RechargeResponseDto;
import com.smartrecharge.rechargeservice.entity.Recharge;
import com.smartrecharge.rechargeservice.exception.BadRequestException;
import com.smartrecharge.rechargeservice.exception.ResourceNotFoundException;
import com.smartrecharge.rechargeservice.messaging.RechargeEventProducer;
import com.smartrecharge.rechargeservice.repository.RechargeRepository;
import com.smartrecharge.rechargeservice.service.RechargeService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RechargeServiceImpl implements RechargeService {

    private final RechargeRepository rechargeRepository;
    private final CustomerClient customerClient;
    private final RechargeEventProducer rechargeEventProducer;

    @Override
    public RechargeResponseDto createRecharge(RechargeRequestDto requestDto) {
        CustomerResponseDto customer = customerClient.getCustomerById(requestDto.getCustomerId());
        if (!customer.getMobileNumber().equals(requestDto.getMobileNumber())) {
            throw new BadRequestException("Mobile number does not match registered customer mobile number");
        }

        Recharge recharge = Recharge.builder()
                .customerId(requestDto.getCustomerId())
                .mobileNumber(requestDto.getMobileNumber())
                .operatorName(requestDto.getOperatorName())
                .amount(requestDto.getAmount())
                .rechargeStatus("PENDING")
                .requestTime(LocalDateTime.now())
                .build();

        Recharge savedRecharge = rechargeRepository.save(recharge);
        log.info("Created recharge request with id={} for customerId={}",
                savedRecharge.getRechargeId(), savedRecharge.getCustomerId());

        rechargeEventProducer.publishRechargeEvent(RechargeEventDto.builder()
                .rechargeId(savedRecharge.getRechargeId())
                .customerId(savedRecharge.getCustomerId())
                .mobileNumber(savedRecharge.getMobileNumber())
                .operatorName(savedRecharge.getOperatorName())
                .amount(savedRecharge.getAmount())
                .requestTime(savedRecharge.getRequestTime())
                .build());

        return toResponseDto(savedRecharge);
    }

    @Override
    public List<RechargeResponseDto> getAllRecharges() {
        return rechargeRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    @Override
    public RechargeResponseDto getRechargeById(Long rechargeId) {
        Recharge recharge = rechargeRepository.findById(rechargeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recharge not found with id: " + rechargeId));
        return toResponseDto(recharge);
    }

    @Override
    public List<RechargeResponseDto> getRechargesByCustomerId(Long customerId) {
        return rechargeRepository.findByCustomerId(customerId).stream().map(this::toResponseDto).toList();
    }

    private RechargeResponseDto toResponseDto(Recharge recharge) {
        return RechargeResponseDto.builder()
                .rechargeId(recharge.getRechargeId())
                .customerId(recharge.getCustomerId())
                .mobileNumber(recharge.getMobileNumber())
                .operatorName(recharge.getOperatorName())
                .amount(recharge.getAmount())
                .rechargeStatus(recharge.getRechargeStatus())
                .requestTime(recharge.getRequestTime())
                .build();
    }
}

