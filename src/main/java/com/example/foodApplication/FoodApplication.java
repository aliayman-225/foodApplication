package com.example.foodApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.foodApplication.*")
public class FoodApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodApplication.class, args);

	}

}
