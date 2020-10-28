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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.RedisKeysOperation;
import com.github.huifer.view.redis.impl.RedisKeysService;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.KeySearchService;
import com.github.huifer.view.redis.servlet.utils.HttpServletUtils;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

public class KeySearchServiceImpl implements KeySearchService {

	RedisKeysOperation keysOperation = new RedisKeysService();


	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		if (url.startsWith("/key/info")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);


			ResultVO ok = new ResultVO("ok", keysOperation.keys(config, map.get("key")), 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/key/del") && !url.endsWith("batch")) {

			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);
			String paramKey = map.get("key");

			Boolean del = keysOperation.del(config, paramKey);
			ResultVO ok = new ResultVO("ok", del, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/key/expire")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);
			String paramKey = map.get("key");
			String paramExpire = map.get("expire");
			Boolean expire = keysOperation.expire(config, paramKey, Long.valueOf(paramExpire));
			ResultVO ok = new ResultVO("ok", expire, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/key/rename")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, String> map = gson.fromJson(postBody, Map.class);

			String oldKey = map.get("oldKey");
			String newKey = map.get("newKey");

			keysOperation.rename(config, oldKey, newKey);

			ResultVO ok = new ResultVO("ok", true, 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/key/delete/batch")) {
			String postBody = HttpServletUtils.getPostBody(request);
			Map<String, List<String>> map = gson.fromJson(postBody, Map.class);
			Long keys = this.keysOperation.deleteKeyInBatch(config, map.get("keys"));
			ResultVO ok = new ResultVO("ok", keys != null, 200);
			response.getWriter().write(gson.toJson(ok));

		}
	}

}



