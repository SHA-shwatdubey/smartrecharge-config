package com.smartrecharge.rechargeservice.client;

import com.smartrecharge.rechargeservice.dto.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/customers/{id}")
    CustomerResponseDto getCustomerById(@PathVariable("id") Long customerId);
}

