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

import com.github.huifer.view.redis.api.RedisHashOperation;
import com.github.huifer.view.redis.impl.RedisHashOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.HashKeyService;
import com.github.huifer.view.redis.servlet.utils.HttpServletUtils;
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

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String keyParam = map.get("key");
			String fieldParam = map.get("field");
			String valueParam = map.get("value");

			this.add(keyParam, fieldParam, valueParam);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/get")) {

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String keyParam = map.get("key");
			Object o = this.get(keyParam);

			ResultVO ok = new ResultVO("ok", o, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/delete")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);


			String keyParam = map.get("key");
			String fieldParam = map.get("field");
			this.delete(keyParam, fieldParam);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/hash/update")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String keyParam = map.get("key");
			String fieldParam = map.get("field");
			String valueParam = map.get("value");
			this.update(keyParam, fieldParam, valueParam);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/hash/nup")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String keyParam = map.get("key");
			String oldFieldParam = map.get("oldField");
			String newFieldParam = map.get("newField");
			String valueParam = map.get("value");

			this.nup(keyParam, oldFieldParam, newFieldParam, valueParam);

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

	@Override
	public void nup(String k, String oldField, String newField, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		hashOperation.upAndSave(config, k, oldField, newField, v);
	}
}
