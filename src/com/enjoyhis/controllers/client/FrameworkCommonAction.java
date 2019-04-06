package com.enjoyhis.controllers.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.pojo.KeyValueMap;
import com.enjoyhis.service.KeyValueMapService;
import com.enjoyhis.util.Constants;

/*
 * 一些小的工具
 */
@Controller
@RequestMapping("/client/framework/FrameworkCommonAction")
public class FrameworkCommonAction {

	@Autowired
	private KeyValueMapService<KeyValueMap> keyValueMapService;

	/**
	 * json 下拉列表
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query4DropDown.json")
	public void query4DropDown(String table, String column1, String column2, String sqlstr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<KeyValueMap> list = keyValueMapService.query4DropDown(table, column1, column2, sqlstr);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "/query4Info.json")
	public void query4Infos(String table, String column1, String column2, String sqlstr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<KeyValueMap> list = keyValueMapService.queryInfo(table, column1, column2, sqlstr);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

}
