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

package com.github.huifer.view.redis.boot.runner;

import java.util.List;

import com.github.huifer.view.redis.cache.SpringRedisProperties;
import com.github.huifer.view.redis.utils.SingletData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class ViewRedisRunner {

	private static final Logger log = LoggerFactory.getLogger(ViewRedisRunner.class);

	private final ApplicationContext context;

	public ViewRedisRunner(ApplicationContext context) {
		this.context = context;
	}

	@Bean
	@ConditionalOnMissingBean({RedisProperties.class, RedisConnectionFactory.class})
	public ApplicationRunner runner() {
		return args -> {
			RedisProperties redisProperties = context.getBean(RedisProperties.class);

			if (log.isDebugEnabled()) {
				log.debug("开始设置 RedisProperties");

			}

			RedisConnectionFactory redisConnectionFactory = context.getBean(RedisConnectionFactory.class);

			SpringRedisProperties springRedisProperties = new SpringRedisProperties();
			springRedisProperties.setProperties(redisProperties);
			springRedisProperties.setRedisConnectionFactory(redisConnectionFactory);

			SingletData.setSpringRedisProperties(springRedisProperties);

			// 设置cluster
			RedisProperties.Cluster cluster = redisProperties.getCluster();
			if (cluster != null) {
				List<String> nodes = cluster.getNodes();
				if (!nodes.isEmpty()) {
					SingletData.setCluster(cluster);
					RedisClusterConnection clusterConnection = redisConnectionFactory.getClusterConnection();
					SingletData.setClusterConnection(clusterConnection);
				}
			}

		};
	}

}
