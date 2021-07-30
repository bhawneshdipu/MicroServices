package com.dipu.microservice.currency.controller;

import com.dipu.microservice.currency.proxy.ExchangeProxy;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyController {

    @Autowired
    private ExchangeProxy exchangeProxy;

    @Value("${spring.application.message:default}")
    String message;

    @GetMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/exchange")
    public ResponseEntity<String> exchange(){
        return exchangeProxy.index();
    }

}
