package com.github.huifer.view.redis.servlet.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.api.IRedisServerInfo;
import com.github.huifer.view.redis.impl.IRedisServiceInfoImpl;
import com.github.huifer.view.redis.model.EasyRedisInfo;
import com.github.huifer.view.redis.model.RedisConnectionConfig;
import com.github.huifer.view.redis.model.info.RedisCliInfoClients;
import com.github.huifer.view.redis.model.info.RedisCliInfoCluster;
import com.github.huifer.view.redis.model.info.RedisCliInfoCpu;
import com.github.huifer.view.redis.model.info.RedisCliInfoMemory;
import com.github.huifer.view.redis.model.info.RedisCliInfoPersistence;
import com.github.huifer.view.redis.model.info.RedisCliInfoReplication;
import com.github.huifer.view.redis.model.info.RedisCliInfoServer;
import com.github.huifer.view.redis.model.info.RedisCliInfoStats;
import com.github.huifer.view.redis.model.vo.ResultVO;
import com.github.huifer.view.redis.servlet.service.HandlerRedisService;
import com.github.huifer.view.redis.utils.SingletData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;

public class HandlerRedisServiceImpl implements HandlerRedisService {
	private static final Logger log = LoggerFactory.getLogger(HandlerRedisServiceImpl.class);

	IRedisServerInfo redisServerInfo = new IRedisServiceInfoImpl();


	Gson gson = new Gson();

	@Override
	public void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (log.isDebugEnabled()) {
			log.debug("当前请求的url = {}", url);
		}

		if (StringUtils.isEmpty(url)) {
			response.getWriter().write("url is null");
		}


		if (url.endsWith("easy_info")) {
			EasyRedisInfo easyRedisInfo = this.handlerEasyInfo();
			ResultVO ok = new ResultVO("ok", easyRedisInfo, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("server")) {
			RedisCliInfoServer redisCliInfoServer = this.handlerServe();
			ResultVO ok = new ResultVO("ok", redisCliInfoServer, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("clients")) {
			RedisCliInfoClients redisCliInfoClients = this.handlerClients();
			ResultVO ok = new ResultVO("ok", redisCliInfoClients, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("memory")) {
			RedisCliInfoMemory redisCliInfoMemory = this.handlerMemory();
			ResultVO ok = new ResultVO("ok", redisCliInfoMemory, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("persistence")) {
			RedisCliInfoPersistence redisCliInfoPersistence = this.handlerPersistence();
			ResultVO ok = new ResultVO("ok", redisCliInfoPersistence, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("stats")) {
			RedisCliInfoStats redisCliInfoStats = this.handlerStats();
			ResultVO ok = new ResultVO("ok", redisCliInfoStats, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("replication")) {
			RedisCliInfoReplication redisCliInfoReplication = this.handlerReplication();
			ResultVO ok = new ResultVO("ok", redisCliInfoReplication, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("cpu")) {
			RedisCliInfoCpu redisCliInfoCpu = this.handlerCpu();
			ResultVO ok = new ResultVO("ok", redisCliInfoCpu, 200);
			response.getWriter().print(gson.toJson(ok));
		}
		else if (url.endsWith("cluster")) {
			RedisCliInfoCluster redisCliInfoCluster = this.handlerCluster();
			ResultVO ok = new ResultVO("ok", redisCliInfoCluster, 200);
			response.getWriter().print(gson.toJson(ok));
		}

	}

	@Override
	public EasyRedisInfo handlerEasyInfo() {
		RedisConnectionConfig config = SingletData.getCurrConfig();

		EasyRedisInfo easyRedisInfo = new EasyRedisInfo();
		easyRedisInfo.setVersion(redisServerInfo.version(config));
		easyRedisInfo.setUseMemory(redisServerInfo.useMemory(config));
		easyRedisInfo.setClient(redisServerInfo.client(config));
		easyRedisInfo.setExecSize(redisServerInfo.execSize(config));
		easyRedisInfo.setRunTime(redisServerInfo.runTime(config));

		return easyRedisInfo;
	}

	@Override
	public RedisCliInfoServer handlerServe() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.server(config);

	}

	@Override
	public RedisCliInfoClients handlerClients() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.clients(config);
	}

	@Override
	public RedisCliInfoMemory handlerMemory() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.memory(config);
	}

	@Override
	public RedisCliInfoPersistence handlerPersistence() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.persistence(config);
	}

	@Override
	public RedisCliInfoStats handlerStats() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.stats(config);
	}

	@Override
	public RedisCliInfoReplication handlerReplication() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.replication(config);
	}

	@Override
	public RedisCliInfoCpu handlerCpu() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.cpu(config);
	}

	@Override
	public RedisCliInfoCluster handlerCluster() {
		RedisConnectionConfig config = SingletData.getCurrConfig();
		return redisServerInfo.cluster(config);
	}
}
