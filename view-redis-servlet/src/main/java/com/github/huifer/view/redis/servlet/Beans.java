package com.github.huifer.view.redis.servlet;

import java.util.Collections;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.github.huifer.view.redis.*")
public class Beans {


	@Bean
	public ServletRegistrationBean statViewServletRegistrationBean() {
		ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
		servletServletRegistrationBean.setServlet(new ViewRedisServlet("/support/"));
		servletServletRegistrationBean.setUrlMappings(Collections.singleton("/redis/*"));
		return servletServletRegistrationBean;
	}
}
