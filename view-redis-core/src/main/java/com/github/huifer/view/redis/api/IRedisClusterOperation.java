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

import com.github.huifer.view.redis.model.cluster.ClusterListInfo;
import com.github.huifer.view.redis.model.cluster.Range;

import org.springframework.data.redis.connection.RedisClusterNode;

/**
 * redis cluster operation
 *
 *
 * 1. 获取节点信息
 * 2. ping 节点
 * @author huifer
 */
public interface IRedisClusterOperation {
	/**
	 * 获取 redis 集群节点信息
	 * @return
	 */
	Iterable<RedisClusterNode> clusterGetNodes();


	/**
	 * ping node
	 * @param node node
	 * @return ping / pong
	 */
	String ping(RedisClusterNode node);


	/**
	 * 将 cluster node 中的 range 解析
	 * @param node node
	 * @return range
	 */
	Range range(RedisClusterNode node);

	/**
	 * 项目内部的集群信息对象
	 * @return 集群信息
	 */
	List<ClusterListInfo> clusterInfos();

	Properties info(String redisNodeId, String cmd);

}
