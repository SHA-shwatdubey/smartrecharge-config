package com.smartrecharge.customerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "Customer Service");
        response.put("status", "UP");
        response.put("port", "8063");
        response.put("documentation", "http://localhost:8063/swagger-ui.html");
        response.put("health", "http://localhost:8063/actuator/health");
        response.put("endpoints", new String[]{
            "POST /api/customers - Create customer",
            "GET /api/customers - Get all customers",
            "GET /api/customers/{id} - Get customer by ID",
            "PUT /api/customers/{id} - Update customer"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> simpleHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Customer Service");
        return ResponseEntity.ok(response);
    }
}

