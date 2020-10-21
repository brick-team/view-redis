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


import com.github.huifer.view.redis.model.RedisConnectionConfig;

/**
 *  redis 的 string 数据类型操作
 * @author huifer
 * */
public interface RedisStringOperation extends IRedisOperationLabel {
	/**
	 * 添加 键值
	 * @param config redis 连接配置
	 * @param k 键
	 * @param v 值
	 */
	void add(RedisConnectionConfig config, String k, String v);

	/**
	 * 获取 key 的 value
	 * @param config redis 连接配置
	 * @param k 键
	 * @return 值
	 */
	Object get(RedisConnectionConfig config, String k);

	/**
	 * 删除 key
	 * @param config redis 连接配置
	 * @param k 键
	 */
	void delete(RedisConnectionConfig config, String k);

	/**
	 * 修改 key 的 value 值
	 * @param config redis 连接配置
	 * @param k 键
	 * @param v 值
	 */
	void update(RedisConnectionConfig config, String k, String v);
}
