package com.ainal.apps.wise_spends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:wise_spends.properties")
public class WiseSpendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiseSpendsApplication.class, args);
	}

}
