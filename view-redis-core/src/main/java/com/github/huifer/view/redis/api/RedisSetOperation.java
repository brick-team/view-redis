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

import java.util.Collection;

import com.github.huifer.view.redis.model.RedisConnectionConfig;

/**
 *  redis 的 set 数据类型操作
 * @author huifer
 * */
public interface RedisSetOperation extends IRedisOperationLabel {

	/**
	 * 添加一个 set
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param v 值
	 */
	void add(RedisConnectionConfig config, String k, String v);

	/**
	 * 获取set数据值
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @return 值
	 */
	Collection get(RedisConnectionConfig config, String k);

	/**
	 * 修改一个键的老数据
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param ov 老数据
	 * @param nv 新数据
	 */
	void update(RedisConnectionConfig config, String k, String ov, String nv);

	/**
	 * 删除一个键下面的值
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param v 值
	 */
	void del(RedisConnectionConfig config, String k, String v);
}
