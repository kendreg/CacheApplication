package com.gk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CustomercrudcacheexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomercrudcacheexApplication.class, args);
	}

}
