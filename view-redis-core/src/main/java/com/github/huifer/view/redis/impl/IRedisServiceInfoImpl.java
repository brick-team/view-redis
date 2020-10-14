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
		return Long.parseLong(String.valueOf(Objects.requireNonNull(info).get(USED_MEMORY)));
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
		return Integer.parseInt(String.valueOf(Objects.requireNonNull(info).get(CONNECTED_CLIENTS)));
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
		return Long.parseLong(
				String.valueOf(Objects.requireNonNull(info).get(TOTAL_COMMANDS_PROCESSED)));
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
		return Long.parseLong(String.valueOf(Objects.requireNonNull(info).get(UPTIME_IN_SECONDS)));
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
		redisCliInfoServer.setRedisVersion(server.getProperty("redis_version"));
		redisCliInfoServer.setRedisGitSha1(server.getProperty("redis_git_sha1"));
		redisCliInfoServer.setRedisGitDirty(server.getProperty("redis_git_dirty"));
		redisCliInfoServer.setRedisBuildId(server.getProperty("redis_build_id"));
		redisCliInfoServer.setRedisMode(server.getProperty("redis_mode"));
		redisCliInfoServer.setOs(server.getProperty("os"));
		redisCliInfoServer.setArchBits(server.getProperty("arch_bits"));
		redisCliInfoServer.setMultiplexingApi(server.getProperty("multiplexing_api"));
		redisCliInfoServer.setProcessId(server.getProperty("process_id"));
		redisCliInfoServer.setRunId(server.getProperty("run_id"));
		redisCliInfoServer.setTcpPort(server.getProperty("tcp_port"));
		redisCliInfoServer.setUptimeInSeconds(server.getProperty("uptime_in_seconds"));
		redisCliInfoServer.setUptimeInDays(server.getProperty("uptime_in_days"));
		redisCliInfoServer.setHz(server.getProperty("hz"));
		redisCliInfoServer.setLruClock(server.getProperty("lru_clock"));
		redisCliInfoServer.setExecutable(server.getProperty("executable"));
		redisCliInfoServer.setConfigFile(server.getProperty("config_file"));
		connection.close();
		return redisCliInfoServer;
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoClients clients(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties clients = connection.info("clients");
		RedisCliInfoClients redisCliInfoClients = new RedisCliInfoClients();
		assert clients != null;
		redisCliInfoClients.setConnectedClients(clients.getProperty("connected_clients"));
		redisCliInfoClients.setClientLongestOutputList(
				clients.getProperty("client_longest_output_list"));
		redisCliInfoClients.setClientBiggestInputBuf(clients.getProperty("client_biggest_input_buf"));
		redisCliInfoClients.setBlockedClients(clients.getProperty("blocked_clients"));
		connection.close();
		return redisCliInfoClients;
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoMemory memory(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties memory = connection.info("memory");
		RedisCliInfoMemory redisCliInfoMemory = new RedisCliInfoMemory();
		assert memory != null;
		redisCliInfoMemory.setUsedMemory(memory.getProperty("used_memory"));
		redisCliInfoMemory.setUsedMemoryHuman(memory.getProperty("used_memory_human"));
		redisCliInfoMemory.setUsedMemoryRss(memory.getProperty("used_memory_rss"));
		redisCliInfoMemory.setUsedMemoryRssHuman(memory.getProperty("used_memory_rss_human"));
		redisCliInfoMemory.setUsedMemoryPeak(memory.getProperty("used_memory_peak"));
		redisCliInfoMemory.setUsedMemoryPeakHuman(memory.getProperty("used_memory_peak_human"));
		redisCliInfoMemory.setTotalSystemMemory(memory.getProperty("total_system_memory"));
		redisCliInfoMemory.setTotalSystemMemoryHuman(memory.getProperty("total_system_memory_human"));
		redisCliInfoMemory.setUsedMemoryLua(memory.getProperty("used_memory_lua"));
		redisCliInfoMemory.setUsedMemoryLuaHuman(memory.getProperty("used_memory_lua_human"));
		redisCliInfoMemory.setMaxmemory(memory.getProperty("maxmemory"));
		redisCliInfoMemory.setMaxmemoryHuman(memory.getProperty("maxmemory_human"));
		redisCliInfoMemory.setMaxmemoryPolicy(memory.getProperty("maxmemory_policy"));
		redisCliInfoMemory.setMemFragmentationRatio(memory.getProperty("mem_fragmentation_ratio"));
		redisCliInfoMemory.setMemAllocator(memory.getProperty("mem_allocator"));
		connection.close();
		return redisCliInfoMemory;
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoPersistence persistence(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties persistence = connection.info("persistence");
		RedisCliInfoPersistence redisCliInfoPersistence = new RedisCliInfoPersistence();
		assert persistence != null;
		redisCliInfoPersistence.setLoading(persistence.getProperty("loading"));
		redisCliInfoPersistence.setRdbChangesSinceLastSave(
				persistence.getProperty("rdb_changes_since_last_save"));
		redisCliInfoPersistence.setRdbBgsaveInProgress(
				persistence.getProperty("rdb_bgsave_in_progress"));
		redisCliInfoPersistence.setRdbLastSaveTime(persistence.getProperty("rdb_last_save_time"));
		redisCliInfoPersistence.setRdbLastBgsaveStatus(
				persistence.getProperty("rdb_last_bgsave_status"));
		redisCliInfoPersistence.setRdbLastBgsaveTimeSec(
				persistence.getProperty("rdb_last_bgsave_time_sec"));
		redisCliInfoPersistence.setRdbCurrentBgsaveTimeSec(
				persistence.getProperty("rdb_current_bgsave_time_sec"));
		redisCliInfoPersistence.setAofEnabled(persistence.getProperty("aof_enabled"));
		redisCliInfoPersistence.setAofRewriteInProgress(
				persistence.getProperty("aof_rewrite_in_progress"));
		redisCliInfoPersistence.setAofRewriteScheduled(
				persistence.getProperty("aof_rewrite_scheduled"));
		redisCliInfoPersistence.setAofLastRewriteTimeSec(
				persistence.getProperty("aof_last_rewrite_time_sec"));
		redisCliInfoPersistence.setAofCurrentRewriteTimeSec(
				persistence.getProperty("aof_current_rewrite_time_sec"));
		redisCliInfoPersistence.setAofLastBgrewriteStatus(
				persistence.getProperty("aof_last_bgrewrite_status"));
		redisCliInfoPersistence.setAofLastWriteStatus(persistence.getProperty("aof_last_write_status"));
		connection.close();
		return redisCliInfoPersistence;
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
		redisCliInfoStats.setTotalConnectionsReceived(stats.getProperty("total_connections_received"));
		redisCliInfoStats.setTotalCommandsProcessed(stats.getProperty("total_commands_processed"));
		redisCliInfoStats.setInstantaneousOpsPerSec(stats.getProperty("instantaneous_ops_per_sec"));
		redisCliInfoStats.setTotalNetInputBytes(stats.getProperty("total_net_input_bytes"));
		redisCliInfoStats.setTotalNetOutputBytes(stats.getProperty("total_net_output_bytes"));
		redisCliInfoStats.setInstantaneousInputKbps(stats.getProperty("instantaneous_input_kbps"));
		redisCliInfoStats.setInstantaneousOutputKbps(stats.getProperty("instantaneous_output_kbps"));
		redisCliInfoStats.setRejectedConnections(stats.getProperty("rejected_connections"));
		redisCliInfoStats.setSyncFull(stats.getProperty("sync_full"));
		redisCliInfoStats.setSyncPartialOk(stats.getProperty("sync_partial_ok"));
		redisCliInfoStats.setSyncPartialErr(stats.getProperty("sync_partial_err"));
		redisCliInfoStats.setExpiredKeys(stats.getProperty("expired_keys"));
		redisCliInfoStats.setEvictedKeys(stats.getProperty("evicted_keys"));
		redisCliInfoStats.setKeyspaceHits(stats.getProperty("keyspace_hits"));
		redisCliInfoStats.setKeyspaceMisses(stats.getProperty("keyspace_misses"));
		redisCliInfoStats.setPubsubChannels(stats.getProperty("pubsub_channels"));
		redisCliInfoStats.setPubsubPatterns(stats.getProperty("pubsub_patterns"));
		redisCliInfoStats.setLatestForkUsec(stats.getProperty("latest_fork_usec"));
		redisCliInfoStats.setMigrateCachedSockets(stats.getProperty("migrate_cached_sockets"));
		connection.close();
		return redisCliInfoStats;
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoReplication replication(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties replication = connection.info("replication");
		RedisCliInfoReplication redisCliInfoReplication = new RedisCliInfoReplication();
		assert replication != null;
		redisCliInfoReplication.setRole(replication.getProperty("role"));
		redisCliInfoReplication.setConnectedSlaves(replication.getProperty("connected_slaves"));
		redisCliInfoReplication.setMasterReplOffset(replication.getProperty("master_repl_offset"));
		redisCliInfoReplication.setReplBacklogActive(replication.getProperty("repl_backlog_active"));
		redisCliInfoReplication.setReplBacklogSize(replication.getProperty("repl_backlog_size"));
		redisCliInfoReplication.setReplBacklogFirstByteOffset(
				replication.getProperty("repl_backlog_first_byte_offset"));
		redisCliInfoReplication.setReplBacklogHistlen(replication.getProperty("repl_backlog_histlen"));
		connection.close();
		return redisCliInfoReplication;
	}

	@Override
	@SuppressWarnings("all")
	public RedisCliInfoCpu cpu(RedisConnectionConfig config) {
		RedisTemplate factory = redisConnectionCacheFactory.factory(config);
		RedisConnection connection = getConnection(factory);
		Properties cpu = connection.info("cpu");
		RedisCliInfoCpu redisCliInfoCpu = new RedisCliInfoCpu();
		assert cpu != null;
		redisCliInfoCpu.setUsedCpuSys(cpu.getProperty("used_cpu_sys"));
		redisCliInfoCpu.setUsedCpuUser(cpu.getProperty("used_cpu_user"));
		redisCliInfoCpu.setUsedCpuSysChildren(cpu.getProperty("used_cpu_sys_children"));
		redisCliInfoCpu.setUsedCpuUserChildren(cpu.getProperty("used_cpu_user_children"));
		connection.close();
		return redisCliInfoCpu;
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
