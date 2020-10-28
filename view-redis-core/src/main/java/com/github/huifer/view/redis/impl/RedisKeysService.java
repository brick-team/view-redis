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

package com.github.huifer.view.redis.impl;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.huifer.view.redis.api.RedisKeysOperation;
import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.RedisDataType;
import com.github.huifer.view.redis.model.RedisKeyInfo;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisKeysService implements RedisKeysOperation {

	RvRedisConnectionFactory factory = new RvRedisConnectionFactoryImpl();

	@Override
	public List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		RedisConnection connection =
				RedisConnectionUtils.getConnection(redisTemplate.getConnectionFactory());

		// 通过 key region 进行搜索
		Set<byte[]> keys = connection.keys(keyRegion.getBytes());
		StringRedisSerializer stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);

		List<RedisKeyInfo> result = new ArrayList<>();

		// 数据封装
		for (byte[] key : keys) {
			String keyName = stringSerializer.deserialize(key);

			Long expire = redisTemplate.getExpire(keyName);

			DataType type = connection.type(key);
			RedisDataType redisDataType = RedisDataType.valueOf(type.name());
			RedisKeyInfo row = new RedisKeyInfo(keyName, redisDataType, expire);
			// 从缓存中拿出key的value
			result.add(row);
		}

		return result;
	}

	private List<RedisKeyInfo> convert(RedisConnection connection, Set<byte[]> keys) {
		StringRedisSerializer stringSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);
		return keys.stream()
				.map(
						s -> {
							String keyName = stringSerializer.deserialize(s);
							DataType type = connection.type(s);
							return new RedisKeyInfo(keyName, RedisDataType.valueOf(type.name()));
						})
				.collect(Collectors.toList());
	}

	@Override
	public Boolean del(RedisConnectionConfig config, String key) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		return redisTemplate.delete(key);
	}

	@Override
	public Boolean expire(RedisConnectionConfig config, String key, long expire) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public void rename(RedisConnectionConfig config, String on, String nn) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		redisTemplate.rename(on, nn);
	}

	@Override
	public Long deleteKeyInBatch(RedisConnectionConfig config, List<String> keys) {
		RedisTemplate redisTemplate = this.factory.factory(config);
		return redisTemplate.delete(keys);
	}
}
