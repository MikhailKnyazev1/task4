package com.example.task4.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import feign.RetryableException;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 503) { // Server temporarily unavailable
            return new RetryableException(
                    response.status(),
                    "Service temporarily unavailable, will retry",
                    response.request().httpMethod(),
                    null,
                    System.currentTimeMillis(),
                    response.request());
        }
        return new Exception("HTTP " + response.status() + " occurred for " + methodKey);
    }
}
