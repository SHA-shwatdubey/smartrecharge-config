package com.smartrecharge.processingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI processingServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Processing Service API")
                .version("v1")
                .description("SmartRecharge transaction processing APIs"));
    }
}

