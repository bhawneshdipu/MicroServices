package com.dipu.microservice.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExchangeMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeMicroServiceApplication.class, args);
	}

}
