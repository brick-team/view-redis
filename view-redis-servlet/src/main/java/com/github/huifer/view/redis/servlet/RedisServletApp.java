package com.github.huifer.view.redis.servlet;

import com.github.huifer.view.redis.http.ViewRedisServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisServletApp {
	public static void main(String[] args) {
		SpringApplication.run(RedisServletApp.class, args);
	}
	@Bean
	public ServletRegistrationBean statViewServletRegistrationBean() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ViewRedisServlet("/support/"),
				"/ddd/*");
		return registrationBean;
	}
}
