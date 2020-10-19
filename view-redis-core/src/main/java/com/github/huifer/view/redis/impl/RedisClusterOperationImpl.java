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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.github.huifer.view.redis.api.RvRedisConnectionFactory;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.ViewRedisClusterNode;
import com.github.huifer.view.redis.model.node.RedisClusterNodeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.core.ClusterOperations;

public class RedisClusterOperationImpl {
	RvRedisConnectionFactory factory = new RvRedisConnectionFactoryImpl();

	@Autowired
	private JedisClusterConnection clusterConnection;


	void demo(RedisConnectionConfig config) {
		ClusterOperations clusterOperations = factory.factory(config).opsForCluster();

	}


	/**
	 * 集群信息
	 * @param clusterConnection
	 * @return
	 */
	Properties info(RedisClusterConnection clusterConnection) {
		return clusterConnection.info();
	}

	List<ViewRedisClusterNode> nodes(RedisClusterConnection clusterConnection) {
		Iterable<RedisClusterNode> redisClusterNodes = clusterConnection.clusterGetNodes();
		List<ViewRedisClusterNode> res = new ArrayList<>(10);
		for (RedisClusterNode redisClusterNode : redisClusterNodes) {
			String host = redisClusterNode.getHost();
			String id = redisClusterNode.getId();
			Integer port = redisClusterNode.getPort();
			RedisNode.NodeType type = redisClusterNode.getType();


			ViewRedisClusterNode viewRedisClusterNode = new ViewRedisClusterNode();
			viewRedisClusterNode.setId(id);
			viewRedisClusterNode.setHost(host);
			viewRedisClusterNode.setPort(port);
			viewRedisClusterNode.setNodeType(type);
			res.add(viewRedisClusterNode);
		}
		return res;
	}

	Map<String, RedisClusterNodeInfo> nodesInfo() {
		Properties info = clusterConnection.info();
		Enumeration<Object> keys = info.keys();
		Map<String, RedisClusterNodeInfo> res = new HashMap<>(10);

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			// todo: 通过 key 转换成  RedisClusterNodeInfo
			Properties o = (Properties) info.get(key);
			res.put(String.valueOf(key), null);
		}
		return res;
	}

}
