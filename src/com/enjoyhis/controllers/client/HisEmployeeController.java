package com.enjoyhis.controllers.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisDepart;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.pojo.Employee;
import com.enjoyhis.rmiclient.IHisEmployee;
import com.enjoyhis.service.AdminRoleService;
import com.enjoyhis.service.HisDepartService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisOrganizService;
import com.enjoyhis.service.HisRegisterService;
import com.enjoyhis.service.HisStationService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;

/**
 * @Title: HisEmployeeController
 * @Description: 人员维护
 * @Version: （版本号）
 * @Create Date: （创建日期）
 */
@Controller
@RequestMapping(value = "/client/employeeinfo")
public class HisEmployeeController {

	final Logger logger = LoggerFactory.getLogger(HisEmployeeController.class);
	IHisEmployee URL = (IHisEmployee) HessianFactoryUtil.getHessianObj(IHisEmployee.class);
	@Autowired
	private HisEmployeeService hisEmployeeService;
	@Autowired
	private HisDepartService hisDepartService;
	@Autowired
	private HisRegisterService hisRegisterService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private HisStationService hisStationService;
	@Autowired
	private HisOrganizService hisOrganizService;
	
	
	@RequestMapping(value = "/empmaintain_home.htm")
	public String saveEmployee(HttpServletRequest request, HttpServletResponse response) {
		return "client/employeeinfo/empmaintain";
	}

	// 分页数据、列表数据
	@RequestMapping(value = "/findEmployee.json") 
	public void findEmployee(Integer unitCode, Integer serchshow, String mob, String empName, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<HisEmployee> list = hisEmployeeService.page4List( unitCode,serchshow, mob, empName, pageNumber, pageSize);
		Integer count = hisEmployeeService.findCountId( unitCode,serchshow, mob, empName);
//		Integer count = hisEmployeeService.findCountId(record);
		Map<Integer,String> rMap =  adminRoleService.getRoleName();
		Map<Integer,String> dMap = hisDepartService.getDepartName();
		Map<Integer,String> sMap = hisStationService.getStationName();
		Map<String,String> oMap = hisOrganizService.getMap();
		
		List<Employee> lists = new ArrayList<>();
		for (HisEmployee entity : list) {
			Employee el = new Employee();
			BeanCopyUtil.copyProperties(entity, el);
			el.setDepName(dMap.get(el.getDepartId()));
			el.setRoleName(rMap.get(el.getRoleId()));
			el.setUserTypeName(sMap.get(el.getUserType()));
			el.setUnitName(oMap.get(el.getUnitCode().toString()));
			lists.add(el);
		}

		// 返回数据以供多少
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(lists);
		request.setAttribute(Constants.pageResultData, page);
	}

	// 获取部门名称
	public Employee copyPro(HisEmployee formPro) {
		Employee employee = new Employee();
		BeanCopyUtil.copyProperties(formPro, employee);
		HisDepart depart = hisDepartService.findById(formPro.getDepartId());
		if (depart != null) {
			employee.setDepName(depart.getDepName());
		}
		return employee;
	}

	/**
	 * @Description 增加新雇员
	 * @param hisEmployee
	 * @param request
	 * @param response
	 * @return "client/employeeinfo/empmaintain"
	 */
	@RequestMapping(value = "/emp_add.htm")
	public void addEmpInfo(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer uc = hisEmployee.getUnitCode();// 院区码
		hisEmployee.setUnitCode(uc);
		hisEmployee.setCreateTime(new Date());
		hisEmployee.setModifyTime(new Date());
		hisEmployee.setLastvistTime(new Date());// 上次登录时间
		int num = 0;
		try {
			num = URL.addEmployeeFy(hisEmployee);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
		}
		response.getWriter().println(num);
	}

	// 校验ID
	@RequestMapping(value = "check_id.htm")
	public void checkUser(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = hisEmployee.getId();
		if (id != null) {
			String flag = "";
			try {
				flag = URL.checkById(id);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
			response.getWriter().println(flag);
		} else {
			response.getWriter().println("");
		}
	}

	@RequestMapping(value = "find_em_all.json")
	public void findEmAll(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = hisEmployee.getId();
		if (id != null) {
			hisEmployee = hisEmployeeService.findById(id);
		}
		request.setAttribute(Constants.jsonResultData, hisEmployee);
		request.setAttribute(Constants.jsonResultCode, 0);

	}

	@RequestMapping(value = "modify_show_status.json")
	public void modifyShowStatus(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Long id = hisEmployee.getId();
//		// hisEmployee.setIsShow(hisEmployee.getIsShow());
//		hisEmployee = hisEmployeeService.findById(id);
//		Integer isshow = hisEmployee.getIsShow();
//		if (isshow == 1) {
//			isshow = 0;
//			hisEmployee.setIsShow(isshow);
//		} else {
//			isshow = 1;
//			hisEmployee.setIsShow(isshow);
//		}
//		hisEmployee.setId(id);
//		int num = 0;
//		if (id != null) {
//
//			int nums = hisEmployeeService.UpdateEmployee(hisEmployee);
//			if (nums == 1) {
//				num = nums;
//			}
//			int urlnums = 0;
//			try {
//				urlnums = URL.updateEmployee(hisEmployee);
//			} catch (HessianRuntimeException e) {
//				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
//				e.printStackTrace();
//			}
//			if (urlnums == 1) {
//				num = urlnums;
//			}
//		}
		int num = hisEmployeeService.UpdateEmployee(hisEmployee,1);
		response.getWriter().println(num);
	}

	/**
	 * @Description 集团同步到分院
	 * @param hisDocroom
	 * @param request
	 * @param respons
	 */
	@Transactional
	public int JtTOFy() {

		List<HisEmployee> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
//		String sessionCode = request.getSession().getAttribute("login_unit_code").toString();
//		Integer ucode = Integer.parseInt(sessionCode);
		for (HisEmployee entiyjt : listjt) {
			
//			Date modifyTimeJts = entiyjt.getModifyTime();
			Long idjt = entiyjt.getId();
//			String pass = entiyjt.getPassword();
			// 用集团的id查分院
			HisEmployee listfy = hisEmployeeService.findById(idjt);
//			entiyjt.setId(idjt);
			
			if (listfy != null) {
				listfy.setId(entiyjt.getId());
				listfy.setEmployeeName(entiyjt.getEmployeeName());
				listfy.setCreateTime(entiyjt.getCreateTime());
				listfy.setUnitCode(entiyjt.getUnitCode());
				listfy.setUserType(entiyjt.getUserType());
				listfy.setIslogin(entiyjt.getIslogin());
				listfy.setMobile(entiyjt.getMobile());
				listfy.setPassword(entiyjt.getPassword());
				listfy.setLastvistTime(entiyjt.getLastvistTime());
				listfy.setRoleId(entiyjt.getRoleId());
				listfy.setStatus(entiyjt.getStatus());
				listfy.setModifyTime(entiyjt.getModifyTime());
				listfy.setImgUrl(entiyjt.getImgUrl());
//				listfy.setCookie(entiyjt.getCookie());
				listfy.setDepartId(entiyjt.getDepartId());
				listfy.setDocroomId(entiyjt.getDocroomId());
				listfy.setDiscRate(entiyjt.getDiscRate());
//				listfy.setIsShow(entiyjt.getIsShow());
				listfy.setTitle(entiyjt.getTitle());
				num = hisEmployeeService.UpdateEmployee(listfy);
				num = num/num;
//				Date modifyTimeFys = listfy.getModifyTime();
//				// 判断集团密码与分院是否相同，不同说明分院已被修改，此时要以分院修改的为准
//				if (pass != (listfy.getPassword())) {
//					entiyjt.setPassword(listfy.getPassword());
//					entiyjt.setModifyTime(new Date());
//					num = URL.updateEmployee(entiyjt);
//					num = hisEmployeeService.UpdateEmployee(entiyjt);
//				} else {
//					// 此处时间判断不兼容性高，带完善
//					if (modifyTimeFys != null && modifyTimeJts != null) {
//						if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//							entiyjt.setModifyTime(new Date());
//							num = hisEmployeeService.UpdateEmployee(entiyjt);
//						}
//					}
//				}
			} else {// 当ID不存在的时候插入当前数据
				entiyjt.setIsShow(0);
				num = hisEmployeeService.addEmployeeFy(entiyjt);
				num = num/num;
			}
		}
		return num;
	}

	/**
	 * 查询当前医院的医生
	 * 
	 * @author wujiaxing
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getDoctorList.json")
	public void getDoctorList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		List<HisEmployee> hisEmployeeList = new ArrayList<>();
		String bookingDate = request.getParameter("bookingDate");
		if (StringUtils.isNotEmpty(bookingDate)) {
			hisEmployeeList = hisRegisterService.findBookingDocsByDate(DateUtils.str2Date(bookingDate, DateUtils.date_sdf));
		} else {
			HisEmployee hisEmployee = new HisEmployee();
			hisEmployee.setUserType(1);
			hisEmployee.setIsShow(1);
			hisEmployee.setLimitStart(0L);
			hisEmployee.setLimitCount(50);
			// hisEmployee.setUnitCode(Integer.valueOf(sysConfigService.getSysConfig().get("local_unit")));
			hisEmployeeList = hisEmployeeService.selectEmployeeList(hisEmployee);
		}
		writer.print(JSONUtils.toJSONString(hisEmployeeList));
	}

	@RequestMapping(value = "/modify_info.json")
	public void modifyStation(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("id");
		if (ids == null) {
			request.setAttribute(Constants.jsonResultCode, -2);
			return;
		}

		Long id = Long.parseLong(ids);
		HisEmployee hisEmployees = hisEmployeeService.findById(id);
		request.setAttribute(Constants.jsonResultData, hisEmployees);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "/findEntityInfos.json")
	public void findEntitys(HisEmployee hisEmployee, HttpServletRequest request, HttpServletResponse respons) {
		List<HisEmployee> list = hisEmployeeService.selectEmployeeList(hisEmployee);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "getEmployeeList.json")
	public void getEmployeeList(HttpServletRequest request, HttpServletResponse response, String pym) {
		if (pym == null) {
			return;
		}
		HisEmployee hisEmployee = new HisEmployee();
		hisEmployee.setStatus(1);
		hisEmployee.setIsShow(1);
		hisEmployee.setSqlStr(" and employee_name like '%" + pym + "%'");
		hisEmployee.setLimitStart(0L);
		hisEmployee.setLimitCount(5);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisEmployeeService.getSearchItemList(hisEmployee));
		request.setAttribute(Constants.pageResultData, page);
	}

}
