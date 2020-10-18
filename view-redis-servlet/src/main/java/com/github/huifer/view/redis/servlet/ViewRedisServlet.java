package com.github.huifer.view.redis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.servlet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ViewRedisServlet extends HttpServlet {
	public final static int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private static final Logger log = LoggerFactory.getLogger(ViewRedisServlet.class);

	protected final String resourcePath;

	public ViewRedisServlet(String resourcePath) {
		this.resourcePath = resourcePath;
	}


	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected String getFilePath(String fileName) {
		return resourcePath + fileName;
	}


	protected void returnResourceFile(
			String fileName, String uri, HttpServletResponse resp
	) throws IOException {


		String filePath = getFilePath(fileName);

		if (filePath.endsWith(".html")) {
			resp.setContentType("text/html; charset=utf-8");

		}

		String text = Utils.readFromResource(filePath);
		resp.getWriter().write(text);

	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String requestURI = req.getRequestURI();


		returnResourceFile("/index.html", "", resp);
	}

}
