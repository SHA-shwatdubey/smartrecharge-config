package com.smartrecharge.customerservice.service.impl;

import com.smartrecharge.customerservice.dto.CustomerRequestDto;
import com.smartrecharge.customerservice.dto.CustomerResponseDto;
import com.smartrecharge.customerservice.entity.Customer;
import com.smartrecharge.customerservice.exception.ResourceNotFoundException;
import com.smartrecharge.customerservice.repository.CustomerRepository;
import com.smartrecharge.customerservice.service.CustomerService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {
        Customer customer = Customer.builder()
                .customerName(requestDto.getCustomerName())
                .email(requestDto.getEmail())
                .mobileNumber(requestDto.getMobileNumber())
                .city(requestDto.getCity())
                .registrationDate(LocalDate.now())
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        log.info("Created customer with id={}", savedCustomer.getCustomerId());
        return toResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        return toResponseDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long customerId, CustomerRequestDto requestDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        customer.setCustomerName(requestDto.getCustomerName());
        customer.setEmail(requestDto.getEmail());
        customer.setMobileNumber(requestDto.getMobileNumber());
        customer.setCity(requestDto.getCity());

        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Updated customer with id={}", updatedCustomer.getCustomerId());
        return toResponseDto(updatedCustomer);
    }

    private CustomerResponseDto toResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .city(customer.getCity())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }
}

