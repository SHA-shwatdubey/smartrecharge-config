package com.smartrecharge.rechargeservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rechargeServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Recharge Service API")
                .version("v1")
                .description("SmartRecharge recharge APIs"));
    }
}

