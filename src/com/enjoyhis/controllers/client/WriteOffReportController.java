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
import com.enjoyhis.service.SourceReportService;
import com.enjoyhis.util.Constants;

@Controller
@RequestMapping(value = "/report/WriteOff")
public class WriteOffReportController {

	@Autowired
	private SourceReportService sourceReportService;
	@Autowired
	private HisEmployeeService hisEmployeeService;

	@RequestMapping(value = "getDocList.json")
	public void getDocList(HttpServletRequest request, HttpServletResponse response, String pym) {
		if (pym == null) {
			return;
		}
		HisEmployee he = new HisEmployee();
		he.setUserType(1);
		he.setStatus(1);
		he.setIsShow(1);
		he.setSqlStr(" and employee_name like '%" + pym + "%'");
		he.setLimitStart(0L);
		he.setLimitCount(5);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisEmployeeService.getSearchItemList(he));
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "gotoWriteOffReport.htm")
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
		return "report/writeOff_report";
	}

	@RequestMapping(value = "/getWriteOfflist.json")
	public void getWriteOfflist(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate, Long docId) {
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
			if (docId == null) {
				docId = 0L;
			}
			page.setRows(sourceReportService.getWriteOfflist(strDate, endDate, docId));
		} else {
			page.setRows(sourceReportService.getWriteOfflist(strDate, endDate, loginId));
		}
		request.setAttribute(Constants.pageResultData, page);
	}
	@RequestMapping(value = "/getWriteOfflistToExcell.htm", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response, String strDate, String endDate, Long docId) throws Exception {
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
			if (docId == null) {
				docId = 0L;
			}
			page.setRows(sourceReportService.getWriteOfflist(strDate, endDate, docId));
		} else {
			page.setRows(sourceReportService.getWriteOfflist(strDate, endDate, loginId));
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(("冲销调整明细表.xlsx").getBytes("gb2312"), "iso-8859-1"));
		byte[] bytes = sourceReportService.getCardDetailListExcel3(page.getRows());
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

}
