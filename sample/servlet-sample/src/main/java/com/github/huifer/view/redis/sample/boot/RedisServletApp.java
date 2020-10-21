/*
 *
 * Copyright 2020 HuiFer All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
