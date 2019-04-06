package com.enjoyhis.controllers.client;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianConnectionException;
import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.pojo.PatientPj;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.HessianFactoryUtil;

@Controller
@RequestMapping(value = "client/account")
public class AccountManagementController {

	@Autowired
	private HisPatientService hisPatientService;

	@RequestMapping(value = "gotoPage.htm")
	public String gotoPage(Model model, HttpServletRequest request, HttpServletResponse response, Long id) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		PatientPj hpps = null;
		String msg = "";
		try {
			hpps = as.getPatentPj(id);
			if (hpps.getAccountAmount().compareTo(BigDecimal.ZERO) == -1) {
				msg = "(欠费)";
			}
		} catch (HessianConnectionException e) {
			hpps = hisPatientService.getPatientPj(id);
			hpps.setAccountAmount(BigDecimal.ZERO);
			hpps.setOriginalAmount(BigDecimal.ZERO);
			hpps.setGivenAmount(BigDecimal.ZERO);
			msg = "(网络异常)";
		} catch (HessianRuntimeException e) {
			hpps = hisPatientService.getPatientPj(id);
			hpps.setAccountAmount(BigDecimal.ZERO);
			hpps.setOriginalAmount(BigDecimal.ZERO);
			hpps.setGivenAmount(BigDecimal.ZERO);
			msg = "(网络异常)";
		} catch (NullPointerException e) {
			hpps = hisPatientService.getPatientPj(id);
			hpps.setAccountAmount(BigDecimal.ZERO);
			hpps.setOriginalAmount(BigDecimal.ZERO);
			hpps.setGivenAmount(BigDecimal.ZERO);
			msg = "(集团无此患者)";
		}
		Long operator = CookieUtil.getCookieId(request, "ID");
		model.addAttribute("isDoc", hisPatientService.isDoc(operator));
		model.addAttribute("patient", hpps);
		model.addAttribute("msg", msg);
		return "client/patient/accountManagement";
	}

	@RequestMapping(value = "getList.json")
	public void getList(HttpServletRequest request, HttpServletResponse response, Long patientId) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(as.getHisStatementList(patientId));
		request.setAttribute(Constants.pageResultData, page);
	}
	// @RequestMapping(value = "getList.json")
	// public void getList(HttpServletRequest request, HttpServletResponse response, Long
	// patientId) {
	// PageResultForBootstrap page = new PageResultForBootstrap();
	// page.setRows(hisPatientService.getHisStatementList(patientId));
	// request.setAttribute(Constants.pageResultData, page);
	// }
}