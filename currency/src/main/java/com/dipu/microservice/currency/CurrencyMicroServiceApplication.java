package com.dipu.microservice.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CurrencyMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyMicroServiceApplication.class, args);
	}

}
