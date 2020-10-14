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

package com.github.huifer.view.redis.boot.ctr;

import com.github.huifer.view.redis.api.IRedisServerInfo;
import com.github.huifer.view.redis.impl.IRedisServiceInfoImpl;
import com.github.huifer.view.redis.model.EasyRedisInfo;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.utils.SingletData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see IRedisServerInfo
 */
@RestController
@RequestMapping("/redis/service")
public class RedisServiceController {


	IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();

	RedisConnectionConfig config = SingletData.getCurrConfig();

	@GetMapping("/easy_info")
	public ResultVO easyInfo() {

		EasyRedisInfo easyRedisInfo = new EasyRedisInfo();
		easyRedisInfo.setVersion(redisServerInfo.version(config));
		easyRedisInfo.setUseMemory(redisServerInfo.useMemory(config));
		easyRedisInfo.setClient(redisServerInfo.client(config));
		easyRedisInfo.setExecSize(redisServerInfo.execSize(config));
		easyRedisInfo.setRunTime(redisServerInfo.runTime(config));

		return new ResultVO("OK", easyRedisInfo, 200);
	}

	@GetMapping("/server")
	public ResultVO server() {
		RedisCliInfoServer server = redisServerInfo.server(config);
		return new ResultVO("OK", server, 200);
	}

	@GetMapping("/clients")
	public ResultVO clients() {
		return new ResultVO("OK", redisServerInfo.clients(config), 200);
	}

	@GetMapping("/memory")
	public ResultVO memory() {
		return new ResultVO("OK", redisServerInfo.memory(config), 200);
	}

	@GetMapping("/persistence")
	public ResultVO persistence() {
		return new ResultVO("OK", redisServerInfo.persistence(config), 200);
	}

	@GetMapping("/stats")
	public ResultVO stats() {
		return new ResultVO("OK", redisServerInfo.stats(config), 200);
	}

	@GetMapping("/replication")
	public ResultVO replication() {
		return new ResultVO("OK", redisServerInfo.replication(config), 200);
	}

	@GetMapping("/cpu")
	public ResultVO cpu() {
		return new ResultVO("OK", redisServerInfo.cpu(config), 200);
	}

	@GetMapping("/cluster")
	public ResultVO cluster() {
		return new ResultVO("OK", redisServerInfo.cluster(config), 200);
	}

	@GetMapping("/keyspace")
	public ResultVO keyspace() {
		return new ResultVO("OK", redisServerInfo.keyspace(config), 200);
	}
}
