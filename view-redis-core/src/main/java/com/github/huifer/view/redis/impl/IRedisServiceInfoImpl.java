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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.github.huifer.view.redis.api.IRedisServerInfo;
import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.convert.RedisPropertiesConvertObj;
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

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

public class IRedisServiceInfoImpl implements IRedisServerInfo {
	public static final String REDIS_VERSION = "redis_version";

	public static final String USED_MEMORY = "used_memory";

	public static final String CONNECTED_CLIENTS = "connected_clients";

	public static final String TOTAL_COMMANDS_PROCESSED = "total_commands_processed";

	public static final String UPTIME_IN_SECONDS = "uptime_in_seconds";

	RvRedisConnectionFactory redisConnectionCacheFactory = new RvRedisConnectionFactoryImpl();

	/**
	 * 版本
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public String version(RedisConnectionConfig config) {
		Properties info = redisInfo(config);
		return String.valueOf(Objects.requireNonNull(info).get(REDIS_VERSION));
	}

	/**
	 * 已使用内存
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public long useMemory(RedisConnectionConfig config) {
		Properties info = redisInfo(config);
		Object o = Objects.requireNonNull(info).get(USED_MEMORY);
		if (o == null) {
			return 0;
		}
		return Long.parseLong(String.valueOf(o));
	}

	/**
	 * 客户端数量
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public int client(RedisConnectionConfig config) {
		Properties info = redisInfo(config);
		Object o = Objects.requireNonNull(info).get(CONNECTED_CLIENTS);
		if (o == null) {
			return 0;
		}
		return Integer.parseInt(String.valueOf(o));
	}

	/**
	 * 已执行的命令
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public long execSize(RedisConnectionConfig config) {
		Properties info = redisInfo(config);
		Object o = Objects.requireNonNull(info).get(TOTAL_COMMANDS_PROCESSED);
		if (o == null) {
			return 0;
		}
		return Long.parseLong(
				String.valueOf(o));
	}

	private Properties redisInfo(RedisConnectionConfig config) {
		RedisTemplate<String, String> factory = redisConnectionCacheFactory.factory(config);
		return getConnection(factory).info();
	}

	/**
	 * 运行时间
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public long runTime(RedisConnectionConfig config) {
		Properties info = redisInfo(config);
		Object o = Objects.requireNonNull(info).get(UPTIME_IN_SECONDS);
		if (o == null) {
			return 0;
		}
		return Long.parseLong(String.valueOf(o));
	}

	/**
	 * 服务器信息
	 *
	 * @param config
	 * @return
	 */
	@SuppressWarnings("all")
	@Override
	public Properties conf(RedisConnectionConfig config) {
		return redisInfo(config);
	}

	@SuppressWarnings("all")
	@Override
	public RedisCliInfoServer server(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties server = connection.info("server");


		RedisCliInfoServer redisCliInfoServer = new RedisCliInfoServer();
		assert server != null;
		connection.close();
		return RedisPropertiesConvertObj.server(server);
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoClients clients(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties clients = connection.info("clients");
		RedisCliInfoClients redisCliInfoClients = new RedisCliInfoClients();
		assert clients != null;
		connection.close();
		return RedisPropertiesConvertObj.clients(clients);

	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoMemory memory(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties memory = connection.info("memory");
		RedisCliInfoMemory redisCliInfoMemory = new RedisCliInfoMemory();
		assert memory != null;
		connection.close();
		return RedisPropertiesConvertObj.memory(memory);
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoPersistence persistence(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties persistence = connection.info("persistence");
		RedisCliInfoPersistence redisCliInfoPersistence = new RedisCliInfoPersistence();
		assert persistence != null;
		connection.close();
		return RedisPropertiesConvertObj.persistence(persistence);
	}

	private RedisConnection getConnection(RedisTemplate factory) {
		return Objects.requireNonNull(factory.getConnectionFactory()).getConnection();
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoStats stats(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties stats = connection.info("stats");
		RedisCliInfoStats redisCliInfoStats = new RedisCliInfoStats();
		assert stats != null;
		connection.close();
		return RedisPropertiesConvertObj.stats(stats);
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoReplication replication(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties replication = connection.info("replication");
		RedisCliInfoReplication redisCliInfoReplication = new RedisCliInfoReplication();
		assert replication != null;
		connection.close();
		return RedisPropertiesConvertObj.replication(replication);
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoCpu cpu(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties cpu = connection.info("cpu");
		RedisCliInfoCpu redisCliInfoCpu = new RedisCliInfoCpu();
		assert cpu != null;
		connection.close();
		return RedisPropertiesConvertObj.cpu(cpu);
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoCluster cluster(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties cluster = connection.info("cluster");
		RedisCliInfoCluster redisCliInfoCluster = new RedisCliInfoCluster();
		assert cluster != null;
		redisCliInfoCluster.setClusterEnabled(cluster.getProperty("cluster_enabled"));
		connection.close();
		return redisCliInfoCluster;
	}

	@Override
	@SuppressWarnings("all")
	public List<RedisCliInfoKeyspace> keyspace(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties replication = connection.info("keyspace");
		List<RedisCliInfoKeyspace> res = new ArrayList<>();
		replication.forEach(
				(k, v) -> {
					RedisCliInfoKeyspace redisCliInfoKeyspace = new RedisCliInfoKeyspace();
					redisCliInfoKeyspace.setDbIndex(String.valueOf(k));
					String[] split = String.valueOf(v).split(",");

					RedisCliInfoKeyspace.KeyInfo keyInfo = new RedisCliInfoKeyspace.KeyInfo();
					for (String s : split) {
						String[] split1 = s.split("=");
						if (split1[0].equals("keys")) {
							keyInfo.setKeys(split1[1]);
						}
						if (split1[0].equals("expires")) {
							keyInfo.setExpires(split1[1]);
						}
						if (split1[0].equals("avg_ttl")) {
							keyInfo.setAvgTtl(split1[1]);
						}
					}
					redisCliInfoKeyspace.setKeyInfo(keyInfo);
					res.add(redisCliInfoKeyspace);
				});
		connection.close();
		return res;
	}

	@Override
	public RedisInfo info(RedisConnectionConfig config) {
		RedisInfo redisInfo = new RedisInfo();
		redisInfo.setClients(clients(config));
		redisInfo.setCluster(cluster(config));
		redisInfo.setCpu(cpu(config));
		redisInfo.setKeyspace(keyspace(config));
		redisInfo.setMemory(memory(config));
		redisInfo.setPersistence(persistence(config));
		redisInfo.setReplication(replication(config));
		redisInfo.setServer(server(config));
		redisInfo.setStats(stats(config));

		return redisInfo;
	}
}
