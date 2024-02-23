package com.example.spring.demospringrest.config;

import com.example.spring.demospringrest.web.interceptors.ClientControllerInterceptor;
import com.example.spring.demospringrest.web.interceptors.LoggingControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingControllerInterceptor());
        registry.addInterceptor(clientControllerInterceptor()).addPathPatterns("/api/v1/client/**");
    }

    @Bean
    public LoggingControllerInterceptor loggingControllerInterceptor() {
        return new LoggingControllerInterceptor();
    }

    @Bean
    public ClientControllerInterceptor clientControllerInterceptor() {
        return new ClientControllerInterceptor();
    }

}
