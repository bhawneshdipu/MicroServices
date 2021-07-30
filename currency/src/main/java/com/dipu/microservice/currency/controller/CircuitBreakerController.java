package com.dipu.microservice.currency.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CircuitBreakerController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/sample-api")
    @Retry(name = "sample-api",fallbackMethod = "sampleApiFallback")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default",fallbackMethod = "concurrentLimitReached")
    public String sampleApi(HttpServletRequest request){
        logger.info("sampleApi call:{}",request.getRequestURI());
        return new RestTemplate().getForEntity("http://localhost:8080/",String.class).getBody();
        //return "sample-api-response";
    }
    public String sampleApiFallback(Exception exception){
        return "sample-fallback-response";
    }
    public String concurrentLimitReached(Exception exception){
        return "concurrentLimitReached";
    }

    @GetMapping("/sample-api-circuit")
    @CircuitBreaker(name = "default",fallbackMethod = "sampleApiFallback")
    @RateLimiter(name = "default")
    @Bulkhead(name = "default",fallbackMethod = "concurrentLimitReached")
    public String sampleCircuitApi(HttpRequest request){
        logger.info("sampleCircuitApi call:{}",request.getURI());
        return new RestTemplate().getForEntity("http://localhost:8080/",String.class).getBody();
        //return "sample-api-response";
    }
}