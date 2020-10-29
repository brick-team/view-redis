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

import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.RedisConnectionService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

/**
 *
 *
 * @author huifer
 */
public class RedisConnectionServiceImpl implements RedisConnectionService {
	Gson gson = new Gson();

	@Override
	public boolean redis() {
		return SingletData.isHasRedis();
	}

	@Override
	public boolean cluster() {
		return SingletData.isHasRedisCluster();
	}

	@Override
	public void handler(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (path.startsWith("/cm/redis")) {
			ResultVO ok = new ResultVO("ok", redis(), 200);
			resp.getWriter().write(gson.toJson(ok));
		}
		else if (path.startsWith("/cm/cluster")) {
			ResultVO ok = new ResultVO("ok", cluster(), 200);
			resp.getWriter().write(gson.toJson(ok));
		}
	}
}
