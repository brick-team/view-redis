package com.github.huifer.view.redis.sample.boot;


import java.util.Map;

import com.github.huifer.view.redis.api.RedisHashOperation;
import com.github.huifer.view.redis.api.RedisZSetOperation;
import com.github.huifer.view.redis.impl.RedisHashOperationImpl;
import com.github.huifer.view.redis.impl.RedisZSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.servlet.enable.EnableViewRedisServlet;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@EnableViewRedisServlet
public class RedisServletApp {
	@Autowired
	private StringRedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RedisServletApp.class, args);

		String s = "{\n" +
				"  \"key\": \"\"\n" +
				"}";

		Gson gson = new Gson();
		Map map = gson.fromJson(s, Map.class);
		System.out.println();
	}

//	RedisZSetOperation setOperation = new RedisZSetOperationImpl();
//
//	RedisHashOperation redisHashOperation = new RedisHashOperationImpl();
//	@Bean
//	public ApplicationRunner applicationRunner() {
//		return args -> {
//			RedisConnectionConfig currConfig = SingletData.currConfig;
////			setOperation.removeOldSaveNew(currConfig, "234", "zzzz", "321", 321);
//			redisHashOperation.upAndSave(currConfig, "bbb", "abcd", "张三", "da");
//		};
//	}

}
