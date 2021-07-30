package com.dipu.microservice.exchange.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currency",url = "http://localhost:8082")
public interface CurrencyProxy {
    @GetMapping("/")
    public ResponseEntity<String> index();

}
