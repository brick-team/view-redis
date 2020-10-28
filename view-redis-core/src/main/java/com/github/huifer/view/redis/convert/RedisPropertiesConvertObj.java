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

package com.github.huifer.view.redis.convert;

import java.util.Properties;

import com.github.huifer.view.redis.model.info.RedisCliInfoClients;
import com.github.huifer.view.redis.model.info.RedisCliInfoCluster;
import com.github.huifer.view.redis.model.info.RedisCliInfoCpu;
import com.github.huifer.view.redis.model.info.RedisCliInfoMemory;
import com.github.huifer.view.redis.model.info.RedisCliInfoPersistence;
import com.github.huifer.view.redis.model.info.RedisCliInfoReplication;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.info.RedisCliInfoStats;

/**
 *
 *
 * @author huifer
 */
public class RedisPropertiesConvertObj {
	public static RedisCliInfoCpu cpu(Properties cpu) {
		RedisCliInfoCpu redisCliInfoCpu = new RedisCliInfoCpu();
		redisCliInfoCpu.setUsedCpuSys(cpu.getProperty("used_cpu_sys"));
		redisCliInfoCpu.setUsedCpuUser(cpu.getProperty("used_cpu_user"));
		redisCliInfoCpu.setUsedCpuSysChildren(cpu.getProperty("used_cpu_sys_children"));
		redisCliInfoCpu.setUsedCpuUserChildren(cpu.getProperty("used_cpu_user_children"));
		return redisCliInfoCpu;
	}

	public static RedisCliInfoCluster cluster(Properties cluster) {
		RedisCliInfoCluster redisCliInfoCluster = new RedisCliInfoCluster();
		redisCliInfoCluster.setClusterEnabled(cluster.getProperty("cluster_enabled"));

		return redisCliInfoCluster;
	}

	public static RedisCliInfoClients clients(Properties clients) {
		RedisCliInfoClients redisCliInfoClients = new RedisCliInfoClients();
		redisCliInfoClients.setConnectedClients(clients.getProperty("connected_clients"));
		redisCliInfoClients.setClientLongestOutputList(
				clients.getProperty("client_longest_output_list"));
		redisCliInfoClients.setClientBiggestInputBuf(clients.getProperty("client_biggest_input_buf"));
		redisCliInfoClients.setBlockedClients(clients.getProperty("blocked_clients"));
		return redisCliInfoClients;
	}

	public static RedisCliInfoMemory memory(Properties memory) {
		RedisCliInfoMemory redisCliInfoMemory = new RedisCliInfoMemory();
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
		return redisCliInfoMemory;
	}


	@SuppressWarnings("all")
	public static RedisCliInfoPersistence persistence(Properties persistence) {
		RedisCliInfoPersistence redisCliInfoPersistence = new RedisCliInfoPersistence();
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
		return redisCliInfoPersistence;
	}


	public static RedisCliInfoStats stats(Properties stats) {
		RedisCliInfoStats redisCliInfoStats = new RedisCliInfoStats();
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
		return redisCliInfoStats;
	}


	public static RedisCliInfoReplication replication(Properties replication) {
		RedisCliInfoReplication redisCliInfoReplication = new RedisCliInfoReplication();
		redisCliInfoReplication.setRole(replication.getProperty("role"));
		redisCliInfoReplication.setConnectedSlaves(replication.getProperty("connected_slaves"));
		redisCliInfoReplication.setMasterReplOffset(replication.getProperty("master_repl_offset"));
		redisCliInfoReplication.setReplBacklogActive(replication.getProperty("repl_backlog_active"));
		redisCliInfoReplication.setReplBacklogSize(replication.getProperty("repl_backlog_size"));
		redisCliInfoReplication.setReplBacklogFirstByteOffset(
				replication.getProperty("repl_backlog_first_byte_offset"));
		redisCliInfoReplication.setReplBacklogHistlen(replication.getProperty("repl_backlog_histlen"));
		return redisCliInfoReplication;
	}

	public static RedisCliInfoServer server(Properties server) {
		RedisCliInfoServer redisCliInfoServer = new RedisCliInfoServer();
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
		return redisCliInfoServer;
	}
}
