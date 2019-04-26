package com.gateway.zuul.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthChecker implements HealthIndicator {
 
    private boolean up = true;
 
    @Override
    public Health health() {
        if (up) {
            return new Health.Builder().up().build();
        } else {
            return new Health.Builder().withDetail("error", "client is down").down().build();
        }
 
    }
 
    public boolean isUp() {
        return up;
    }
 
    public void setUp(boolean up) {
        this.up = up;
    }
}