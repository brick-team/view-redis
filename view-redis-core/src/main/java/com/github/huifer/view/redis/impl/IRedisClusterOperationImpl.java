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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.github.huifer.view.redis.api.IRedisClusterOperation;
import com.github.huifer.view.redis.api.RvRedisClusterConnectionFactory;
import com.github.huifer.view.redis.model.cluster.ClusterListInfo;
import com.github.huifer.view.redis.model.cluster.Range;

import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;

/**
 *
 *
 * @author huifer
 */
public class IRedisClusterOperationImpl implements IRedisClusterOperation {
	public static final List<String> INFO_KEYS = new ArrayList<>();


	private final Map<String, RedisClusterNode> nodeMap = new HashMap<>(6);

	RvRedisClusterConnectionFactory factory = new RvRedisClusterConnectionFactoryImpl();

	@Override
	public Iterable<RedisClusterNode> clusterGetNodes() {
		RedisClusterConnection factory = this.factory.factory();
		Iterable<RedisClusterNode> redisClusterNodes = factory.clusterGetNodes();
		for (RedisClusterNode redisClusterNode : redisClusterNodes) {
			nodeMap.put(redisClusterNode.getId(), redisClusterNode);
		}
		return redisClusterNodes;
	}

	@Override
	public String ping(RedisClusterNode node) {
		RedisClusterConnection factory = this.factory.factory();
		return factory.ping(node);
	}

	@Override
	public Range range(RedisClusterNode node) {
		RedisClusterNode.SlotRange slotRange = node.getSlotRange();
		int[] slotsArray = slotRange.getSlotsArray();

		return new Range(slotsArray[0], slotsArray[slotsArray.length - 1]);
	}

	@Override
	public List<ClusterListInfo> clusterInfos() {
		RedisClusterConnection redisClusterConnection = this.factory.factory();
		Iterable<RedisClusterNode> redisClusterNodes = clusterGetNodes();
		List<ClusterListInfo> res = new ArrayList<>();
		for (RedisClusterNode redisClusterNode : redisClusterNodes) {
			ClusterListInfo clusterListInfo = new ClusterListInfo();

			clusterListInfo.setId(redisClusterNode.getId());
			clusterListInfo.setName(redisClusterNode.getName());
			clusterListInfo.setHost(redisClusterNode.getHost());
			clusterListInfo.setPort(redisClusterNode.getPort());
			clusterListInfo.setType(redisClusterNode.getType().name());
			clusterListInfo.setMasterId(redisClusterNode.getMasterId());
			clusterListInfo.setRange(this.range(redisClusterNode));

			RedisClusterNode.LinkState linkState = redisClusterNode.getLinkState();
			clusterListInfo.setLinkState(linkState.toString());
			Set<RedisClusterNode.Flag> flags = redisClusterNode.getFlags();
			StringBuilder sb = new StringBuilder(32);

			for (RedisClusterNode.Flag flag : flags) {
				sb.append(flag.getRaw() + ",");
			}
			String flagstr = sb.deleteCharAt(sb.length() - 1).toString();
			clusterListInfo.setFlags(flagstr);


			try {
				String ping = redisClusterConnection.ping(redisClusterNode);

				clusterListInfo.setPing(ping);
			}
			catch (Exception e) {
				clusterListInfo.setPing(e.getMessage());
			}
			res.add(clusterListInfo);
		}
		return res;
	}

	/**
	 * server
	 * clients
	 * memory
	 * persistence
	 * stats
	 * replication
	 * cpu
	 *  @param redisClusterNode
	 * @param infoKey
	 *
	 * @return
	 */
	private Properties info(RedisClusterNode redisClusterNode, String infoKey) {
		if (INFO_KEYS.contains(infoKey)) {
			RedisClusterConnection factory = this.factory.factory();
			return factory.info(redisClusterNode, infoKey);
		}
		return null;
	}

	static {
		INFO_KEYS.add("server");
		INFO_KEYS.add("clients");
		INFO_KEYS.add("memory");
		INFO_KEYS.add("persistence");
		INFO_KEYS.add("stats");
		INFO_KEYS.add("replication");
		INFO_KEYS.add("cpu");
	}
}
