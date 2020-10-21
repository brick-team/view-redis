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

/**
 * redis 服务相关信息
 * @author huifer
 * */
public interface IRedisServerInfo {
	/**
	 * 版本
	 *
	 * @return 版本
	 */
	String version(RedisConnectionConfig config);

	/**
	 * 已使用内存
	 *
	 * @return 已使用内存
	 */
	long useMemory(RedisConnectionConfig config);

	/**
	 * 客户端数量
	 *
	 * @return 客户端数量
	 */
	int client(RedisConnectionConfig config);

	/**
	 * 已执行的命令
	 *
	 * @return 已执行的命令
	 */
	long execSize(RedisConnectionConfig config);

	/**
	 * 运行时间
	 *
	 * @return 运行时间
	 */
	long runTime(RedisConnectionConfig config);

	/**
	 * 服务器信息
	 *
	 * @return 服务器信息
	 */
	Properties conf(RedisConnectionConfig config);

	/**
	 * 获取 server 命令返回结果
	 * @param config redis 连接配置
	 * @return server 命令返回结果
	 */
	RedisCliInfoServer server(RedisConnectionConfig config);

	/**
	 * 获取 clients 命令返回结果
	 * @param config redis 连接配置
	 * @return clients 命令返回结果
	 */
	RedisCliInfoClients clients(RedisConnectionConfig config);

	/**
	 * 获取 memory 命令返回结果
	 * @param config redis 连接配置
	 * @return memory 命令返回结果
	 */
	RedisCliInfoMemory memory(RedisConnectionConfig config);

	/**
	 * 获取 persistence 命令返回结果
	 * @param config redis 连接配置
	 * @return persistence 命令返回结果
	 */
	RedisCliInfoPersistence persistence(RedisConnectionConfig config);

	/**
	 * 获取 stats 命令返回结果
	 * @param config redis 连接配置
	 * @return stats 命令返回结果
	 */
	RedisCliInfoStats stats(RedisConnectionConfig config);

	/**
	 * 获取 replication 命令返回结果
	 * @param config redis 连接配置
	 * @return replication 命令返回结果
	 */
	RedisCliInfoReplication replication(RedisConnectionConfig config);

	/**
	 * 获取 cpu 命令返回结果
	 * @param config redis 连接配置
	 * @return cpu 命令返回结果
	 */
	RedisCliInfoCpu cpu(RedisConnectionConfig config);

	/**
	 * 获取 cluster 命令返回结果
	 * @param config redis 连接配置
	 * @return cluster 命令返回结果
	 */
	RedisCliInfoCluster cluster(RedisConnectionConfig config);

	/**
	 * 获取 keyspace 命令返回结果
	 * @param config redis 连接配置
	 * @return keyspace 命令返回结果
	 */
	List<RedisCliInfoKeyspace> keyspace(RedisConnectionConfig config);

	/**
	 * 获取 info 命令返回结果
	 * @param config redis 连接配置
	 * @return info 命令返回结果
	 */
	RedisInfo info(RedisConnectionConfig config);
}
