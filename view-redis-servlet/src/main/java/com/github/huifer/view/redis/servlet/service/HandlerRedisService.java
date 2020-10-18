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
