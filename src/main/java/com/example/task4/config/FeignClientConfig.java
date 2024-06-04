package com.example.task4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.codec.ErrorDecoder;
import feign.Retryer;

@Configuration
public class FeignClientConfig {

    @Bean
    public Retryer feignRetryer() {
        // Задаем 3 попытки с интервалом в 5000 миллисекунд
        return new CustomRetryer(3, 5000);
    }

    @Bean
    public ErrorDecoder feignErrorDecoder() {
        // Используем наш кастомный декодер ошибок
        return new CustomErrorDecoder();
    }
}
