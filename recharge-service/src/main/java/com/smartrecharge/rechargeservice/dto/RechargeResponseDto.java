package com.smartrecharge.rechargeservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargeResponseDto {

    private Long rechargeId;
    private Long customerId;
    private String mobileNumber;
    private String operatorName;
    private BigDecimal amount;
    private String rechargeStatus;
    private LocalDateTime requestTime;
}

