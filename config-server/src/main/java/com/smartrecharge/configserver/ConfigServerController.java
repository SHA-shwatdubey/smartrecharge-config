package com.smartrecharge.configserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigServerController {

    @GetMapping("/")
    public Map<String, String> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "SmartRecharge Config Server");
        response.put("port", "8060");
        response.put("description", "Spring Cloud Config Server - Fetches configuration from GitHub");
        response.put("endpoints", "GET /customer-service/default, GET /recharge-service/default, GET /processing-service/default");
        response.put("health", "http://localhost:8060/actuator/health");
        response.put("github-repo", "https://github.com/SHA-shwatdubey/smartrecharge-config");
        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Config Server");
        return response;
    }
}

