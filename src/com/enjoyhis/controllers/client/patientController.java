package com.enjoyhis.controllers.client;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.pojo.HisPatientPojo;
import com.enjoyhis.pojo.Register;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.rmiclient.HesRegisterService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.service.HisMqService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;

/**
 * 
 * @author tianfei
 *
 */
@Controller
public class patientController {

	@Autowired
	private HisPatientService hisPatientService;
	@Autowired
	private HisEmployeeService hisEmployeeService;
	@Autowired
	private HisMqService mqService;

	HesPatientService hessianPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);
	HesRegisterService hessianRegisterService = (HesRegisterService) HessianFactoryUtil.getHessianObj(HesRegisterService.class);

	Logger log = LogUtils.CLIENT_TRACE;

	/**
	 * 查询患者列表(C端调B端接口)
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/patient/query4list.json", method = RequestMethod.POST)
	public void query4List(String data, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = JSONUtils.toJSONObject(data);
		String name = (String) jsonObject.get("name");
		String mobile = (String) jsonObject.get("mobile");
		List<Object> list = hisPatientService.page4List(name, mobile, null, null, null);
		System.out.println("patientController.query4List()" + list.size());
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 患者管理页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/patient/patient_manager.htm", method = RequestMethod.GET)
	public String patientManager(String uid, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		if (null == cookie) {
			Cookie cookie2 = new Cookie("ID", uid);
			cookie2.setMaxAge(60 * 60 * 24);// 过期时间为24小时
			cookie2.setPath("/");
			response.addCookie(cookie2);
			uid = cookie2.getValue();
		}
		if (StringUtils.isBlank(uid)) {
			Cookie cookie3 = CookieUtil.getCookieByName(request, "ID");
			uid = cookie3.getValue();
		}
		HisEmployee hisEmployee = hisEmployeeService.findById(Long.parseLong(uid));
		model.addAttribute("userType", hisEmployee.getUserType());

		if (hisEmployee.getUserType() == 1) {// 医生工作站
			model.addAttribute("dentistId", uid);
		} else {// 护士工作站
			model.addAttribute("dentistId", null);
		}
		return "client/patient/patient_manager";
	}

	/**
	 * 添加患者页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/patient/patient_add.htm", method = RequestMethod.GET)
	public String patientAdd(Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		// Long uid = 101L;
		model.addAttribute("uid", uid);
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		return "client/patient/patient_add";
	}

	@RequestMapping(value = "/client/patient/patient_update.htm", method = RequestMethod.GET)
	public String patientUpdate(Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		model.addAttribute("uid", uid);
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());

		HisRegister register = null;
		try {
			register = hessianRegisterService.findNextBooking(id);
		} catch (HessianRuntimeException e1) {
			log.error("hession掉服务器接口错误");
		}

		HisPatientPojo patient = null;
		try {
			patient = (HisPatientPojo) hessianPatientService.findById(id);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
		}
		if (patient == null || patient.getPatName() == null) {
			patient = hisPatientService.findById(id);
		}
		if (patient != null) {
			if (register != null) {
				patient.setFmtlastappointDate(DateUtils.date_sdf.format(register.getBeginTime()));
			} else {
				patient.setFmtlastappointDate(null);
			}
		}
		model.addAttribute("patient", patient);
		return "client/patient/patient_update";
	}

	@RequestMapping(value = "/client/patient/save", method = RequestMethod.POST)
	public void save(Long id, String patName, String patNo, String userSex, String persid, Integer age, String birthday, String mobile, String tel, String email, String address, String source, String introducer, Long maindocId, String newlyAsk, String allergicHis, String remark, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int code = 0;
		boolean flag = false;
		// 参数验证
		try {
			if (StringUtils.isBlank(patName) || StringUtils.isBlank(userSex.toString()) || StringUtils.isBlank(mobile) || StringUtils.isBlank(source)) {
				code = -2;
			} else {
				if (null == id || StringUtils.isBlank(id.toString())) {// 添加操作
					HisPatient patient = new HisPatient(id, patName, null, null, null, null, mobile, patNo, userSex, age, DateUtils.str2Date(birthday, DateUtils.date_sdf), maindocId, null, null, null, null, null, remark, source, address, newlyAsk, allergicHis, null, null, null, null, null, introducer, null, tel, email, persid, null);
					HisPatient hispatient = hisPatientService.save(1, patient);
					try {
						hessianPatientService.insertPatient2jt(hispatient);
						flag = true;
					} catch (HessianRuntimeException e) {
						log.error("hession掉服务器接口错误");
						mqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
						flag = true;
					}
				} else {// 修改操作
					HisPatient hisPatient = hisPatientService.update(id, patName, patNo, userSex, persid, age, birthday, mobile, tel, email, address, source, introducer, maindocId, newlyAsk, allergicHis, remark);
					try {
						hessianPatientService.insertPatient2jt(hisPatient);
						flag = true;
					} catch (HessianRuntimeException e) {
						log.error("hession掉服务器接口错误");
						mqService.mqMsgToJt(hisPatient.getId(), HisMqEnum.his_patient);
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	/**
	 * 查询患者管理列表
	 * 
	 * @param name 患者姓名
	 * @param mobile 电话
	 * @param docId 医生id
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/patient/patient_list.json", method = RequestMethod.POST)
	public void patientList(String name, String mobile, Long docId, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<Object> list = hisPatientService.page4List(name, mobile, docId, pageNumber, pageSize);
		Integer count = hisPatientService.query4Count(name, mobile, docId);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "/client/patient/patient_register_view.htm", method = RequestMethod.GET)
	public String patientRegisterView(Long pid, Model model, HttpServletRequest request, HttpServletResponse response) {
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
		return "client/patient/patient_register";
	}

	@RequestMapping(value = "/client/patient/case_record_view.htm", method = RequestMethod.GET)
	public String caseRecordView(Long pid, Model model, HttpServletRequest request, HttpServletResponse response) {
		HisPatientPojo patient = hisPatientService.findById(pid);
		model.addAttribute("patient", patient);
		return "client/patient/case_record";
	}

	/**
	 * 查询患者就诊记录
	 */
	@RequestMapping(value = "/client/patient/patient_register.json", method = RequestMethod.POST)
	public void patientRegister(Long pid, Long maindocId, HttpServletRequest request, HttpServletResponse response) {
		List<Register> list = hisPatientService.registerByPid(pid, maindocId);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(list.size());
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

}
