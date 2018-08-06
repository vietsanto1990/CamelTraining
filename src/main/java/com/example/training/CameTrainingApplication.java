package com.example.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CameTrainingApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(CameTrainingApplication.class, args);
		Thread.sleep(2000);
	}
}
