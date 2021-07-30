package com.dipu.microservice.currency.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "exchange",url = "http://localhost:8083")
public interface ExchangeProxy {

    @GetMapping("/")
    public ResponseEntity<String> index();
}
