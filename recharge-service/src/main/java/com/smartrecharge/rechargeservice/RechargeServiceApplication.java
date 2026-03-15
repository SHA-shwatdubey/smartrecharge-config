package com.smartrecharge.rechargeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RechargeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RechargeServiceApplication.class, args);
    }
}


