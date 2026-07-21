package com.forge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class HealthController {

@GetMapping("/test")
public String test() {
    System.out.println("TEST HIT");
    return "OK";
}
    @GetMapping("/api/v1/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "Forge",
                "version", "1.0.0",
                "timestamp", Instant.now().toString()
        );
    }
}