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

import com.github.huifer.view.redis.api.RedisHashOperation;
import com.github.huifer.view.redis.impl.RedisHashOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.HashKeyService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashKeyServiceImpl implements HashKeyService {
	private static final Logger log = LoggerFactory.getLogger(HashKeyServiceImpl.class);

	RedisHashOperation hashOperation = new RedisHashOperationImpl();

	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url.startsWith("/hash/add")) {

			String[] split = url.split("/");
			String value = split[split.length - 1];
			String field = split[split.length - 2];
			String k = split[split.length - 3];

			this.add(k, field, value);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/get")) {
			String[] split = url.split("/");
			String k = split[split.length - 1];
			Object o = this.get(k);
			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/delete")) {
			String[] split = url.split("/");
			String field = split[split.length - 1];
			String key = split[split.length - 2];
			this.delete(key, field);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/update")) {
			String[] split = url.split("/");
			String value = split[split.length - 1];
			String field = split[split.length - 2];
			String k = split[split.length - 3];
			this.update(k, field, value);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
	}

	@Override
	public void add(String k, String field, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		hashOperation.add(config, k, field, v);

	}

	@Override
	public Object get(String k) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return hashOperation.get(config, k);
	}

	@Override
	public void delete(String k, String field) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		hashOperation.del(config, k, field);

	}

	@Override
	public void update(String k, String field, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		hashOperation.update(config, k, field, v);
	}
}
