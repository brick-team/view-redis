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

import com.github.huifer.view.redis.api.RedisStringOperation;
import com.github.huifer.view.redis.impl.RedisStringOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.StringKeyService;
import com.github.huifer.view.redis.servlet.utils.HttpServletUtils;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringKeyServiceImpl implements StringKeyService {

	private static final Logger log = LoggerFactory.getLogger(StringKeyServiceImpl.class);

	Gson gson = new Gson();

	RedisStringOperation stringOperation = new RedisStringOperationImpl();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (url.startsWith("/string/add")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);
			String paramKey = map.get("key");
			String paramValue = map.get("value");
			this.add(paramKey, paramValue);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/string/get")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);
			String paramKey = map.get("key");
			Object o = this.get(paramKey);
			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/string/delete")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);
			String paramKey = map.get("key");
			this.delete(paramKey);
			ResultVO ok = new ResultVO("ok", true, 200);

			response.getWriter().write(gson.toJson(ok));
		}

	}

	@Override
	public void add(String key, String value) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		stringOperation.add(config, key, value);

	}

	@Override
	public Object get(String key) {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		return stringOperation.get(config, key);
	}

	@Override
	public void delete(String key) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		stringOperation.delete(config, key);

	}
}
