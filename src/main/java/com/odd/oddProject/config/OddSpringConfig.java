package com.odd.oddProject.config;

import com.odd.oddProject.service.OpenApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OddSpringConfig {
    @Bean
    public OpenApiService openApiService(){
        return new OpenApiService();
    }

}
