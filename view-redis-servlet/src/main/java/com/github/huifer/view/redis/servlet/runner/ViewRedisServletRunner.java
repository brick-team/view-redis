package com.github.huifer.view.redis.servlet.runner;

import com.github.huifer.view.redis.cache.SpringRedisProperties;
import com.github.huifer.view.redis.utils.SingletData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class ViewRedisServletRunner {

	private static final Logger log = LoggerFactory.getLogger(ViewRedisServletRunner.class);

	@Autowired
	private ApplicationContext context;

	@Bean
	@ConditionalOnMissingBean({RedisProperties.class, RedisConnectionFactory.class})
	public ApplicationRunner runner() {
		return args -> {
			RedisProperties redisProperties = context.getBean(RedisProperties.class);

 			if (log.isDebugEnabled()) {
				log.debug("开始设置 RedisProperties");

			}

			RedisConnectionFactory redisConnectionFactory = context.getBean(RedisConnectionFactory.class);


			SpringRedisProperties springRedisProperties = new SpringRedisProperties();
			springRedisProperties.setProperties(redisProperties);
			springRedisProperties.setRedisConnectionFactory(redisConnectionFactory);

			SingletData.setSpringRedisProperties(springRedisProperties);

		};
	}
}

