package com.example.supermercadopb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.supermercadopb")
@EntityScan(basePackages = "com.example.supermercadopb.entity")
public class SupermercadoPbApplication {
	public static void main(String[] args) {
		SpringApplication.run(SupermercadoPbApplication.class, args);
	}
}
