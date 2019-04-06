package com.enjoyhis.controllers.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Name: HisDataSynchMenu
 * @Description: 负责各个模块的数据同步
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 * @Parameters: 参数
 */
@Controller
@RequestMapping("/client/datasynchronization")
public class HisDataSynchMenu {
	final Logger logger = LoggerFactory.getLogger(HisDataSynchMenu.class);
	@Autowired
	private HisServiceItemFlXmController hisServiceItemFlXmController;
	@Autowired
	private HisEmployeeController hisEmployeeController;
	@Autowired
	private HisDepartController hisDepartController;
	@Autowired
	private HisDictController hisDictController;
	@Autowired
	private HisPrepayactController hisPrepayactController;
	@Autowired
	private HisStationController hisStationController;
	@Autowired
	private HisXtXrayController hisXtXrayController;
	@Autowired
	private HisOrganizController hisOrganizController;
	@Autowired
	private HisPaymentController hisPaymentController;
	@Autowired
	private HisServiceItemFlController hisServiceItemFlController;
	@Autowired
	private SysConfigController sysConfigController;
	@Autowired
	private HisGroupController hisGroupController;
	@Autowired
	private HisGroupEmployeeController hisGroupEmployeeController;
	/**
	 * 此方法为解锁扎帐 非同步处理
	 */
	@Autowired
	private ProcessController processController;
	

	@RequestMapping("/synchmenulist.htm")
	private String dataSynch() {
		return "/client/datasynchronization/synchmenulist";
	}

	// 数据同步
	@RequestMapping("synch.htm")
	public void JtDataTOFy(Integer type, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		if (type == 1)// 三级处置项
			response.getWriter().println(hisServiceItemFlXmController.JtDataTOFy());
		else if (type == 0)// 二级处置项
			response.getWriter().println(hisServiceItemFlController.JtDataTOFyFl());
		else if (type == 2)// 人员维护
			response.getWriter().println(hisEmployeeController.JtTOFy());
		else if (type == 3)
			System.err.println("初始化云端");
		else if (type == 4)// 管理配置同步
			response.getWriter().println(sysConfigController.JtSysConfigTOFy());
		else if (type == 5)
			response.getWriter().println(hisDepartController.JtDataTOFy());
		else if (type == 6)
			System.err.println("权限同步");
		else if (type == 7)// 患者来源
			response.getWriter().println(hisDictController.JtDataTOFy());
		else if (type == 8)// 充值活动
			response.getWriter().println(hisPrepayactController.JtTOFy());
		else if (type == 9)
			response.getWriter().println(hisStationController.JtDataTOFy());
		else if (type == 10)
			response.getWriter().println(hisXtXrayController.JtToFy());
		else if (type == 11)
			response.getWriter().println(hisOrganizController.JtToFy());
		else if (type == 12)
			response.getWriter().println(hisPaymentController.JtToFy());
		else if (type == 13)
			response.getWriter().println(hisGroupController.JtToFy());
		else if (type == 14)
			response.getWriter().println(hisGroupEmployeeController.JtToFy());
		else if (type == 99)
			response.getWriter().println(processController.Upadte());
	}
}
