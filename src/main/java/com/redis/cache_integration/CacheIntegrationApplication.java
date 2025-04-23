package com.redis.cache_integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
public class CacheIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheIntegrationApplication.class, args);
	}

}
