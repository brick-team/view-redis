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

import java.util.Map;

import com.github.huifer.view.redis.api.RedisHashOperation;
import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.model.RedisConnectionConfig;

public class RedisHashOperationImpl implements RedisHashOperation {

	RvRedisConnectionFactory factory = new RvRedisConnectionFactoryImpl();

	@Override
	public void add(RedisConnectionConfig config, String k, String field, String v) {
		factory.factory(config).opsForHash().put(k, field, v);
	}

	@Override
	public Map get(RedisConnectionConfig config, String k) {
		return factory.factory(config).opsForHash().entries(k);
	}

	@Override
	public void del(RedisConnectionConfig config, String k, String field) {
		factory.factory(config).opsForHash().delete(k, field);
	}

	@Override
	public void update(RedisConnectionConfig config, String k, String field, String v) {
		factory.factory(config).opsForHash().put(k, field, v);
	}


	@Override
	public void upAndSave(RedisConnectionConfig config, String k, String oldField, String newField, String v) {
		this.del(config, k, oldField);
		this.add(config, k, newField, v);
	}
}