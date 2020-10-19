package com.github.huifer.view.redis.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.huifer.view.redis.servlet.service.HandlerRedisService;
import com.github.huifer.view.redis.servlet.service.HashKeyService;
import com.github.huifer.view.redis.servlet.service.KeySearchService;
import com.github.huifer.view.redis.servlet.service.ListKeyService;
import com.github.huifer.view.redis.servlet.service.SetKeyService;
import com.github.huifer.view.redis.servlet.service.StringKeyService;
import com.github.huifer.view.redis.servlet.service.ZSetKeyService;
import com.github.huifer.view.redis.servlet.service.impl.HandlerRedisServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.HashKeyServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.KeySearchServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.ListKeyServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.SetKeyServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.StringKeyServiceImpl;
import com.github.huifer.view.redis.servlet.service.impl.ZSetKeyServiceImpl;
import com.github.huifer.view.redis.servlet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;


public class ViewRedisServlet extends HttpServlet {
	public final static int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static final String LOGIN_NAME_PARAM = "login_name";

	public static final String PASSWORD_PARAM = "password";

	private static final Logger log = LoggerFactory.getLogger(ViewRedisServlet.class);

	protected final String resourcePath;

	HandlerRedisService handlerRedisService = new HandlerRedisServiceImpl();

	KeySearchService keySearchService = new KeySearchServiceImpl();

	StringKeyService stringKeyService = new StringKeyServiceImpl();

	HashKeyService hashKeyService = new HashKeyServiceImpl();

	ZSetKeyService zSetKeyService = new ZSetKeyServiceImpl();

	ListKeyService listKeyService = new ListKeyServiceImpl();

	SetKeyService setKeyService = new SetKeyServiceImpl();

	private String loginName;

	private String password;

	public ViewRedisServlet(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@Override
	public void init() throws ServletException {
		super.init();

		String initParamLoginName = getInitParameter(LOGIN_NAME_PARAM);


		if (!StringUtils.isEmpty(initParamLoginName)) {
			this.loginName = initParamLoginName;
		}
		else {
			this.loginName = "redis-admin";
		}
		String initParamPassword = getInitParameter(PASSWORD_PARAM);


		if (!StringUtils.isEmpty(initParamPassword)) {
			this.password = initParamPassword;
		}
		else {
			this.password = "redis-admin";
		}

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
		assert text != null;
		resp.getWriter().write(text);

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String requestURI = req.getRequestURI();

		resp.setCharacterEncoding("utf-8");

		if (contextPath == null) {
			contextPath = "";
		}

//		String uri = contextPath + servletPath;
		String path = requestURI.substring(contextPath.length() + servletPath.length());
		log.debug("当前访问地址 = {}", path);

		if (path.equals("/login")) {
			String loginNameParam = req.getParameter(LOGIN_NAME_PARAM);
			String paramPassword = req.getParameter(PASSWORD_PARAM);


			if (this.loginName.equals(loginNameParam) && this.password.equals(paramPassword)) {
				req.getSession().setAttribute(LOGIN_NAME_PARAM, loginNameParam);
				resp.getWriter().print("success");
			}
			else {
				resp.getWriter().print("error");
			}

		}

		// 进入登录页面
		if ("".equals(path) || "login".equals(path) || "/".equals(path)) {
			returnResourceFile("/login.html", "", resp);
		}
		// 进入 view redis 管理页面
		if ("/index.html".equals(path)) {
			if (checkSession(req, resp)) {
				returnResourceFile("/index.html", "", resp);
			}
			else {
				return;
			}

		}

		if (path.startsWith("/service")) {
			this.handlerRedisService.handler(path, req, resp);
		}
		if (path.startsWith("/key")) {
			this.keySearchService.handler(path, req, resp);
		}
		if (path.startsWith("/string")) {
			this.stringKeyService.handler(path, req, resp);
		}
		if (path.startsWith("/hash")) {
			this.hashKeyService.handler(path, req, resp);
		}
		if (path.startsWith("/zset")) {
			this.zSetKeyService.handler(path, req, resp);
		}
		if (path.startsWith("/list")) {
			this.listKeyService.handler(path, req, resp);
		}
		if (path.startsWith("/set")) {
			this.setKeyService.handler(path, req, resp);
		}
	}

	/**
	 * 判断是否存在 login_name , 不存在返回异常
	 */
	private boolean checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String sessionLoginName = (String) request.getSession().getAttribute(LOGIN_NAME_PARAM);
		if (StringUtils.isEmpty(sessionLoginName)) {
			response.getWriter().write("error");
			return false;
		}
		return true;
	}

}
