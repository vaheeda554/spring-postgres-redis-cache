package com.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.redis.entity")
@EnableCaching

public class SpringPostgreRedisExample1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgreRedisExample1Application.class, args);
	}

}
