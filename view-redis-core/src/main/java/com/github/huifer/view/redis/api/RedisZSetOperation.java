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

package com.github.huifer.view.redis.api;

import java.util.Set;

import com.github.huifer.view.redis.model.RedisConnectionConfig;

/** redis 的 zset 数据类型操作 */
public interface RedisZSetOperation extends IRedisOperationLabel {

	/**
	 * zset 添加数据
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param score 分数
	 * @param member 值
	 */
	void add(RedisConnectionConfig config, String k, double score, String member);

	/**
	 * 删除一个元素
	 *
	 * @param config
	 * @param key 键
	 * @param member 值
	 */
	void del(RedisConnectionConfig config, String key, String member);

	/**
	 * 获取zset
	 *
	 * @param config
	 * @param key 键
	 * @return 数据
	 */
	Set get(RedisConnectionConfig config, String key);

	/**
	 * 更新一个元素
	 *
	 * @param config
	 * @param k 键
	 * @param score 分数
	 * @param member 值
	 */
	void update(RedisConnectionConfig config, String k, double score, String member);
}
