package com.smartrecharge.customerservice.dto;

import java.time.LocalDate;
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
public class CustomerResponseDto {

    private Long customerId;
    private String customerName;
    private String email;
    private String mobileNumber;
    private String city;
    private LocalDate registrationDate;
}

