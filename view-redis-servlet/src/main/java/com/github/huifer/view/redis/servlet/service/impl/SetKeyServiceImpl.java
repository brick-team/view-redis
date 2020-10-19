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

import com.github.huifer.view.redis.api.RedisSetOperation;
import com.github.huifer.view.redis.impl.RedisSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.SetKeyService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

public class SetKeyServiceImpl implements SetKeyService {
	RedisSetOperation setOperation = new RedisSetOperationImpl();

	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url.startsWith("/set/add")) {
			String[] split = url.split("/");
			String value = split[split.length - 1];
			String key = split[split.length - 2];
			this.add(key, value);

			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/set/get")) {
			String[] split = url.split("/");

			String key = split[split.length - 1];
			Object o = this.get(key);
			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/set/update")) {
			String[] split = url.split("/");
			String nv = split[split.length - 1];
			String ov = split[split.length - 2];
			String k = split[split.length - 3];
			this.update(k, ov, nv);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/set/delete")) {
			String[] split = url.split("/");
			String v = split[split.length - 1];
			String k = split[split.length - 2];
			this.delete(k, v);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
	}

	@Override
	public void add(String k, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		setOperation.add(config, k, v);

	}

	@Override
	public Object get(String key) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return setOperation.get(config, key);
	}

	@Override
	public void update(String k, String ov, String nv) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		setOperation.update(config, k, ov, nv);

	}

	@Override
	public void delete(String k, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		setOperation.del(config, k, v);

	}
}
