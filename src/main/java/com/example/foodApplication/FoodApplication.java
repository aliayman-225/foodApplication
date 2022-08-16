package com.example.foodApplication;

import com.example.foodApplication.JWT.JwtUtils;
import com.example.foodApplication.services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.switchuser.AuthenticationSwitchUserEvent;

@SpringBootApplication
@ComponentScan("com.example.foodApplication.*")
public class FoodApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodApplication.class, args);

		}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}



}
