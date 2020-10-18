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

package com.github.huifer.view.redis.utils;

import com.github.huifer.view.redis.cache.SpringRedisProperties;
import com.github.huifer.view.redis.model.RedisConnectionConfig;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class SingletData {
	public static SpringRedisProperties springRedisProperties;


	public static RedisConnectionConfig currConfig;

	public static RedisTemplate redisTemplate;

	public static RedisConnectionConfig getCurrConfig() {
		return currConfig;
	}

	public static void setCurrConfig(RedisConnectionConfig param) {
		if (currConfig == null) {
			currConfig = param;
		}
	}

	public static RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public static SpringRedisProperties getSpringRedisProperties() {
		if (SingletData.springRedisProperties != null) {
			return SingletData.springRedisProperties;
		}
		else {
			throw new RuntimeException("spring redis properties is null");
		}
	}

	public static void setSpringRedisProperties(SpringRedisProperties param) {
		if (SingletData.springRedisProperties == null && param != null) {

			RedisConnectionFactory redisConnectionFactory = param.getRedisConnectionFactory();

			if (redisConnectionFactory == null) {
				throw new RuntimeException("RedisConnectionFactory is null");
			}

			RedisTemplate<Object, Object> template = initRedisTemplate(redisConnectionFactory);


			initRedisConfig(param);


			SingletData.redisTemplate = template;
			SingletData.springRedisProperties = param;
		}
	}

	private static void initRedisConfig(SpringRedisProperties param) {
		RedisConnectionConfig redisConnectionConfig = new RedisConnectionConfig();
		redisConnectionConfig.setDbIndex(param.getProperties().getDatabase());
		redisConnectionConfig.setHost(param.getProperties().getHost());
		redisConnectionConfig.setPort(param.getProperties().getPort());
		redisConnectionConfig.setPwd(param.getProperties().getPassword());
		SingletData.currConfig = redisConnectionConfig;
		SingletData.setCurrConfig(redisConnectionConfig);

	}

	private static RedisTemplate<Object, Object> initRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer());
		template.setDefaultSerializer(new StringRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		template.afterPropertiesSet();
		return template;
	}

}
