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
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.util.Constants;

@Controller
@RequestMapping(value = "/report/income")
public class IncomeReportController {

	@Autowired
	private HisPatientService hisPatientService;
	@Autowired
	private HisEmployeeService hisEmployeeService;

	@RequestMapping(value = "gotoIncomeReport.htm")
	public String gotoSourceReport(Model model, HttpServletRequest request, HttpServletResponse response, Long id) {
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
		model.addAttribute("idDoc", he.getUserType() == 1);
		if (null != he.getUserType() && he.getUserType() == 1)
			model.addAttribute("uid", loginId);
		else
			model.addAttribute("uid", null);
		return "report/income_report";
	}

	@RequestMapping(value = "/incomeReport.json")
	public void incomeReport(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate, Long docId) {
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
		if (he.getUserType() != 1) {
			page.setRows(hisPatientService.selectDateList(strDate, endDate, docId));
		} else {
			page.setRows(hisPatientService.selectDateList(strDate, endDate, loginId));
		}
		request.setAttribute(Constants.pageResultData, page);
	}
	
	@RequestMapping(value = "incomeReportToExcell.htm", method = RequestMethod.GET)
	public ResponseEntity<byte[]> incomeReportToExcell(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate, Long docId) throws Exception {
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
		if (he.getUserType() != 1) {
			page.setRows(hisPatientService.selectDateList(strDate, endDate, docId));
		} else {
			page.setRows(hisPatientService.selectDateList(strDate, endDate, loginId));
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(("医生收入表.xlsx").getBytes("gb2312"), "iso-8859-1"));
		byte[] bytes = hisPatientService.getCardDetailListExcel(page.getRows());
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

	}

}
