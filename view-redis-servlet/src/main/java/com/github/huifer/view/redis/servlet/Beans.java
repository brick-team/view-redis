package com.github.huifer.view.redis.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import static com.github.huifer.view.redis.servlet.ViewRedisServlet.LOGIN_NAME_PARAM;
import static com.github.huifer.view.redis.servlet.ViewRedisServlet.PASSWORD_PARAM;

@Component
@ComponentScan("com.github.huifer.view.redis.*")
@EnableConfigurationProperties(ViewRedisConfig.class)
public class Beans {

	@Autowired
	private ApplicationContext context;

	@Bean
	public ServletRegistrationBean viewRedisServlet() {
		ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
		servletServletRegistrationBean.setServlet(new ViewRedisServlet("/support/"));
		Map<String, String> initParams = new HashMap<>(10);
		ViewRedisConfig bean = context.getBean(ViewRedisConfig.class);


		if (bean != null && !bean.equals(new ViewRedisConfig())) {

			initParams.put(LOGIN_NAME_PARAM, bean.getLoginName());
			initParams.put(PASSWORD_PARAM, bean.getPassword());

		}
		servletServletRegistrationBean.setInitParameters(initParams);
		servletServletRegistrationBean.setUrlMappings(Collections.singleton("/redis/*"));
		return servletServletRegistrationBean;
	}
}
