package com.ruanzerah.skyio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SkyioApplication {
	public static void main(String[] args) {
		SpringApplication.run(SkyioApplication.class, args);
	}

}
