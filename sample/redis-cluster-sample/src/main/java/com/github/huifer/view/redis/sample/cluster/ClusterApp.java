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

package com.github.huifer.view.redis.sample.cluster;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author huifer
 */
@SpringBootApplication
@RestController
public class ClusterApp {


	@Autowired
	private StringRedisTemplate redisTemplate;


	public static void main(String[] args) {
		SpringApplication.run(ClusterApp.class, args);
	}

//	@Bean
//	public ApplicationRunner applicationRunner() {
//		return args -> {
//			RedisClusterConnection clusterConnection = redisTemplate.getConnectionFactory().getClusterConnection();
////			clusterConnection.lPush("a".getBytes(), "fff".getBytes());
//			Iterable<RedisClusterNode> nodes = clusterConnection.clusterGetNodes();
//			List<String> ss = new ArrayList<>();
//			for (RedisClusterNode node : nodes) {
//				Set<byte[]> keys = clusterConnection.keys(node, "*".getBytes());
//				if (node.getPort().equals(6382)) {
//
//					clusterConnection.shutdown(node);
//				}
//
//
//				for (byte[] key : keys) {
//					ss.add(new String(key, StandardCharsets.UTF_8));
//					DataType type = clusterConnection.type(key);
//					System.out.println(type);
//
//				}
//			}
//			System.out.println();
//
//		};
//	}

}
