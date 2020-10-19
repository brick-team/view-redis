package com.github.huifer.view.redis.sample.boot;


import java.util.Map;

import com.github.huifer.view.redis.servlet.enable.EnableViewRedisServlet;
import com.google.gson.Gson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableViewRedisServlet
public class RedisServletApp {
	public static void main(String[] args) {
		SpringApplication.run(RedisServletApp.class, args);

			String s = "{\n" +
					"  \"key\": \"\"\n" +
					"}";

			Gson gson = new Gson();
			Map map = gson.fromJson(s, Map.class);
			System.out.println();
	}

}
