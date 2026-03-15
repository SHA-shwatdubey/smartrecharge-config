package com.smartrecharge.rechargeservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recharges")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rechargeId;

    private Long customerId;
    private String mobileNumber;
    private String operatorName;
    private BigDecimal amount;
    private String rechargeStatus;
    private LocalDateTime requestTime;
}

