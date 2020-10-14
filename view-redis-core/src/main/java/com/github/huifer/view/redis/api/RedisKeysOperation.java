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

/** redis key 的操作接口 */
public interface RedisKeysOperation {

	/**
	 * redis 节点信息
	 *
	 * @param config redis 连接配置
	 * @param keyRegion key的正则表达式
	 * @return redis key 信息
	 */
	List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion);
}
