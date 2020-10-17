package com.github.huifer.view.redis.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewRedisServlet extends HttpServlet {
	public final static int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private static final Logger log = LoggerFactory.getLogger(ViewRedisServlet.class);

	protected final String resourcePath;

	public ViewRedisServlet(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public static String read(InputStream in) {
		InputStreamReader reader;
		reader = new InputStreamReader(in, StandardCharsets.UTF_8);
		return read(reader);
	}

	public static String read(Reader reader) {
		try {

			StringWriter writer = new StringWriter();

			char[] buffer = new char[DEFAULT_BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}

			return writer.toString();
		}
		catch (IOException ex) {
			throw new IllegalStateException("read error", ex);
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected String getFilePath(String fileName) {
		return resourcePath + fileName;
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String filePath = getFilePath("index.html");

		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		String requestURI = req.getRequestURI();

		resp.getWriter().write(String.format("<!doctype html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\"\n" +
				"          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
				"    <title>Document</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<h2>%s</h2>\n" +
				"\n" +
				"\n" +
				"</body>\n" +
				"</html>", requestURI));
	}

}
