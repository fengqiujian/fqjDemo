package com.enjoyhis.controllers.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.dao.HisEmployeeBookingitemMapper;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisEmployeeBookingitem;
import com.enjoyhis.persistence.his.po.HisOrganiz;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.pojo.BookingInfo;
import com.enjoyhis.pojo.BookingPlan;
import com.enjoyhis.pojo.HisPatientPojo;
import com.enjoyhis.pojo.Register;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.rmiclient.HesRegisterService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisMqService;
import com.enjoyhis.service.HisOrganizService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.service.HisRegisterService;
import com.enjoyhis.service.KeyValueMapService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.service.SysSeqService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisEnum;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;
import com.enjoyhis.util.PropertiesUtil;
import com.enjoyhis.util.SMSUtil;

/**
 * 挂号、预约
 */
@Controller
public class registerController {

	@Autowired
	private HisRegisterService hisRegisterService;// 挂号、预约service
	@Autowired
	private HisPatientService hisPatientService;// 患者service
	@Autowired
	private HisEmployeeService hisEmployeeService;// 医生、护士service
	@SuppressWarnings("rawtypes")
	@Autowired
	private KeyValueMapService keyValueMapService;
	@Autowired
	private HisMqService mqService;
	@Autowired
	private SysConfigService sysConfigService;// 院区service
	@Autowired
	private HisOrganizService hisOrganizService;// 组织机构service
	@Autowired
	private SysSeqService sysSeqService;

	@Autowired
	private HisEmployeeBookingitemMapper employeeBookingitemMapper;

	private HesPatientService hesPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);
	private HesRegisterService hesRegisterService = (HesRegisterService) HessianFactoryUtil.getHessianObj(HesRegisterService.class);

	public static Logger log = LogUtils.CLIENT_TRACE;

	/**
	 * 今日就诊页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/register_index.htm", method = RequestMethod.GET)
	public String registerIndex(String uid, Model model, HttpServletRequest request, HttpServletResponse response) {
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
//		return "client/register/register_index1";

		HisEmployee hisEmployee = hisEmployeeService.findById(Long.parseLong(uid));
		model.addAttribute("userType", hisEmployee.getUserType());
		if (hisEmployee.getUserType() == 1) {
			// 医生
			String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
			model.addAttribute("callNoIp", callNoIp);
			model.addAttribute("dentistId", Long.parseLong(uid));
		} else {
			// 护士工作站
			model.addAttribute("dentistId", null);
		}
		return "client/register/register_index";
	}

//	@RequestMapping(value = "/client/register/register_index2.htm", method = RequestMethod.GET)
//	public String registerIndex2(String uid, Model model, HttpServletRequest request, HttpServletResponse response) {
//		
//		
//		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
//		if (null == cookie) {
//			Cookie cookie2 = new Cookie("ID", uid);
//			cookie2.setMaxAge(60 * 60 * 24);// 过期时间为24小时
//			cookie2.setPath("/");
//			response.addCookie(cookie2);
//			uid = cookie2.getValue();
//		}
//		if (StringUtils.isBlank(uid)) {
//			Cookie cookie3 = CookieUtil.getCookieByName(request, "ID");
//			uid = cookie3.getValue();
//		}
//		
//		HisEmployee hisEmployee = hisEmployeeService.findById(Long.parseLong(uid));
//		model.addAttribute("userType", hisEmployee.getUserType());
//		if (hisEmployee.getUserType() == 1) {
//			// 医生
//			String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
//			model.addAttribute("callNoIp", callNoIp);
//			model.addAttribute("dentistId", Long.parseLong(uid));
//		} else {
//			// 护士工作站
//			model.addAttribute("dentistId", null);
//		}
//		return "client/register/register_index";
//	}
	
	/**
	 * 历史就诊记录页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/register_history.htm", method = RequestMethod.GET)
	public String registerHistory(Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		if (hisEmployee.getUserType() == 1)// 医生工作站
			model.addAttribute("dentistId", uid);
		else// 护士工作站
			model.addAttribute("dentistId", null);
		return "client/register/register_history";
	}

	/**
	 * 今日工作（护士站）
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/today_work_view.htm", method = RequestMethod.GET)
	public String todayWorkView(String uid, Model model, HttpServletRequest request, HttpServletResponse response) {
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

		model.addAttribute("yesterday", DateUtils.getYesterday());
		model.addAttribute("today", DateUtils.timestamptoStr(null));
		model.addAttribute("tomorrow", DateUtils.getTomorrow());

		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
		HisOrganiz hisOrganiz = hisOrganizService.findById(unitCode);
		model.addAttribute("hisOrganiz", hisOrganiz);
		return "client/register/today_work";
	}

	/**
	 * X光工作站
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/xws_register_index.htm", method = RequestMethod.GET)
	public String xwsRegisterIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "client/register/xws_register_index";
	}

	/**
	 * 查询今日就诊列表
	 * 
	 * @param name 患者姓名
	 * @param mobile 电话
	 * @param type 全部：0 、 预约:1、 挂号:2 待收费:3 已完成:4
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/find_register.json")
	public void findRegister(Integer userType, Long dentistId, String dateTime, String name, String mobile, Integer type, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		try {
			String sortName = request.getParameter("sortName");
			String sortOrder = request.getParameter("sortOrder");
			if (StringUtils.isNotBlank(sortName) && sortName.equals("status")) {
				sortName = "a.status";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("serviceItems")) {
				sortName = "a.service_items";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("source")) {
				sortName = "b.source";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("beginTime")) {
				sortName = "a.begin_time";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("ghTime")) {
				sortName = "a.gh_time";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("isnew")) {
				sortName = "a.isnew";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("docName")) {
				sortName = "c.employee_name";
			} else if (StringUtils.isNotBlank(sortName) && sortName.equals("patName")) {
				sortName = "b.pat_name";
			} else {
				sortOrder = null;
			}
			Date date = DateUtils.str2Date(dateTime, DateUtils.date_sdf);
			List<Object> list = hisRegisterService.findRegister(dentistId, date, name, mobile, type, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
			int selectCount = hisRegisterService.selectCount(dentistId, date, name, mobile, type, null == userType ? 2 : userType);
			PageResultForBootstrap page = new PageResultForBootstrap();
			page.setTotal(selectCount);
			page.setRows(list);
			request.setAttribute(Constants.pageResultData, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/client/register/find_register_count.json", method = RequestMethod.POST)
	public void findRegisterCount(Integer userType, Long dentistId, String dateTime, String name, String mobile, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		Date date = DateUtils.str2Date(dateTime, DateUtils.date_sdf);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		String sortName = request.getParameter("sortName");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortName) && sortName.equals("status")) {
			sortName = "a.status";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("serviceItems")) {
			sortName = "a.service_items";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("source")) {
			sortName = "b.source";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("beginTime")) {
			sortName = "a.begin_time";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("ghTime")) {
			sortName = "a.gh_time";
		} else {
			sortOrder = null;
		}
		// type 全部：0 、 预约:1、 挂号:2 待收费:3 已完成:4
		List<Object> list = hisRegisterService.findRegister(dentistId, date, name, mobile, 0, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list.size());
		List<Object> list2 = hisRegisterService.findRegister(dentistId, date, name, mobile, 2, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list2.size());
		List<Object> list3 = hisRegisterService.findRegister(dentistId, date, name, mobile, 1, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list3.size());
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count1", list.size());
		map.put("count2", list2.size());
		map.put("count3", list3.size());
		request.setAttribute(Constants.jsonResultData, map);
	}

	@RequestMapping(value = "/client/register/find_todaywork_count.json", method = RequestMethod.POST)
	public void findTodayworkCount(Integer userType, Long dentistId, String dateTime, String name, String mobile, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		Date yesterday = DateUtils.str2Date(DateUtils.getYesterday(), DateUtils.date_sdf);
		Date today = DateUtils.str2Date(DateUtils.timestamptoStr(null), DateUtils.date_sdf);
		Date tomorrow = DateUtils.str2Date(DateUtils.getTomorrow(), DateUtils.date_sdf);
		if (pageNumber == null) {
			pageNumber = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		String sortName = request.getParameter("sortName");
		String sortOrder = request.getParameter("sortOrder");
		if (StringUtils.isNotBlank(sortName) && sortName.equals("status")) {
			sortName = "a.status";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("serviceItems")) {
			sortName = "a.service_items";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("source")) {
			sortName = "b.source";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("beginTime")) {
			sortName = "a.begin_time";
		} else if (StringUtils.isNotBlank(sortName) && sortName.equals("ghTime")) {
			sortName = "a.gh_time";
		} else {
			sortOrder = null;
		}
		// type 全部：0 、 预约:1、 挂号:2 待收费:3 已完成:4
		List<Object> list = hisRegisterService.findRegister(dentistId, yesterday, name, mobile, null, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list.size());
		List<Object> list2 = hisRegisterService.findRegister(dentistId, today, name, mobile, 1, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list2.size());
		List<Object> list3 = hisRegisterService.findRegister(dentistId, tomorrow, name, mobile, 1, pageNumber, pageSize, null == userType ? 2 : userType, sortName, sortOrder);
		// System.out.println(list3.size());
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("count1", list.size());
		map.put("count2", list2.size());
		map.put("count3", list3.size());
		request.setAttribute(Constants.jsonResultData, map);
	}

	/**
	 * 挂号页面
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/register_view.htm", method = RequestMethod.GET)
	public String registerView(String uid, Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
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

		if (StringUtils.isNotBlank(uid)) {
			getOftenItems(model, Long.valueOf(uid));
			HisEmployee employee = hisEmployeeService.findById(Long.valueOf(uid));
			model.addAttribute("employee", employee);
			model.addAttribute("userType", employee.getUserType());
			if (employee.getUserType() == 1) {
				// 医生
				String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
				model.addAttribute("callNoIp", callNoIp);
				model.addAttribute("dentistId", Long.parseLong(uid));
			} else {
				// 护士工作站
				model.addAttribute("dentistId", null);
			}
		}
		Register register = new Register();
		if (null != id) {
			register = hisRegisterService.findById(id);
			// 需要判断当前挂号单状态
			if (register != null && register.getStatus() != 2) {
				return "client/register/register_index";
			}
		}

		model.addAttribute("register", register);
		String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
		model.addAttribute("callNoIp", callNoIp);
		if (null != id && HisEnum.REGISTER.key == register.getStatus()) {
			return "client/register/update_register";
		}
		if (null != register.getId()) {
			return "client/register/update_register";
		}
		model.addAttribute("callNoIp", callNoIp);
		return "client/register/register";
	}

	@RequestMapping(value = "/client/register/booking2register.htm", method = RequestMethod.GET)
	public String booking2register(String uid, Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
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
		Register register = new Register();
		if (null != id) {
			register = hisRegisterService.findById(id);
		}
		HisEmployee employee = hisEmployeeService.findById(Long.valueOf(uid));
		model.addAttribute("employee", employee);
		getOftenItems(model, Long.valueOf(uid));
		model.addAttribute("register", register);
		String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
		model.addAttribute("callNoIp", callNoIp);
		if (null != id && HisEnum.REGISTER.key == register.getStatus()) {
			return "client/register/booking2register";
		}
		if (null != register.getId()) {
			return "client/register/booking2register";
		}
		return "client/register/register";
	}

	@RequestMapping(value = "/client/register/save", method = RequestMethod.POST)
	public void save(HisPatient hisPatient, HisRegister hisRegister, String strBirthday, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 正式环境放开以下代码
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long userId = Long.parseLong(cookie.getValue());
		int code = 0;
		boolean flag = false;
		Map<String, Object> returnData = new HashMap<>();
		// 参数验证
		try {
			hisPatient.setId(hisRegister.getPatId());
			String replaceSource = hisPatient.getSource().replace(",", "");
			hisPatient.setSource(replaceSource);
			hisRegister.setSource(replaceSource);
			if (StringUtils.isBlank(hisPatient.getPatName()) || StringUtils.isBlank(hisPatient.getUserSex().toString()) || StringUtils.isBlank(hisPatient.getMobile()) || StringUtils.isBlank(hisPatient.getSource()) || hisPatient.getSource().equals(",") || StringUtils.isBlank(hisRegister.getServiceItems()) || hisRegister.getDentistId() == null) {
				code = -2;
			} else {
				HisEmployeeBookingitem hisEmployeeBookingitem = new HisEmployeeBookingitem();
				// 记录医生常用预约目的
				hisEmployeeBookingitem.setEmployeeId(userId);
				hisEmployeeBookingitem.setItemName(hisRegister.getServiceItems());
				HisEmployeeBookingitem selectByBean = employeeBookingitemMapper.selectByBean(hisEmployeeBookingitem);
				if (selectByBean == null) {
					employeeBookingitemMapper.insertSelective(hisEmployeeBookingitem);
				}
				List<HisEmployeeBookingitem> itemList = employeeBookingitemMapper.findList(userId);
				if (itemList != null && itemList.size() > 10) {
					HisEmployeeBookingitem hisEmployeeBookingitem2 = itemList.get(itemList.size() - 10);
					employeeBookingitemMapper.deleteByDoctorId(userId, hisEmployeeBookingitem2.getId());
				}

				if (null == hisRegister.getId() || StringUtils.isBlank(hisRegister.getId().toString())) {// 添加操作
					// 第一次就诊医生默认赋予主治医生
					hisPatient.setMaindocId(hisRegister.getDentistId());
					HisPatient patient = hisRegisterService.save(hisPatient, hisRegister, userId, strBirthday);
					returnData.put("patNo", patient.getPatNo());
					flag = true;
				} else {// 修改操作，如果是新用户预约转挂号，则可以修改patient信息，否则不可以修改patient信息
					Register register = hisRegisterService.findById(hisRegister.getId());
					if (register.getStatus() > 2) {
						returnData.put("flag", flag);
						request.setAttribute(Constants.jsonResultData, returnData);
						request.setAttribute(Constants.jsonResultCode, -120);
						return;
					}
					if (register.getStatus() == HisEnum.BOOKING.key && register.getIsnew() == 1) {
						hisRegister.setIsnew(1);
						if (hisPatient.getPatNo().startsWith("TEMP")) {
							Map<String, String> sysConfig = sysConfigService.getSysConfig();
							Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
							Long tempno = sysSeqService.getTableSeq(unitCode, "his_patient_no");
							String patientNo = tempno.toString();
							if (unitCode > 1000) {
								patientNo = patientNo.substring(0, patientNo.length() - unitCode.toString().length());
							} else if (unitCode >= 100) {
								patientNo = patientNo.substring(0, patientNo.length() - 1 - unitCode.toString().length());
							} else if (unitCode >= 10) {
								patientNo = patientNo.substring(0, patientNo.length() - 2 - unitCode.toString().length());
							} else {
								patientNo = patientNo.substring(0, patientNo.length() - 3 - unitCode.toString().length());
							}
							if (patientNo.length() > 5) {
								hisPatient.setPatNo("10" + unitCode + DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd).substring(2, 8) + patientNo.substring(patientNo.length() - 5, patientNo.length()));
							} else {
								hisPatient.setPatNo("10" + unitCode + DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd).substring(2, 8) + patientNo);
							}
							Long dentistId = hisRegister.getDentistId();
							HisEmployee hisEmployee = hisEmployeeService.findById(dentistId);
							hisPatient.setMaindocId(dentistId);
							hisPatient.setBirthday(DateUtils.str2Date(strBirthday, DateUtils.date_sdf));
							hisPatient.setMaindocName(hisEmployee.getEmployeeName());
							returnData.put("patNo", hisPatient.getPatNo());
							hisPatient.setModifyTime(DateUtils.getDate());
							hisPatient.setNewlyDate(DateUtils.getNow());
						}
						hisPatientService.updateByPrimaryKeySelective(hisPatient);
						HisPatientPojo hispatient = hisPatientService.findById(hisPatient.getId());
						try {
							hesPatientService.insertPatient2jt(hispatient);
						} catch (HessianRuntimeException e) {
							mqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
							log.error("hession掉服务器接口错误");
						}
					}
					flag = hisRegisterService.update(hisRegister);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		returnData.put("flag", flag);
		request.setAttribute(Constants.jsonResultData, returnData);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	/**
	 * 修改预约前的回显查询
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/client/register/getRegisterInfoById.json")
	public void getRegisterInfoById(HttpServletRequest request, HttpServletResponse response, Long id) {
		Register register = hisRegisterService.findById(id);
		try {
			PrintWriter writer = response.getWriter();
			writer.print(JSONUtils.toJSONString(register));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param id registerID
	 * @param patId 患者ID
	 * @param patName 患者名
	 * @param patNo 病历编号
	 * @param userSex 性别
	 * @param mobile 手机号码
	 * @param age 年龄
	 * @param birthday 生日
	 * @param dentistId 预约医生
	 * @param bookingDate 预约日期
	 * @param begin 预约开始时间
	 * @param end 预约结束时间
	 * @param serviceItems 预约目的
	 * @param remark 备注
	 * @param allergicHis
	 * @param isAppoint 是否预约（1为预约）
	 * @param status 预约状态
	 * @param source 患者来源
	 * @param type 患者类型 （新增的）
	 * @param affirm 是否确认 （新增的）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/client/register/save_booking", method = RequestMethod.POST)
	public void saveBooking(Long id, Long patId, String patName, String patNo, String userSex, String mobile, Integer age, String birthday, Long dentistId, String bookingDate, String begin, String end, String serviceItems, String remark, String allergicHis, Integer isAppoint, Integer status, String source, String type, int affirm, String introducer, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 正式环境放开以下代码
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long userId = Long.parseLong(cookie.getValue());
		int code = 0;
		boolean flag = false;
		String unitCode = request.getParameter("unitCode");
		// 参数验证
		try {
			source = source.replace(",", "");
			if (StringUtils.isBlank(patName) || StringUtils.isBlank(userSex.toString()) || StringUtils.isBlank(mobile) || StringUtils.isBlank(bookingDate.toString()) || StringUtils.isBlank(dentistId.toString()) || StringUtils.isBlank(begin) || StringUtils.isBlank(end) || StringUtils.isBlank(source) || source.equals(",")) {
				code = -2;
			} else {
				HisEmployeeBookingitem hisEmployeeBookingitem = new HisEmployeeBookingitem();
				if (StringUtils.isNotBlank(serviceItems)) {
					// 记录医生常用预约目的
					hisEmployeeBookingitem.setEmployeeId(userId);
					hisEmployeeBookingitem.setItemName(serviceItems);
					HisEmployeeBookingitem selectByBean = employeeBookingitemMapper.selectByBean(hisEmployeeBookingitem);
					if (selectByBean == null) {
						employeeBookingitemMapper.insertSelective(hisEmployeeBookingitem);
					}
				}

				List<HisEmployeeBookingitem> itemList = employeeBookingitemMapper.findList(userId);
				if (itemList != null && itemList.size() > 10) {
					HisEmployeeBookingitem hisEmployeeBookingitem2 = itemList.get(itemList.size() - 10);
					employeeBookingitemMapper.deleteByDoctorId(userId, hisEmployeeBookingitem2.getId());
				}
				if (null == id || StringUtils.isBlank(id.toString())) {// 添加操作
					HisPatient hispatient = hisRegisterService.saveBooking(id, patId, patName, patNo, userSex, mobile, age, birthday, dentistId, bookingDate, begin, end, serviceItems, remark, allergicHis, isAppoint, status, userId, source, type, affirm, introducer, unitCode);
					// mqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
					flag = true;
				} else {// 修改操作
					flag = hisRegisterService.updateBooking(id, patId, patName, patNo, userSex, mobile, age, birthday, dentistId, bookingDate, begin, end, serviceItems, remark, allergicHis, isAppoint, status, userId, source, type, affirm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	@RequestMapping(value = "/client/register/exit_register", method = RequestMethod.POST)
	public void exitRegister(Long id, String remark, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int code = 0;
		boolean flag = false;
		Register register = hisRegisterService.findById(id);
		if (register.getStatus() > 2) {
			request.setAttribute(Constants.jsonResultData, flag);
			request.setAttribute(Constants.jsonResultCode, -120);
			return;
		}
		// 参数验证
		try {
			if (StringUtils.isBlank(id.toString())) {
				code = -2;
			} else {
				flag = hisRegisterService.exitRegister(id, remark);
				log.info("退号结果：", flag);
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	/**
	 * 取消预约操作
	 * 
	 * @param id
	 * @param remark
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/client/register/exit_booking", method = RequestMethod.POST)
	public void exitBooking(Long id, String remark, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int code = 0;
		boolean flag = false;
		// 判断状态
		Register register = hisRegisterService.findById(id);
		if (register.getStatus() > 1) {
			request.setAttribute(Constants.jsonResultData, flag);
			request.setAttribute(Constants.jsonResultCode, 130);
			return;
		}

		// 参数验证
		try {
			if (StringUtils.isBlank(id.toString())) {
				code = -2;
			} else {
				flag = hisRegisterService.exitBooking(id, remark);
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = -2123;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	/**
	 * 选择患者页面
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/select_patient.htm", method = RequestMethod.GET)
	public String selectPatient(Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
		return "client/register/select_patient";
	}

	/**
	 * 查询患者列表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/find_patient.json", method = RequestMethod.POST)
	public void findPatient(String patName, String mobile, Long maindocId, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		/*
		 * List<Object> list = hisPatientService.page4List(patName,mobile,maindocId,pageNumber,
		 * pageSize); Integer count = hisPatientService.query4Count(patName,mobile,maindocId);
		 * PageResultForBootstrap page = new PageResultForBootstrap(); page.setTotal(count);
		 * page.setRows(list);
		 */
		PageResultForBootstrap page = hisPatientService.page4Data(patName, mobile, maindocId, pageNumber, pageSize);
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 更多操作
	 * 
	 * @param registerId
	 * @param type 1新建挂号 2新建预约
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/more_actions.htm", method = RequestMethod.GET)
	public String moreActions(Long registerId, Long pid, Integer type, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		model.addAttribute("uid", uid);
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		model.addAttribute("employee", hisEmployee);
		getOftenItems(model, uid);

		Register register = null;
		if (null != registerId) {
			try {
				register = hesRegisterService.findById(registerId);
				if (register.getPatient() == null) {
					Register fyregister = hisRegisterService.findById(registerId);
					register.setPatient(fyregister.getPatient());
				}
			} catch (HessianRuntimeException e) {
				e.printStackTrace();
			}
			if (register == null) {
				register = hisRegisterService.findById(registerId);
			}
		} else {
			HisPatient hisPatient = null;
			try {
				hisPatient = hesPatientService.findById(pid);
			} catch (HessianRuntimeException e) {
				e.printStackTrace();
			}
			if (hisPatient == null || hisPatient.getPatName() == null) {
				hisPatient = hisPatientService.findById(pid);
			}
			register = new Register();
			register.setPatient(hisPatient);
		}
		LogUtils.CLIENT_TRACE.info("moreActions-------------" + register);
		if (type == 1) {// 预约转挂号
			model.addAttribute("register", register);
			String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
			model.addAttribute("callNoIp", callNoIp);
			return "client/register/update_register";
		} else if (type == 3) {// 新建挂号
			register.setId(null);
			String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
			model.addAttribute("callNoIp", callNoIp);
			model.addAttribute("register", register);
			return "client/register/register2";
		} else if (type == 2) {// 新建预约
			if (hisEmployee.getUserType() == 2) { // 护士
				register.setId(null);
				@SuppressWarnings("rawtypes")
				List doctors = keyValueMapService.query4DropDown("his_employee", "id", "employee_name", "{'where':'user_type=1 and is_show=1'}");
				model.addAttribute("doctors", doctors);
				model.addAttribute("type", 1);
				if (pid != null) {
					model.addAttribute("patId", pid);
				} else {
					model.addAttribute("patId", register.getPatId());
				}
				model.addAttribute("register", register);
				return "client/register/booking_plan";
			} else {
				register.setId(null);
				model.addAttribute("doctorId", hisEmployee.getId());
				if (pid != null) {
					model.addAttribute("patId", pid);
				} else {
					model.addAttribute("patId", register.getPatId());
				}
				model.addAttribute("register", register);
				return "client/register/doctor_plan";
			}
		} else if (type == 4 || type == 5) {
			model.addAttribute("register", register);
			String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");
			model.addAttribute("callNoIp", callNoIp);
			return "client/register/update_register";
		} else if (type == 8) {// 查看挂号
			model.addAttribute("register", register);
			return "client/register/look_register";
		} else if (type == 9) {// 查看预约
			model.addAttribute("register", register);
			return "client/register/look_booking";
		} else if (type == 10) {// 修改预约
			@SuppressWarnings("rawtypes")
			List doctors = keyValueMapService.query4DropDown("his_employee", "id", "employee_name", "{'where':'user_type=1 and is_show=1'}");
			model.addAttribute("doctors", doctors);
			model.addAttribute("type", 1);
			if (pid != null) {
				model.addAttribute("patId", pid);
			} else {
				model.addAttribute("patId", register.getPatId());
			}
			model.addAttribute("register", register);
			return "client/register/booking_plan";
		} else {
			model.addAttribute("register", register);
			return "client/register/booking_update";
		}
	}

	private void getOftenItems(Model model, Long uid) {
		List<HisEmployeeBookingitem> findList = employeeBookingitemMapper.findList(Long.valueOf(uid));
		model.addAttribute("oftenItems", JSONUtils.toJSONString(findList));
	}

	/**
	 * 全部预约计划页面（护士）
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/booking_plan_view.htm", method = RequestMethod.GET)
	public String bookingPlanView(Model model, HttpServletRequest request, HttpServletResponse response) {
		String bookingDate = request.getParameter("bookingDate");
		if (StringUtils.isNotBlank(bookingDate)) {
			model.addAttribute("bookingDate", bookingDate);
		}
		log.info("全部预约计划页面（护士）被访问");
		@SuppressWarnings("rawtypes")
		List doctors = keyValueMapService.query4DropDown("his_employee", "id", "employee_name", "{'where':'user_type=1 and is_show=1'}");
		model.addAttribute("doctors", doctors);
		Long operator = CookieUtil.getCookieId(request, "ID");
		HisEmployee employee = hisEmployeeService.findById(operator);
		getOftenItems(model, operator);
		model.addAttribute("employee", employee);
		return "client/register/booking_plan";
	}

	/**
	 * 全部预约计划数据获取（护士）
	 * 
	 * @param bookingDate
	 * @param doctors
	 * @param isBookingDocs 是否只查看有预约的医生
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/booking_plan_data.json", method = RequestMethod.POST)
	public void bookingPlanData(String bookingDate, String doctors, boolean isBookingDocs, HttpServletRequest request, HttpServletResponse response) {
		log.info("全部预约计划数据获取（护士）被访问，", bookingDate, doctors, isBookingDocs);
		Date date = DateUtils.str2Date(bookingDate, DateUtils.date_sdf);
		BookingPlan bookingPlan = hisRegisterService.bookingPlanData(date, doctors, isBookingDocs);
		request.setAttribute(Constants.jsonResultData, bookingPlan);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 全部预约计划页面(医生)
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/doctor_plan_view.htm", method = RequestMethod.GET)
	public String doctorPlanView(Long patId, Long doctorId, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		if (doctorId == null || StringUtils.isBlank(doctorId.toString())) {
			doctorId = Long.parseLong(cookie.getValue());
		}

		String bookingDate = request.getParameter("bookingDate");
		if (StringUtils.isNotBlank(bookingDate)) {
			model.addAttribute("bookingDate", bookingDate);
		}
		Long operator = CookieUtil.getCookieId(request, "ID");
		HisEmployee employee = hisEmployeeService.findById(operator);
		getOftenItems(model, operator);
		model.addAttribute("employee", employee);
		model.addAttribute("doctorId", doctorId);
		model.addAttribute("patId", patId);
		return "client/register/doctor_plan";
	}

	@RequestMapping(value = "/client/register/doctor_plan_data.json", method = RequestMethod.POST)
	public void doctorPlanData(String bookingDate, Long doctorId, HttpServletRequest request, HttpServletResponse response) {
		Date date = DateUtils.str2Date(bookingDate, DateUtils.date_sdf);
		Map<String, Object> map = hisRegisterService.doctorPlanData(date, doctorId);
		request.setAttribute(Constants.jsonResultData, map);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 获取所有的预约信息
	 * 
	 * @author wujiaxing
	 * @param beginDate
	 * @param endDate
	 * @param doctorId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/client/register/doctorPlan.json")
	public void doctorPlan(String beginDate, String endDate, String doctorId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)) {
			writer.print(-1);
			return;
		}
		List<BookingInfo> bookingInfoList = new ArrayList<>();
		Date beginTime = DateUtils.str2Date(beginDate, DateUtils.datetimeFormat);
		Date endTime = DateUtils.str2Date(endDate, DateUtils.datetimeFormat);
		if (StringUtils.isNotEmpty(doctorId)) {
			String[] doctorIds = doctorId.split(",");
			bookingInfoList = hisRegisterService.doctorPlanData(beginTime, endTime, doctorIds);
		} else {
			bookingInfoList = hisRegisterService.doctorPlanData(beginTime, endTime, null);
		}
		writer.print(JSONUtils.toJSONString(bookingInfoList));
	}

	@RequestMapping(value = "/client/register/doctorPlanByTime.json")
	public void doctorPlanByTime(String currentDate, String doctorId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		if (StringUtils.isEmpty(currentDate) || StringUtils.isEmpty(doctorId)) {
			writer.print(-1);
			return;
		}
		Date currentTime = DateUtils.str2Date(currentDate, DateUtils.datetimeFormat);
		List<HisRegister> bookingInfoList = hisRegisterService.doctorPlanByTime(currentTime, doctorId);
		writer.print(JSONUtils.toJSONString(bookingInfoList));
	}

	/**
	 * 修改今日工作（护士站）短信或电话回访操作
	 * 
	 * @param registerId
	 * @param type 1:电话回访 2:短信回访
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/update_msg_call.json", method = RequestMethod.POST)
	public void updateMsgOrCall(Long registerId, String mobile, Integer type, HttpServletRequest request, HttpServletResponse response) {
		Register register = hisRegisterService.findById(registerId);
		// HisEmployee employee = hisEmployeeService.findById(register.getDentistId());
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
		HisOrganiz hisOrganiz = hisOrganizService.findById(unitCode);
		StringBuffer msgContent = new StringBuffer();
		// 【欢乐口腔】尊敬的……，您预约了2017年1月3日15：00至欢乐口腔……分院看诊，地址：…… 请您提前安排好时间，如需改约请您致电……
		msgContent.append("尊敬的");
		msgContent.append(register.getPatient().getPatName());
		msgContent.append("您好，您预约了");
		msgContent.append(register.getBeginTime() == null ? DateUtils.date2Str(register.getGhTime(), DateUtils.datetimeFormat) : DateUtils.date2Str(register.getBeginTime(), DateUtils.datetimeFormat));
		msgContent.append("至欢乐口腔");
		msgContent.append(hisOrganiz.getUnitName() + "分院看诊,就诊地址：");
		msgContent.append(hisOrganiz.getAddress());
		msgContent.append("请您提前安排好时间");
		msgContent.append(hisOrganiz.getTel() != null ? ",如需改约请您致电：" + hisOrganiz.getTel() : "");
		if (null != mobile && type == 2) {
			SMSUtil.sendSMS(mobile, msgContent.toString());
		}
		log.info("update_msg_call.json执行，短信内容：" + msgContent);
		boolean flag = true;
		int code = 0;
		try {
			hisRegisterService.updateMsgOrCall(registerId, type);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			code = -1;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	@RequestMapping(value = "/client/register/send_msg.json", method = RequestMethod.POST)
	public void sendMsg(String mobile, Long registerId, String msg, HttpServletRequest request, HttpServletResponse response) {
		log.info("send_msg执行，msg=" + msg);
		if (null != mobile) {
			SMSUtil.sendSMS(mobile, msg);
		}
		boolean flag = true;
		int code = 0;
		try {
			hisRegisterService.updateMsgOrCall(registerId, 2);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
			code = -1;
		}
		request.setAttribute(Constants.jsonResultData, flag);
		request.setAttribute(Constants.jsonResultCode, code);
	}

	/**
	 * 患者统计表（护士站、医生站） 备注：如果是护士站，doctorId的只为null,否则不为空，判断医生还是护士依据userType:1医生 2护士
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/patient_report_view.htm", method = RequestMethod.GET)
	public String patientReportView(Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		// Long uid = 102L;
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		if (null != hisEmployee.getUserType() && hisEmployee.getUserType() == 1)
			model.addAttribute("uid", uid);
		else
			model.addAttribute("uid", null);
		return "/report/patient_report";
	}

	/**
	 * 患者统计表数据（报表）
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param doctorId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/patient_report_data.json", method = RequestMethod.POST)
	public void patientReportData(String beginDate, String endDate, Long doctorId, HttpServletRequest request, HttpServletResponse response) {
		List<Object> list = hisRegisterService.patientReportData(beginDate, endDate, doctorId);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(list.size());
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "/client/register/patient_report_dataToExcell.htm ", method = RequestMethod.GET)
	public ResponseEntity<byte[]> patientReportDataToExcell(String beginDate, String endDate, Long doctorId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Object> list = hisRegisterService.patientReportData(beginDate, endDate, doctorId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(("患者统计表.xlsx").getBytes("gb2312"), "iso-8859-1"));
		byte[] bytes = hisRegisterService.getCardDetailListExcel(list);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

	/**
	 * 预约未到统计表
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/client/register/nonarrival_report_view.htm", method = RequestMethod.GET)
	public String nonarrivalReportView(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "/report/nonarrival_report";
	}

	/**
	 * 预约未到统计表（报表）
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/client/register/nonarrival_report_data.json", method = RequestMethod.POST)
	public void nonarrivalReportData(String beginDate, String endDate, HttpServletRequest request, HttpServletResponse response) {
		List<Object> list = hisRegisterService.nonarrivalReportData(beginDate, endDate);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(list.size());
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "/client/register/nonarrival_report_dataToExcell.htm", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportExcel(String beginDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Object> list = hisRegisterService.nonarrivalReportData(beginDate, endDate);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(("预约未到统计表.xlsx").getBytes("gb2312"), "iso-8859-1"));
		byte[] bytes = hisRegisterService.getCardDetailListExcel2(list);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/client/register/report_index.htm", method = RequestMethod.GET)
	public String reportIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		Long uid = Long.parseLong(cookie.getValue());
		HisEmployee hisEmployee = hisEmployeeService.findById(uid);
		model.addAttribute("userType", hisEmployee.getUserType());
		return "/report/report_index";
	}

}
