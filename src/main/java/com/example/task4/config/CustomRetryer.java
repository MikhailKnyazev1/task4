package com.example.task4.config;

import feign.RetryableException;
import feign.Retryer;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CustomRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    private int attempt = 1;

    public CustomRetryer(int maxAttempts, long backoff) {
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ < maxAttempts) {
            try {
                SECONDS.sleep(backoff);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
            return;
        }
        throw e;
    }

    @Override
    public Retryer clone() {
        return new CustomRetryer(maxAttempts, backoff);
    }
}
