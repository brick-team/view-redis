package com.github.huifer.view.redis.sample.servlet;


import com.github.huifer.view.redis.servlet.enable.EnableViewRedisServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableViewRedisServlet
public class RedisServletApp {
	public static void main(String[] args) {
		SpringApplication.run(RedisServletApp.class, args);
	}

}
