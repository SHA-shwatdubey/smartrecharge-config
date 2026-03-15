package com.smartrecharge.customerservice.service;

import com.smartrecharge.customerservice.dto.CustomerRequestDto;
import com.smartrecharge.customerservice.dto.CustomerResponseDto;
import java.util.List;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto requestDto);

    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long customerId);

    CustomerResponseDto updateCustomer(Long customerId, CustomerRequestDto requestDto);
}

