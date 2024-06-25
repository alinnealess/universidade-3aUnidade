package com.imd.universidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.imd.universidade")
public class UniversidadeApplication {

	public static void main(String[] args) {

		SpringApplication.run(UniversidadeApplication.class, args);
	}

}
