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

package com.github.huifer.view.redis.servlet.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.RedisZSetOperation;
import com.github.huifer.view.redis.impl.RedisZSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.ZSetKeyService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZSetKeyServiceImpl implements ZSetKeyService {
	private static final Logger log = LoggerFactory.getLogger(ZSetKeyServiceImpl.class);

	Gson gson = new Gson();

	RedisZSetOperation zSetOperation = new RedisZSetOperationImpl();


	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url.startsWith("/zset/add")) {
			String[] split = url.split("/");
			String value = split[split.length - 1];
			String score = split[split.length - 2];
			String key = split[split.length - 3];
			this.add(key, Double.parseDouble(score), value);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/zset/delete")) {
			String[] split = url.split("/");
			String value = split[split.length - 1];
			String key = split[split.length - 2];
			this.delete(key, value);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/zset/get")) {
			String[] split = url.split("/");
			String key = split[split.length - 1];

			Object o = this.get(key);
			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/zset/update")) {
			String[] split = url.split("/");
			String value = split[split.length - 1];
			String score = split[split.length - 2];
			String key = split[split.length - 3];

			this.update(key, Double.parseDouble(score), value);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
	}

	@Override
	public void add(String k, double score, String member) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		zSetOperation.add(config, k, score, member);

	}

	@Override
	public void delete(String key, String member) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		zSetOperation.del(config, key, member);

	}

	@Override
	public void update(String k, double score, String member) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		zSetOperation.update(config, k, score, member);

	}

	@Override
	public Object get(String key) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return zSetOperation.get(config, key);
	}
}
