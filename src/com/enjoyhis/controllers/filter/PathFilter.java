package com.enjoyhis.controllers.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;

import com.enjoyhis.util.LogUtils;

public class PathFilter implements Filter {

	Logger log = LogUtils.OPERATOR;

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String pathInfo = req.getRequestURI() + "?";

		if (!pathInfo.contains(".css") && !pathInfo.contains(".js") && !pathInfo.contains(".jpg") && !pathInfo.contains(".png") && !pathInfo.contains(".gif")) {
			Enumeration<String> parameterNames = req.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				Object paramName = (Object) parameterNames.nextElement();
				pathInfo += paramName;
				String paramValue = req.getParameter(paramName.toString());
				pathInfo += "=";
				pathInfo += paramValue;
				pathInfo += "&";
			}
			log.info(pathInfo);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
