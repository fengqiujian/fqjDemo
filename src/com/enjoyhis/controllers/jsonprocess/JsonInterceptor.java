package com.enjoyhis.controllers.jsonprocess;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.enjoyhis.annotation.AuthPassport;
import com.enjoyhis.controllers.json.AppResults;
import com.enjoyhis.controllers.json.ClientInfo;
import com.enjoyhis.controllers.json.Results;
import com.enjoyhis.controllers.json.Status;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.JSONUtils;

public class JsonInterceptor extends HandlerInterceptorAdapter {
	final Logger logger = LoggerFactory.getLogger("client");

	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 60 * 60 * 24);
		// if(session.getAttribute("user")==null){
		// if(requestPath.equals("/admin/login.htm?null")||requestPath.equals("/admin/login?null")){
		//
		// }else
		// if(requestPath.equals("/admin/index.htm?null")||requestPath.equals("/admin/timeOut.htm?null")){
		//
		// }else
		// {
		//// response.sendRedirect("timeOut.htm");
		// request.getRequestDispatcher("timeOut.htm").forward(request, response);
		// return false;
		// }
		// }

		request.setAttribute("urlBeginTime", System.currentTimeMillis());
		ClientInfo reqInfo = createReqInfo(request);
		// request.setAttribute(Constants.clientInfo, reqInfo);
		// 验证用户权限
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
			if (authPassport != null) {
				if (StringUtils.isBlank(reqInfo.getxCookie()) || reqInfo.getxUid() == null) {
					AppResults results = new AppResults();
					Status status = new Status();
					status.setCode(-10);
					status.setDesc("权限验证失败");
					results.setStatus(status);
					sendJson(request, response, results);
					return false;
				} else {
					boolean ischeck = true;// ykDentistService.checkCookie(reqInfo.getxUid(),
											// reqInfo.getxCookie());
					if (!ischeck) {
						AppResults results = new AppResults();
						Status status = new Status();
						status.setCode(-10);
						status.setDesc("权限验证失败");
						results.setStatus(status);
						sendJson(request, response, results);
						return false;
					}
				}

				request.setAttribute(Constants.clientInfo, reqInfo);
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception {

		Integer jsonResultCode = (Integer) request.getAttribute(Constants.jsonResultCode);
		Object returndata = request.getAttribute(Constants.jsonResultData);
		Results pageResultData = (Results) request.getAttribute(Constants.pageResultData);
		Integer totalNum = (Integer) request.getAttribute(Constants.jsonResultSize);
		Integer hasMore = (Integer) request.getAttribute(Constants.jsonHasMore);
		// 是JSON响应处理，html响应不处理
		if (jsonResultCode != null || returndata != null) {
			if (jsonResultCode == null) {
				jsonResultCode = 0;
			}
			AppResults results = new AppResults();
			Status status = new Status();
			status.setCode(jsonResultCode);
			if (jsonResultCode < 0) {
				status.setDesc(Constants.codeMap.get(jsonResultCode));
			}
			results.setStatus(status);
			results.setReturndata(returndata);
			results.setTotalNum(totalNum);
			results.setHasMore(hasMore);
			sendJson(request, response, results);
		} else if (pageResultData != null) {
			sendJson(request, response, pageResultData);
		} else {
			response.setHeader("Cache-Control", "no-cache");
		}
		// System.out.println("post handle time"+ (System.currentTimeMillis() -
		// (Long)request.getAttribute("startTime")));
		logStat(request);
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object handler, Exception ex) throws Exception {
		if (ex != null) {
			ex.printStackTrace();
		}
	}

	private ClientInfo createReqInfo(HttpServletRequest request) {
		ClientInfo reqInfo = new ClientInfo();
		reqInfo.setAcceptEmojiEncoding(request.getHeader("emoji-encoding"));
		reqInfo.setIp(request.getRemoteAddr());
		reqInfo.setxChannelId(request.getHeader("x-channel-id"));
		reqInfo.setxClientVersion(request.getHeader("x-client-version"));
		reqInfo.setxCookie(request.getHeader("x-cookie"));
		reqInfo.setxMachineId(request.getHeader("x-machine-id"));
		reqInfo.setxPlatform(request.getHeader("x-platform"));
		reqInfo.setxPlatformUa(request.getHeader("x-platform-ua"));
		if (StringUtils.isNotBlank(request.getHeader("x-uid"))) {
			reqInfo.setxUid(Long.valueOf(request.getHeader("x-uid")));
		} else {
			// for test
			if (request.getParameter("x-uid") != null)
				reqInfo.setxUid(Long.valueOf(request.getParameter("x-uid")));
			if (request.getParameter("x-cookie") != null)
				reqInfo.setxCookie(request.getParameter("x-cookie"));
		}
		if ("iphone".equals(reqInfo.getxPlatform())) {
			reqInfo.setOrigin("1");
		} else {
			if ("iphone-qy".equals(reqInfo.getxPlatform())) {
				reqInfo.setOrigin("3");
			} else {
				reqInfo.setOrigin("2");
			}
		}
		request.setAttribute(Constants.clientInfo, reqInfo);
		return reqInfo;
	}

	private void sendJson(HttpServletRequest request, HttpServletResponse response, Results results) {

		String respstr = JSONUtils.toJSONString(results);
		// 不同客户端表情符转码
		// respstr = IOSEmojiUtil.processEmojiTransCoding(respstr, "response",
		// (ClientInfo)request.getAttribute("clientInfo"));

		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setContentLength(respstr.getBytes("utf-8").length);
			response.getWriter().write(respstr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void logStat(HttpServletRequest request) {
		ClientInfo reqInfo = (ClientInfo) request.getAttribute("clientInfo");
		StringBuffer loginfo = new StringBuffer();
		long spend_time = 0;
		Long btime = (Long) request.getAttribute("urlBeginTime");
		if (btime != null)
			spend_time = System.currentTimeMillis() - btime;
		// **************设备类型***************//
		String data = reqInfo.getxPlatform();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************远端IP***************//
		data = reqInfo.getIp();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************版本号***************//
		data = reqInfo.getxClientVersion();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************渠道号***************//
		data = reqInfo.getxChannelId();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************UID***************//
		Long uid = reqInfo.getxUid();
		if (uid == null) {
			loginfo.append("").append("\t");
		} else {
			loginfo.append(uid).append("\t");
		}
		// **************URL***************//
		data = request.getRequestURI();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************COMMANDS***************//
		data = request.getQueryString();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************响应时间***************//
		// long startTime = (Long) request.getAttribute("startTime");
		// request.removeAttribute("startTime");
		loginfo.append(spend_time + "\t");
		// **************设备编码***************//
		data = reqInfo.getxMachineId();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		// **************PLANTFORM-UA***************//
		data = reqInfo.getxPlatformUa();
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		if (StringUtils.isBlank(data)) {
			data = "";
		}
		loginfo.append(data).append("\t");
		logger.info(loginfo.toString());
	}
}