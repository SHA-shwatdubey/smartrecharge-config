package com.smartrecharge.rechargeservice.controller;

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
        response.put("service", "Recharge Service");
        response.put("status", "UP");
        response.put("port", "8064");
        response.put("documentation", "http://localhost:8064/swagger-ui.html");
        response.put("health", "http://localhost:8064/actuator/health");
        response.put("endpoints", new String[]{
            "POST /api/recharges - Create recharge",
            "GET /api/recharges - Get all recharges",
            "GET /api/recharges/{id} - Get recharge by ID",
            "GET /api/recharges/customer/{customerId} - Get recharges by customer"
        });
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> simpleHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Recharge Service");
        return ResponseEntity.ok(response);
    }
}

