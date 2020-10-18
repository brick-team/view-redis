package com.github.huifer.view.redis.servlet.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface KeySearchService {

	void handler(String url, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
