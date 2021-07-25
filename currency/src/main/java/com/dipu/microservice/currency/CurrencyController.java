package com.dipu.microservice.currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    @Value("{spring.application.message:default}")
    String message;
    @GetMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
