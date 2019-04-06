package com.enjoyhis.controllers.client;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.pojo.HisPatientPojo;
import com.enjoyhis.pojo.PatientCase;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.service.HispatientCaseService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
public class patientCaseController {

	@Autowired
	private HisPatientService hisPatientService;
	@Autowired
	private HisEmployeeService hisEmployeeService;
	@Autowired
	private HispatientCaseService hispatientCaseService;

	HesPatientService hessianPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);

	Logger log = LogUtils.CLIENT_TRACE;

	/**
	 * 添加患者页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/case/patientcase_add.htm", method = RequestMethod.GET)
	public String patientAdd(Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		model.addAttribute("uid", uid);
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		return "client/patient/patient_add";
	}

	@RequestMapping(value = "/client/case/patientcase_update.htm", method = RequestMethod.GET)
	public String patientUpdate(Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		model.addAttribute("uid", uid);
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		HisPatientPojo patient = hisPatientService.findById(id);
		model.addAttribute("patient", patient);
		return "client/patient/patient_update";
	}

	@RequestMapping(value = "/client/case/patientcase/save", method = RequestMethod.POST)
	public void save(Long pid, Long docId, String tooth, String zs, String xbs, String jwbs, String jc, String zd, String cl, String yz, String jzsx, String remark, String nowdate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int code = 0;
		boolean flag = false;
		// 参数验证
		try {
			flag = hispatientCaseService.save(pid, docId, tooth, zs, xbs, jwbs, jc, zd, cl, yz, jzsx, remark, nowdate);
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	@RequestMapping(value = "/client/case/case_record_view.htm", method = RequestMethod.GET)
	public String caseRecordView(Long pid, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());

		HisPatient patient = null;
		try {
			patient = hessianPatientService.findById(pid);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
		}
		if (patient == null) {
			patient = hisPatientService.findById(pid);
		}

		model.addAttribute("patient", patient);
		return "client/patient/case_record";
	}

	/**
	 * 查询患者就诊记录(电子病例)
	 * 
	 * @author tianfei
	 * @param pid 患者id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/case/patient_case_data.json", method = RequestMethod.POST)
	public void patientCaseData(Long pid, Long maindocId, HttpServletRequest request, HttpServletResponse response) {
		List<PatientCase> list = hispatientCaseService.patientCaseByPid(pid, maindocId);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(list.size());
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "/client/case/case_add_view.htm", method = RequestMethod.GET)
	public String caseAddView(Long pid, Model model, HttpServletRequest request, HttpServletResponse response) {
		HisPatientPojo patient = hisPatientService.findById(pid);
		model.addAttribute("patient", patient);
		model.addAttribute("nowdate", com.enjoyhis.util.DateUtils.formatTime());
		return "client/patient/case_add";
	}

	@RequestMapping(value = "/client/case/case_detail_view.htm", method = RequestMethod.GET)
	public String caseDetailView(Long caseId, Model model, HttpServletRequest request, HttpServletResponse response) {
		HisPatientCase hisPatientCase = hispatientCaseService.findById(caseId);
		HisPatient patient = null;
		try {
			patient = hessianPatientService.findById(hisPatientCase.getPatId());
		} catch (HessianRuntimeException e) {
		}
		if(patient == null) {
			patient = hisPatientService.findById(hisPatientCase.getPatId());
		}
		model.addAttribute("patient", patient);
		model.addAttribute("hisPatientCase", hisPatientCase);
		model.addAttribute("jldate", com.enjoyhis.util.DateUtils.date2Str(hisPatientCase.getBlDate(), com.enjoyhis.util.DateUtils.time_sdf));
		return "client/patient/case_detail";
	}

}
