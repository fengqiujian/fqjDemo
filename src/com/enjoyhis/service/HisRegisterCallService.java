package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoyhis.persistence.his.dao.HisDepartDao;
import com.enjoyhis.persistence.his.dao.HisDocroomDao;
import com.enjoyhis.persistence.his.dao.HisEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisPatientDao;
import com.enjoyhis.persistence.his.dao.HisRegisterCallDao;
import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.po.HisDepart;
import com.enjoyhis.persistence.his.po.HisDocroom;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisRegisterCall;
import com.enjoyhis.pojo.RegisterCall;
import com.enjoyhis.util.DateUtils;

@Service("hisRegisterCallService")
public class HisRegisterCallService {

	@Autowired
	private HisRegisterCallDao hisRegisterCallDao;
	@Autowired
	private HisRegisterDao hisRegisterDao;
	@Autowired
	private HisPatientDao hisPatientDao;
	@Autowired
	private HisEmployeeDao hisEmployeeDao;
	@Autowired
	private HisDocroomDao hisDocroomDao;// 诊室
	@Autowired
	private HisDepartDao hisDepartDao;// 科室

	/**
	 * 0-无，1-排，2-叫，3-过，4-结，5-废
	 * 
	 * @param ghId
	 * @param callDate
	 */
	public void save(Long ghId, Date callDate) {
		HisRegisterCall registerCall = new HisRegisterCall();
		registerCall.setCallDate(callDate);
		Integer callno = hisRegisterCallDao.selectCount(registerCall);
		registerCall.setGhId(ghId);
		registerCall.setCallno(callno + 1);
		registerCall.setCalltype(1);
		hisRegisterCallDao.insertSelective(registerCall);
	}

	/**
	 * 获取叫号信息
	 * 
	 * @param calltype //0-无，1-排，2-叫，3-过，4-结，5-废
	 * @param type
	 * @return
	 */
	@Transactional
	public RegisterCall hisgetCallParams(Integer calltype, Integer type, Long registerId) {
		RegisterCall registerCall = new RegisterCall();
		HisRegisterCall record = new HisRegisterCall();
		record.setCallDate(DateUtils.getDate());
		record.setCalltype(calltype);
		if (null != registerId) {
			record.setGhId(registerId);
		}
		record.setLimitStart(0L);
		record.setLimitCount(1);
		if (type != 0) {
			record.setSqlSort(" callno asc");
		} else {
			record.setSqlSort(" callno desc");
		}
		HisRegisterCall hisRegisterCall = hisRegisterCallDao.selectOne(record);
		if (null != hisRegisterCall && null != hisRegisterCall.getId()) {
			// 根据挂号ID查科室，再根据科室查询今天所有本科室的挂号信息
			HisRegister register = hisRegisterDao.selectByPrimaryKey(hisRegisterCall.getGhId());
			HisRegister reg = new HisRegister();
			reg.setDeptCode(register.getDeptCode());
			reg.setGhTime(DateUtils.getNow());
			List<HisRegister> hisRegisterList = hisRegisterDao.selectList(reg);
			List<Long> ghIds = new ArrayList<>();
			for (int i = 0; i < hisRegisterList.size(); i++) {
				ghIds.add(hisRegisterList.get(i).getId());
			}

			List<HisRegisterCall> registerCallList = hisRegisterCallDao.selectListByGhids(ghIds);

			if (type == 1) {// 说明是叫号操作
				if (registerCallList != null && registerCallList.size() > 0) {
					for (int i = 0; i < registerCallList.size(); i++) {
						HisRegisterCall hisRegisterCall2 = registerCallList.get(i);
						hisRegisterCall2.setCalltype(4);
						hisRegisterCallDao.updateByPrimaryKeySelective(hisRegisterCall2);
					}
				}
				hisRegisterCall.setCalltype(2);
				hisRegisterCallDao.updateByPrimaryKeySelective(hisRegisterCall);
			}
			if (type == 5) {// 说明是退号操作
				hisRegisterCall.setCalltype(5);
				hisRegisterCallDao.updateByPrimaryKeySelective(hisRegisterCall);
			}
			if (type == 3) {// 说明是过号操作
				hisRegisterCall.setCalltype(3);
				hisRegisterCallDao.updateByPrimaryKeySelective(hisRegisterCall);
			}
			registerCall.setUniqid(hisRegisterCall.getGhId().toString());// 唯一标识
			HisRegister hisRegister = register;
			if (null != hisRegister && null != hisRegister.getPatId() && null != hisRegister.getDentistId()) {
				HisPatient hisPatient = hisPatientDao.selectByPrimaryKey(hisRegister.getPatId());
				HisEmployee hisEmployee = hisEmployeeDao.selectByPrimaryKey(hisRegister.getDentistId());
				if (null != hisEmployee && null != hisEmployee.getDocroomId()) {
					HisDocroom hisDocroom = hisDocroomDao.selectByPrimaryKey(hisEmployee.getDocroomId());
					registerCall.setRoom(hisDocroom.getRoomName());// 诊室
				}
				if (null != hisEmployee && null != hisEmployee.getDepartId()) {
					HisDepart hisDepart = hisDepartDao.selectByPrimaryKey(hisEmployee.getDepartId());
					registerCall.setDepartments(hisDepart.getDepName());// 科室
				}
				registerCall.setTitle(hisEmployee.getTitle());// 职称
				registerCall.setDoctor(hisEmployee.getEmployeeName());// 医生姓名
				registerCall.setAvatar("");// 医生头像
				registerCall.setPatient(hisPatient.getPatName());// 患者名称
				registerCall.setPat_age(null == hisPatient.getAge() ? "20" : hisPatient.getAge().toString());// 患者年龄
				registerCall.setPat_sex(hisPatient.getUserSex().equals("男") ? "1" : "2");// 患者性别(0-未知,
																							// 1-男,
																							// 2-女)
				registerCall.setNumber(hisRegisterCall.getCallno().toString());// 就诊编号
			}
			return registerCall;
		} else {
			return null;
		}

	}
}
