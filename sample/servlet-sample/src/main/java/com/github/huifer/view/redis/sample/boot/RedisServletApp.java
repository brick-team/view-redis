package com.github.huifer.view.redis.sample.boot;


import java.util.Map;

import com.github.huifer.view.redis.servlet.enable.EnableViewRedisServlet;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableViewRedisServlet
public class RedisServletApp {
	@Autowired
	private RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RedisServletApp.class, args);

		String s = "{\n" +
				"  \"key\": \"\"\n" +
				"}";

		Gson gson = new Gson();
		Map map = gson.fromJson(s, Map.class);
		System.out.println();
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
		};
	}

}
