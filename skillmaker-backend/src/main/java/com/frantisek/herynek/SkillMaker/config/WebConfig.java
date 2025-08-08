package com.frantisek.herynek.SkillMaker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Povolit všechny endpointy
                        .allowedOrigins("http://localhost:3000") // frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // povolené metody
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
