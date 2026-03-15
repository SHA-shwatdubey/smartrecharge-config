package com.smartrecharge.rechargeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private Long customerId;
    private String customerName;
    private String email;
    private String mobileNumber;
    private String city;
}

