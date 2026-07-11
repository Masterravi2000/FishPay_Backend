package com.fishpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/health",
                                "/api/v1/payments/create-order",
                                "/api/v1/payments/verify-signature",
                                "/api/v1/payments/webhook",
                                "/api/v1/invoices/invoice-status/**",
                                "/api/v1/invoices/history",
                                "/api/v1/invoices/viewed/**",
                                "/api/v1/refunds/refund",
                                "/api/v1/refunds/refund/**",
                                "/api/v1/refunds/history"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }
}
