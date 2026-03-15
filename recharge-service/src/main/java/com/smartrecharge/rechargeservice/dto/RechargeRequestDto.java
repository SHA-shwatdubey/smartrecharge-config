package com.smartrecharge.rechargeservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class RechargeRequestDto {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @NotBlank(message = "Operator name is required")
    private String operatorName;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Recharge amount must be greater than zero")
    private BigDecimal amount;
}

