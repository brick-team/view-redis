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

package com.github.huifer.view.redis.servlet.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.model.EasyRedisInfo;
import com.github.huifer.view.redis.model.info.RedisCliInfoClients;
import com.github.huifer.view.redis.model.info.RedisCliInfoCluster;
import com.github.huifer.view.redis.model.info.RedisCliInfoCpu;
import com.github.huifer.view.redis.model.info.RedisCliInfoMemory;
import com.github.huifer.view.redis.model.info.RedisCliInfoPersistence;
import com.github.huifer.view.redis.model.info.RedisCliInfoReplication;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.info.RedisCliInfoStats;

/**
 * 处理接口请求: /redis/service
 * @author huifer
 */
public interface HandlerRedisService {

	void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException;


	EasyRedisInfo handlerEasyInfo();

	RedisCliInfoServer handlerServe();

	RedisCliInfoClients handlerClients();


	RedisCliInfoMemory handlerMemory();


	RedisCliInfoPersistence handlerPersistence();

	RedisCliInfoStats handlerStats();

	RedisCliInfoReplication handlerReplication();

	RedisCliInfoCpu handlerCpu();

	RedisCliInfoCluster handlerCluster();

}
