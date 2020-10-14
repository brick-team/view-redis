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
import java.util.Properties;

import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.RedisInfo;
import com.github.huifer.view.redis.model.info.RedisCliInfoClients;
import com.github.huifer.view.redis.model.info.RedisCliInfoCluster;
import com.github.huifer.view.redis.model.info.RedisCliInfoCpu;
import com.github.huifer.view.redis.model.info.RedisCliInfoKeyspace;
import com.github.huifer.view.redis.model.info.RedisCliInfoMemory;
import com.github.huifer.view.redis.model.info.RedisCliInfoPersistence;
import com.github.huifer.view.redis.model.info.RedisCliInfoReplication;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.info.RedisCliInfoStats;

/** redis 服务相关信息 */
public interface IRedisServerInfo {
	/**
	 * 版本
	 *
	 * @return
	 */
	String version(RedisConnectionConfig config);

	/**
	 * 已使用内存
	 *
	 * @return
	 */
	long useMemory(RedisConnectionConfig config);

	/**
	 * 客户端数量
	 *
	 * @return
	 */
	int client(RedisConnectionConfig config);

	/**
	 * 已执行的命令
	 *
	 * @return
	 */
	long execSize(RedisConnectionConfig config);

	/**
	 * 运行时间
	 *
	 * @return
	 */
	long runTime(RedisConnectionConfig config);

	/**
	 * 服务器信息
	 *
	 * @return
	 */
	Properties conf(RedisConnectionConfig config);

	RedisCliInfoServer server(RedisConnectionConfig config);

	RedisCliInfoClients clients(RedisConnectionConfig config);

	RedisCliInfoMemory memory(RedisConnectionConfig config);

	RedisCliInfoPersistence persistence(RedisConnectionConfig config);

	RedisCliInfoStats stats(RedisConnectionConfig config);

	RedisCliInfoReplication replication(RedisConnectionConfig config);

	RedisCliInfoCpu cpu(RedisConnectionConfig config);

	RedisCliInfoCluster cluster(RedisConnectionConfig config);

	List<RedisCliInfoKeyspace> keyspace(RedisConnectionConfig config);

	RedisInfo info(RedisConnectionConfig config);
}
