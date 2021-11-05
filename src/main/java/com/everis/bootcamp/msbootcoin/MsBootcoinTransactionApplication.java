package com.everis.bootcamp.msbootcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsBootcoinTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBootcoinTransactionApplication.class, args);
	}

}
