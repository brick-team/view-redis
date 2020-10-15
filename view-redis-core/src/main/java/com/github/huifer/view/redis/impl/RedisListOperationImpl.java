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

import java.util.List;
import java.util.UUID;

import com.github.huifer.view.redis.api.RedisListOperation;
import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.model.RedisConnectionConfig;

import org.springframework.data.redis.core.ListOperations;

public class RedisListOperationImpl implements RedisListOperation {

	RvRedisConnectionFactory factory = new RvRedisConnectionFactoryImpl();

	@Override
	public void add(RedisConnectionConfig conf, String k, String v) {
		factory.factory(conf).opsForList().rightPush(k, v);
	}

	@Override
	public List get(RedisConnectionConfig conf, String k) {
		return factory.factory(conf).opsForList().range(k, 0, -1);
	}

	@Override
	public List get(RedisConnectionConfig conf, String k, long start, long end) {
		return factory.factory(conf).opsForList().range(k, start, end);
	}

	@Override
	public void update(RedisConnectionConfig conf, String k, String ov, String nv) {
		List list = get(conf, k);
		factory.factory(conf).opsForList().remove(k, list.size(), ov);
		add(conf, k, nv);
	}

	@Override
	public void del(RedisConnectionConfig conf, String k) {
		factory.factory(conf).opsForList().remove(k, 0, -1);
	}

	/**
	 * 删除这个key的第row行数据
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param row 行号
	 */
	@Override
	public void removeByRow(RedisConnectionConfig config, String k, int row) {
		ListOperations listOperations = factory.factory(config).opsForList();
		UUID uuid = UUID.randomUUID();
		listOperations.set(k, row, "__delete__" + uuid.toString());
		listOperations.remove(k, 0, "__delete__" + uuid.toString());
	}

	@Override
	public Long size(RedisConnectionConfig conf, String k) {
		ListOperations listOperations = factory.factory(conf).opsForList();
		return listOperations.size(k);
	}
}