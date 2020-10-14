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

package com.github.huifer.view.redis.sample;

import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.boot.ann.EnableViewRedis;
import com.github.huifer.view.redis.impl.RvRedisConnectionFactoryImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableViewRedis
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

		RvRedisConnectionFactory redisConnectionFactory = new RvRedisConnectionFactoryImpl();
		RedisTemplate redisTemplate = redisConnectionFactory.factory(null);
		redisTemplate.opsForValue().set("111", "222");

	}

}
