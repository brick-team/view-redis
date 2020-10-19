package com.github.huifer.view.redis.servlet.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.RedisKeysOperation;
import com.github.huifer.view.redis.impl.RedisKeysService;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.KeySearchService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;

public class KeySearchServiceImpl implements KeySearchService {

	RedisKeysOperation keysOperation = new RedisKeysService();


	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		if (url.startsWith("/key/info")) {
			String[] split = url.split("/");
			String keyRegion = split[split.length - 1];
			ResultVO ok = new ResultVO("ok", keysOperation.keys(config, keyRegion), 200);
			response.getWriter().write(gson.toJson(ok));
		}
		else if (url.startsWith("/key/del")) {
			String[] split = url.split("/");
			String key = split[split.length - 1];
			Boolean del = keysOperation.del(config, key);
			ResultVO ok = new ResultVO("ok", del, 200);
			response.getWriter().write(gson.toJson(ok));
		}
	}
}
