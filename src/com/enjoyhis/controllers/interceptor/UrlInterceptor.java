package com.enjoyhis.controllers.interceptor;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.enjoyhis.persistence.his.dao.ProcessDao;
import com.enjoyhis.persistence.his.dao.SysConfigMapper;
import com.enjoyhis.persistence.his.po.SysConfig;
import com.enjoyhis.pojo.Process;

public class UrlInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SysConfigMapper configMapper;

	@Autowired
	private ProcessDao processDao;

	private List<String> excludeUrls;

	// 0正常 1拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UrlPathHelper helper = new UrlPathHelper();
		String relativePath = helper.getOriginatingRequestUri(request);
		List<String> excludeUrls = getExcludeUrls();
		if (excludeUrls.contains(relativePath)) {
			return true;
		}
		response.setContentType("text/html;charset=UTF-8");
		SysConfig record = new SysConfig();
		record.setKeystr("run_status");
		SysConfig sysConfig = configMapper.selectOne(record);
		if (sysConfig != null && sysConfig.getValuestr().equals("1")) {
			PrintWriter writer = response.getWriter();
			writer.print("{\"status\":{\"code\":-11,\"desc\":\"正在进行扎帐，目前不能进行操作\"}}");
			request.setAttribute("info", "正在进行扎帐，目前不能进行操作");
			writer.flush();
			writer.close();
			return false;
		}

		Long currentTimeMillis = System.currentTimeMillis();
		Long y = currentTimeMillis - new Long(1000 * 60 * 60 * 24);
		Date yes = new Date(y);
		// 获取最后扎帐记录
		Process findBiger = processDao.findBiger();
		if (findBiger == null) {
			PrintWriter writer = response.getWriter();
			writer.print("{\"status\":{\"code\":-11,\"desc\":\"系统未进行扎帐，目前不能进行操作\"}}");
			request.setAttribute("info", "系统未进行扎帐，目前不能进行操作");
			writer.flush();
			writer.close();
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date yesterday = sdf.parse(sdf.format(yes));
		if (findBiger != null && findBiger.getStatDate().before(yesterday)) {
			PrintWriter writer = response.getWriter();
			writer.print("{\"status\":{\"code\":-11,\"desc\":\"昨天未进行扎帐，目前不能进行操作\"}}");
			request.setAttribute("info", "昨天未进行扎帐，目前不能进行操作");
			writer.flush();
			writer.close();
			return false;
		} else {
			if (findBiger.getDateStatus() == 1) {
				PrintWriter writer = response.getWriter();
				writer.print("{\"status\":{\"code\":-11,\"desc\":\"未按时进行扎帐，系统已锁定，请联系管理员解锁\"}}");
				request.setAttribute("info", "未按时进行扎帐，系统已锁定，请联系管理员解锁");
				writer.flush();
				writer.close();
				return false;
			}
		}

		return true;
	}

	public List<String> getExcludeUrls() {
		if (excludeUrls == null) {
			excludeUrls = Collections.emptyList();
		}
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}
