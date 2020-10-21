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

import java.util.List;

import com.github.huifer.view.redis.model.RedisConnectionConfig;

/**
 *  redis 的 list 数据类型操作
 *
 * @author huifer
 * */
public interface RedisListOperation extends IRedisOperationLabel {
	/**
	 * 添加list键值
	 * @param conf redis 连接配置
	 * @param k 键
	 * @param v 值
	 */
	void add(RedisConnectionConfig conf, String k, String v);

	/**
	 * redis 中 list 的数据值
	 * @param conf redis 连接配置
	 * @param k 键
	 * @return redis 中 list 的数据值
	 */
	List get(RedisConnectionConfig conf, String k);

	/**
	 *
	 * @param conf redis 连接配置
	 * @param k 键
	 * @param start 开始标记
	 * @param end 结束标记
	 * @return redis 中 list 的数据值
	 */
	List get(RedisConnectionConfig conf, String k, long start, long end);

	/**
	 * 更新数据
	 *
	 * @param conf redis 连接配置
	 * @param k 键
	 * @param ov 老的数据值
	 * @param nv 新的数据值
	 */
	void update(RedisConnectionConfig conf, String k, String ov, String nv);

	/**
	 * 删除这个key的第row行数据
	 *
	 * @param config redis 连接配置
	 * @param k 键
	 * @param row 行号
	 */
	void removeByRow(RedisConnectionConfig config, String k, int row);

	/**
	 * 删除整个key
	 *
	 * @param conf redis 连接配置
	 * @param k 键
	 */
	void del(RedisConnectionConfig conf, String k);

	/**
	 * 获取 list 的大小
	 * @param conf redis 连接配置
	 * @param k 键
	 * @return 获取 list 的大小
	 */
	Long size(RedisConnectionConfig conf, String k);
}
