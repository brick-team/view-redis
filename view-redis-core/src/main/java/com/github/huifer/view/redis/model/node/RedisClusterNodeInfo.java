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

package com.github.huifer.view.redis.model.node;

import com.github.huifer.view.redis.model.info.RedisCliInfoClients;
import com.github.huifer.view.redis.model.info.RedisCliInfoCluster;
import com.github.huifer.view.redis.model.info.RedisCliInfoCpu;
import com.github.huifer.view.redis.model.info.RedisCliInfoKeyspace;
import com.github.huifer.view.redis.model.info.RedisCliInfoMemory;
import com.github.huifer.view.redis.model.info.RedisCliInfoPersistence;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.info.RedisCliInfoStats;

public class RedisClusterNodeInfo {
	private RedisCliInfoServer server;

	private RedisCliInfoClients clients;

	private RedisCliInfoMemory memory;

	private RedisCliInfoPersistence persistence;

	private RedisCliInfoStats stats;

	private RedisCliInfoCpu cpu;

	private RedisCliInfoCluster cluster;

	private RedisCliInfoKeyspace keyspace;

	public RedisCliInfoServer getServer() {
		return server;
	}

	public void setServer(RedisCliInfoServer server) {
		this.server = server;
	}

	public RedisCliInfoClients getClients() {
		return clients;
	}

	public void setClients(RedisCliInfoClients clients) {
		this.clients = clients;
	}

	public RedisCliInfoMemory getMemory() {
		return memory;
	}

	public void setMemory(RedisCliInfoMemory memory) {
		this.memory = memory;
	}

	public RedisCliInfoPersistence getPersistence() {
		return persistence;
	}

	public void setPersistence(RedisCliInfoPersistence persistence) {
		this.persistence = persistence;
	}

	public RedisCliInfoStats getStats() {
		return stats;
	}

	public void setStats(RedisCliInfoStats stats) {
		this.stats = stats;
	}

	public RedisCliInfoCpu getCpu() {
		return cpu;
	}

	public void setCpu(RedisCliInfoCpu cpu) {
		this.cpu = cpu;
	}

	public RedisCliInfoCluster getCluster() {
		return cluster;
	}

	public void setCluster(RedisCliInfoCluster cluster) {
		this.cluster = cluster;
	}

	public RedisCliInfoKeyspace getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(RedisCliInfoKeyspace keyspace) {
		this.keyspace = keyspace;
	}
}
