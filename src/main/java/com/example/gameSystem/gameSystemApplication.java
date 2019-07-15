package com.example.gameSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class gameSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(gameSystemApplication.class, args);
	}
}
