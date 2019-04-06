package com.enjoyhis.controllers.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.service.FinanceReportService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.util.Constants;

/**
 * 收款汇总controller
 */
@Controller
@RequestMapping(value = "/report/finance")
public class FinanceReportController {

	@Autowired
	private FinanceReportService financeReportService;
	@Autowired
	private HisEmployeeService hisEmployeeService;

	@RequestMapping(value = "gotoFinanceReport.htm")
	public String gotoFinanceReport(Model model, HttpServletRequest request, HttpServletResponse response, Long id) {
		return "report/finance_report";
	}

	@RequestMapping("/reportFirst.json")
	public void reportFirst(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long loginId = Long.parseLong(cookie.getValue());
		HisEmployee he = hisEmployeeService.getHisEmployeeOne(loginId);
		if (he.getUserType() != 1) { // 医生传空id
			page.setRows(financeReportService.selectDateList(strDate, endDate, null));
		} else {
			page.setRows(financeReportService.selectDateList(strDate, endDate, loginId));
		}
		request.setAttribute(Constants.pageResultData, page);
	}
	@RequestMapping(value = "reportFirstToExcell.htm", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(("收款汇总统计表.xlsx").getBytes("gb2312"), "iso-8859-1"));
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long loginId = Long.parseLong(cookie.getValue());
		HisEmployee he = hisEmployeeService.getHisEmployeeOne(loginId);
		byte[] bytes;
		if (he.getUserType() != 1) { // 医生传空id
			bytes = financeReportService.getCardDetailListExcel(strDate, endDate, null);
		} else {
			bytes =financeReportService.getCardDetailListExcel(strDate, endDate, loginId);
		}
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
}
