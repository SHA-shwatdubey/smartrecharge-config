package com.smartrecharge.customerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect old webjars URL to SpringDoc's proper swagger-ui URL
        registry.addRedirectViewController(
                "/webjars/swagger-ui/index.html",
                "/swagger-ui.html"
        );
    }
}


