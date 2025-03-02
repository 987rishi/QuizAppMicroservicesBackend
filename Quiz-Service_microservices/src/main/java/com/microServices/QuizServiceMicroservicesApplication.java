package com.microServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceMicroservicesApplication.class, args);
	}

}
