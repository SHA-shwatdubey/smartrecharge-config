package com.smartrecharge.apigateway.controller;

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
        response.put("service", "API Gateway");
        response.put("status", "UP");
        response.put("port", "8062");
        response.put("description", "SmartRecharge API Gateway - Routes requests to microservices");
        response.put("routes", new String[]{
            "GET  /api/customers -> Customer Service (8063)",
            "POST /api/customers -> Customer Service (8063)",
            "GET  /api/recharges -> Recharge Service (8064)",
            "POST /api/recharges -> Recharge Service (8064)",
            "GET  /api/transactions -> Processing Service (8065)"
        });
        response.put("eureka_dashboard", "http://localhost:8061");
        response.put("health_check", "http://localhost:8062/actuator/health");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> simpleHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "API Gateway");
        return ResponseEntity.ok(response);
    }
}

