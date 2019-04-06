package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.dao.HisEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisPatientCaseDao;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.pojo.PatientCase;
import com.enjoyhis.rmiclient.HesPatientCaseService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.LogUtils;

/**
 * Created by Administrator on 2016/9/30.
 */

@Service("hispatientCaseService")
public class HispatientCaseService {

	@Autowired
	private HisPatientCaseDao hisPatientCaseDao;
	@Autowired
	private HisEmployeeDao hisEmployeeDao;
	@Autowired
	private HisMqService mqService;
	@Autowired
	private SysSeqService sysSeqService;
	@Autowired
	private SysConfigService sysConfigService;// 院区service

	HesPatientCaseService hesPatientCaseService = (HesPatientCaseService) HessianFactoryUtil.getHessianObj(HesPatientCaseService.class);

	public List<PatientCase> patientCaseByPid(Long pid, Long maindocId) {
		List<PatientCase> patientCaseList = new ArrayList<PatientCase>();
		List<HisPatientCase> hisPatientCases = null;
		try {
			hisPatientCases = hesPatientCaseService.getPatientCaseByPid(pid);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hessian接口异常");
		}
		if (hisPatientCases == null) {
			HisPatientCase record = new HisPatientCase();
			record.setPatId(pid);
			hisPatientCases = hisPatientCaseDao.selectList(record);
		}

		if (hisPatientCases != null && hisPatientCases.size() > 0) {
			for (HisPatientCase hisPatientCase : hisPatientCases) {
				patientCaseList.add(getPatientCaseInfo(hisPatientCase));
			}
		}
		return patientCaseList;
	}

	private PatientCase getPatientCaseInfo(HisPatientCase hisPatientCase) {
		PatientCase patientCase = new PatientCase();
		if (null == hisPatientCase) {
			return patientCase;
		}
		try {
			BeanCopyUtil.copyProperties(hisPatientCase, patientCase);
			if (null != hisPatientCase.getDid()) {
				HisEmployee hisEmployee = hisEmployeeDao.selectByPrimaryKey(hisPatientCase.getDid());
				if (hisEmployee != null) {
					patientCase.setMaindocName(hisEmployee.getEmployeeName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientCase;
	}

	public boolean save(Long pid, Long docId, String tooth, String zs, String xbs, String jwbs, String jc, String zd, String cl, String yz, String jzsx, String remark, String nowdate) {
		try {
			Map<String, String> sysConfig = sysConfigService.getSysConfig();
			Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
			Long patientCaseId = sysSeqService.getTableSeq(unitCode, "his_patient_case");

			HisPatientCase record = new HisPatientCase();
			record.setId(patientCaseId);
			record.setPatId(pid);
			record.setDid(docId);
			record.setTooth(tooth);
			record.setZs(zs);
			record.setXbs(xbs);
			record.setJwbs(jwbs);
			record.setJc(jc);
			record.setZd(zd);
			record.setCl(cl);
			record.setYz(yz);
			record.setJzsx(jzsx);
			record.setRemark(remark);
			Date blDate = DateUtils.parseDate(nowdate + ":00", "yyyy-MM-dd HH:mm:ss");
			record.setBlDate(blDate);
			record.setCreateTime(new Date());
			record.setModifyTime(new Date());
			hisPatientCaseDao.insertSelective(record);
			try {
				hesPatientCaseService.insertPatientCase2jt(record);
			} catch (HessianRuntimeException e) {
				mqService.mqMsgToJt(patientCaseId, HisMqEnum.his_patient_case);
				LogUtils.CLIENT_TRACE.error("hessian接口异常");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public HisPatientCase findById(Long caseId) {
		HisPatientCase record = new HisPatientCase();
		record.setId(caseId);
		HisPatientCase hisPatientCase = null;
		try {
			hisPatientCase = hesPatientCaseService.selectOne(record);
		} catch (HessianRuntimeException e) {
		}
		if (hisPatientCase == null) {
			hisPatientCase = hisPatientCaseDao.selectOne(record);
		}
		return hisPatientCase;
	}
}
