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
import com.github.huifer.view.redis.model.RedisKeyInfo;

/**
 *  redis key 的操作接口
 *
 * @author huifer
 * */
public interface RedisKeysOperation {

	/**
	 * redis 节点信息
	 *
	 * @param config redis 连接配置
	 * @param keyRegion key的正则表达式
	 * @return redis key 信息
	 */
	List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion);

	/**
	 * 删除 key
	 * @param config redis 连接配置
	 * @param key key
	 * @return true: 删除成功, false: 删除失败
	 */
	Boolean del(RedisConnectionConfig config, String key);

	/**
	 * 设置过期时间
	 * @param config redis 连接配置
	 * @param key key
	 * @param expire 过期的秒数
	 * @return true: 设置成功, false: 设置失败
	 */
	Boolean expire(RedisConnectionConfig config, String key, long expire);

	/**
	 * 重命名
	 * @param config redis 连接配置
	 * @param on 历史 key name
	 * @param nn 新 key name
	 */
	void rename(RedisConnectionConfig config, String on, String nn);

	/**
	 * 批量删除key
	 * @param config  redis 连接配置
	 * @param keys keys
	 * @return
	 */
	Long deleteKeyInBatch(RedisConnectionConfig config, List<String> keys);
}
