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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.RedisSetOperation;
import com.github.huifer.view.redis.impl.RedisSetOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.SetKeyService;
import com.github.huifer.view.redis.servlet.utils.HttpServletUtils;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

public class SetKeyServiceImpl implements SetKeyService {
	RedisSetOperation setOperation = new RedisSetOperationImpl();

	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url.startsWith("/set/add")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String paramKey = map.get("key");
			String paramValue = map.get("value");

			this.add(paramKey, paramValue);

			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/set/get")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String paramKey = map.get("key");

			Object o = this.get(paramKey);
			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/set/update")) {

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String keyParam = map.get("key");
			String nvParam = map.get("nv");
			String ovParam = map.get("ov");

			this.update(keyParam, ovParam, nvParam);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/set/delete")) {

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String paramKey = map.get("key");
			String paramValue = map.get("value");


			this.delete(paramKey, paramValue);
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
