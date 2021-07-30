package com.dipu.microservice.exchange.controller;

import com.dipu.microservice.exchange.proxy.CurrencyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExchangeController {
    @Autowired
    private CurrencyProxy currencyProxy;

    @Value("${spring.application.message:default}")
    String message;

    @GetMapping("/")
    public ResponseEntity<String> index(){
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @RequestMapping("/currency")
    public String currency(){
        return currencyProxy.index().getBody();
    }
}
