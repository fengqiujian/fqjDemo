package com.enjoyhis.controllers.admin;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.enjoyhis.controllers.client.HisDepartController;
import com.enjoyhis.controllers.client.HisDictController;
import com.enjoyhis.controllers.client.HisEmployeeController;
import com.enjoyhis.controllers.client.HisGroupController;
import com.enjoyhis.controllers.client.HisOrganizController;
import com.enjoyhis.controllers.client.HisPaymentController;
import com.enjoyhis.controllers.client.HisPrepayactController;
import com.enjoyhis.controllers.client.HisServiceItemFlController;
import com.enjoyhis.controllers.client.HisServiceItemFlXmController;
import com.enjoyhis.controllers.client.HisStationController;
import com.enjoyhis.controllers.client.SysConfigController;
import com.enjoyhis.persistence.his.dao.HisPatientCaseMapper;
import com.enjoyhis.persistence.his.dao.HisPatientMapper;
import com.enjoyhis.persistence.his.dao.HisRegisterCallMapper;
import com.enjoyhis.persistence.his.dao.HisRegisterMapper;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.rmiclient.HesPatientCaseService;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.rmiclient.HesRegisterService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Component
public class JtToFyJob {

	@Autowired
	private HisServiceItemFlXmController hisServiceItemFlXmController;
	@Autowired
	private HisDepartController hisDepartController;
	@Autowired
	private HisDictController hisDictController;
	@Autowired
	private HisPrepayactController hisPrepayactController;
	@Autowired
	private HisStationController hisStationController;
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
	private HisEmployeeController hisEmployeeController;

	@Autowired
	private HisRegisterMapper hisRegisterMapper;

	@Autowired
	private SysConfigService configService;

	@Autowired
	private HisPatientMapper hisPatientMapper;

	@Autowired
	private HisPatientCaseMapper hisPatientCaseMapper;

	@Autowired
	private HisRegisterCallMapper registerCallMapper;

	HesRegisterService hesRegisterService = (HesRegisterService) HessianFactoryUtil.getHessianObj(HesRegisterService.class);
	HesPatientService hesPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);
	HesPatientCaseService hesPatientCaseService = (HesPatientCaseService) HessianFactoryUtil.getHessianObj(HesPatientCaseService.class);

	Logger logInfo = LogUtils.INFO;

//	@Scheduled(cron = "11 11 1 * * ?")
	@Scheduled(fixedRate = 1000 * 60 * 10)
	public void allTable() {
		logInfo.info("JtToFyJob------------------执行了");
		// 三级处置项
		hisServiceItemFlXmController.JtDataTOFy();
		// 二级处置项
		hisServiceItemFlController.JtDataTOFyFl();
		// 管理配置同步 （有问题）
//		sysConfigController.JtSysConfigTOFy();
		// 科室
		hisDepartController.JtDataTOFy();
		// 患者来源
		logInfo.info("患者来源同步" + hisDictController.JtDataTOFy());
		// 充值活动
		hisPrepayactController.JtTOFy();

		hisEmployeeController.JtTOFy();

		logInfo.info("岗位同步" + hisStationController.JtDataTOFy());

		hisOrganizController.JtToFy();

		hisPaymentController.JtToFy();

		hisGroupController.JtToFy();

	}

	/**
	 * 十分钟间隔同步今日register
	 */
	@Scheduled(fixedRate = 1000 * 60 * 10)
	public void registerTable() {
		Map<String, String> sysConfig = configService.getSysConfig();
		String unitCode = sysConfig.get("local_unit");
		List<HisRegister> registers = hesRegisterService.getRegisterByUnitCode(unitCode);
		logInfo.info("registerTable()定时器执行了，集团有：" + registers.size() + "条预约信息");
		int flag = 0;
		if (registers != null) {
			for (int i = 0; i < registers.size(); i++) {
				HisRegister register = registers.get(i);
				HisRegister register2 = hisRegisterMapper.selectByPrimaryKey(register.getId());
				if (register2 == null && register != null) {
					HisPatient hisPatient = hisPatientMapper.selectByPrimaryKey(register.getPatId());
					HisPatient jtPatient = hesPatientService.findHisPatientById(register.getPatId());
					if (hisPatient == null && jtPatient != null) {
						hisPatientMapper.insert(jtPatient);
					}
					hisRegisterMapper.insertSelective(register);
					flag++;
				}
			}
		}
		logInfo.info("registerTable()定时器执行了，共同步下来：" + flag + "条预约信息");
	}

	/**
	 * 同步patient
	 */
	// @Scheduled(cron = "11 11 2 * * ?")
	// public void patientTable() {
	// int flag = 0;
	// List<HisPatient> hisPatientList = hesPatientService.getHisPatientList();
	// for (int i = 0; i < hisPatientList.size(); i++) {
	// HisPatient hisPatient = hisPatientList.get(i);
	// HisPatient fyPatient = hisPatientMapper.selectByPrimaryKey(hisPatient.getId());
	// if (fyPatient == null && hisPatient != null) {
	// hisPatientMapper.insert(hisPatient);
	// flag++;
	// }
	// }
	// logInfo.info("patientTable()定时器执行了，共同步下来：" + flag + "条患者信息");
	// }

	// 每天晚上11点11分11秒同步 患者与电子病历
	@Scheduled(cron = "11 11 23 * * ?")
	public void patientTable() {
		int flag = 0;
		Integer todayCount = hesPatientService.todayCount();
		Integer num = 1;
		if (todayCount % 100 == 0) {
			num = todayCount / 100;
		} else {
			num = todayCount / 100 + 1;
		}
		for (int i = 0; i < num; i++) {
			HisPatient patient = new HisPatient();
			patient.setLimitStart((long) i);
			patient.setLimitCount(100);
			List<HisPatient> hisPatientList = hesPatientService.selectListToday(patient);
			for (int j = 0; j < hisPatientList.size(); j++) {
				HisPatient hisPatient = hisPatientList.get(j);
				HisPatient fyPatient = hisPatientMapper.selectByPrimaryKey(hisPatient.getId());
				if (fyPatient == null) {
					hisPatientMapper.insert(hisPatient);
					flag++;
				} else {
					hisPatientMapper.updateByPrimaryKey(hisPatient);
					flag++;
				}
			}
		}
		logInfo.info("patientTable()定时器执行了，共同步下来：" + flag + "条患者信息");
		flag = 0;
		List<HisPatientCase> hisPatientCaseList = hesPatientCaseService.getHisPatientList();
		for (int i = 0; i < hisPatientCaseList.size(); i++) {
			HisPatientCase hisPatientCase = hisPatientCaseList.get(i);
			HisPatientCase fyPatient = hisPatientCaseMapper.selectByPrimaryKey(hisPatientCase.getId());
			if (fyPatient == null && hisPatientCase != null) {
				hisPatientCaseMapper.insert(hisPatientCase);
				flag++;
			}
		}
		logInfo.info("patientCaseTable()定时器执行了，共同步下来：" + flag + "条电子病历");
	}

	/**
	 * 6小时同步一次今日patient
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60 * 6)
	public void patientTableToday() {
		int flag = 0;
		Integer todayCount = hesPatientService.todayCount();
		Integer num = 1;
		if (todayCount % 100 == 0) {
			num = todayCount / 100;
		} else {
			num = todayCount / 100 + 1;
		}
		for (int i = 0; i < num; i++) {
			HisPatient patient = new HisPatient();
			patient.setLimitStart((long) i);
			patient.setLimitCount(100);
			List<HisPatient> hisPatientList = hesPatientService.selectListToday(patient);
			for (int j = 0; j < hisPatientList.size(); j++) {
				HisPatient hisPatient = hisPatientList.get(j);
				HisPatient fyPatient = hisPatientMapper.selectByPrimaryKey(hisPatient.getId());
				if (fyPatient == null) {
					hisPatientMapper.insert(hisPatient);
					flag++;
				} else {
					hisPatientMapper.updateByPrimaryKey(hisPatient);
					flag++;
				}
			}
		}
		logInfo.info("patientTable()定时器执行了，共同步下来：" + flag + "条患者信息");
	}

	/**
	 * 6小时同步一次patientcase(今日的)
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60 * 6)
	public void patientCaseTable() {
		int flag = 0;
		List<HisPatientCase> hisPatientCaseList = hesPatientCaseService.getHisPatientList();
		for (int i = 0; i < hisPatientCaseList.size(); i++) {
			HisPatientCase hisPatientCase = hisPatientCaseList.get(i);
			HisPatientCase fyPatient = hisPatientCaseMapper.selectByPrimaryKey(hisPatientCase.getId());
			if (fyPatient == null && hisPatientCase != null) {
				hisPatientCaseMapper.insert(hisPatientCase);
				flag++;
			}
		}
		logInfo.info("patientCaseTable()定时器执行了，共同步下来：" + flag + "条电子病历");
	}

	/**
	 * 每天清空叫号表
	 */
	@Scheduled(cron = "59 59 23 * * ?")
	public void emptyCall() {
		int deleteAll = registerCallMapper.deleteAll();
		logInfo.info("emptyCall()定时器执行，清空本地叫号表" + deleteAll + "条数据");
	}

}
