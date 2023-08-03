package com.capstone.capstonediscount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CapstoneDiscountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneDiscountApplication.class, args);
	}

}
