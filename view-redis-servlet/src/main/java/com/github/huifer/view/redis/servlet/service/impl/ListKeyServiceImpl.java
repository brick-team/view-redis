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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.RedisListOperation;
import com.github.huifer.view.redis.impl.RedisListOperationImpl;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.IndexAndData;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.ListKeyService;
import com.github.huifer.view.redis.servlet.utils.HttpServletUtils;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

public class ListKeyServiceImpl implements ListKeyService {

	RedisListOperation redisListOperation = new RedisListOperationImpl();

	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (url.startsWith("/list/add")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String key = map.get("key");
			String value = map.get("value");

			this.add(key, value);

			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/list/get")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String key = map.get("key");
			String start = map.get("start");
			String stop = map.get("stop");
			Object o = this.get(key, Long.parseLong(start), Long.parseLong(stop));
			long size = this.size(key);
			Map<String, Object> res = new HashMap<>();
			res.put("size", size);
			res.put("rows", o);
			ResultVO ok = new ResultVO("ok", res, 200);
			response.getWriter().write(gson.toJson(ok));

		}
		else if (url.startsWith("/list/update")) {

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String k = map.get("k");
			String ov = map.get("ov");
			String nv = map.get("nv");

			this.update(k, ov, nv);
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/list/removeByRow")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String k = map.get("k");
			String row = map.get("row");

			this.removeRow(k, Integer.parseInt(row));
			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
	}

	@Override
	public void add(String k, String v) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		redisListOperation.add(config, k, v);

	}

	@Override
	public Object get(String k) {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		List<?> list = redisListOperation.get(config, k);
		List<IndexAndData> res = new ArrayList<>(list.size());

		for (int i = 0; i < list.size(); i++) {
			IndexAndData indexAndData = new IndexAndData();
			indexAndData.setIndexId(i);
			indexAndData.setData(list.get(i));
			res.add(indexAndData);
		}

		return res;
	}

	@Override
	public void update(String k, String ov, String nv) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		redisListOperation.update(config, k, ov, nv);

	}

	@Override
	public void removeRow(String k, int row) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		redisListOperation.removeByRow(config, k, row);

	}

	@Override
	public Object get(String k, long start, long stop) {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		List<?> list = redisListOperation.get(config, k, start, stop);
		List<IndexAndData> res = new ArrayList<>(list.size());
		for (int i = 0; i < list.size(); i++) {
			IndexAndData indexAndData = new IndexAndData();
			indexAndData.setIndexId(i + start);
			indexAndData.setData(list.get(i));
			res.add(indexAndData);
		}
		return res;
	}

	@Override
	public long size(String key) {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisListOperation.size(config, key);
	}
}
