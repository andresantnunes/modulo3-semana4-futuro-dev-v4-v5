package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos os endpoints
                .allowedOrigins("http://localhost:5173", "http://localhost:3000", "*") // Libera o front (5173, 3000 e o resto)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Métodos permitidos
                // OPTIONS é necessário para o preflight/pre-avisar do CORS,
                // que é uma requisição feita pelo navegador para verificar se o
                // servidor aceita a requisição real
                .allowedHeaders("*") // Headers permitidos
                .allowCredentials(false);
    }
}