package com.smartrecharge.processingservice.controller;

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
        response.put("service", "Processing Service");
        response.put("status", "UP");
        response.put("port", "8065");
        response.put("documentation", "http://localhost:8065/swagger-ui.html");
        response.put("health", "http://localhost:8065/actuator/health");
        response.put("endpoints", new String[]{
            "GET /api/transactions - Get all transactions",
            "GET /api/transactions/{id} - Get transaction by ID",
            "GET /api/transactions/recharge/{rechargeId} - Get transactions by recharge"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> simpleHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Processing Service");
        return ResponseEntity.ok(response);
    }
}

