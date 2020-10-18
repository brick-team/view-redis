package com.github.huifer.view.redis.servlet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StringKeyService {
	void handler(String url, HttpServletRequest request, HttpServletResponse response);


	void add(String key, String value);

	void get(String key);


}
