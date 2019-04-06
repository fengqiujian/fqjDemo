package com.enjoyhis.rmiclient;

import java.util.List;
import java.util.Map;

import com.enjoyhis.persistence.his.po.HisPatient;

public interface HesPatientService {

	/**
	 * 将本地患者同步到集团
	 * 
	 * @author tianfei
	 * @param hisPatient
	 * @return
	 */
	boolean insertPatient2jt(HisPatient hisPatient);

	/**
	 * 查询集团患者（分页查询）
	 * 
	 * @author tianfei
	 * @param map
	 * @return
	 */
	List<Object> page4List(Map<String, Object> map);

	Map<String, Object> page4Data(Map<String, Object> map);

	Integer query4Count(Map<String, Object> map);

	/**
	 * 通过患者id查询集团患者
	 * 
	 * @param patId
	 * @return
	 */
	HisPatient findById(Long patId);

	List<Object> findRegister(Map<String, Object> map);

	/**
	 * 通过患者id查询集团患者
	 * 
	 * @param patId
	 * @return
	 */
	HisPatient findHisPatientById(Long patId);

	List<HisPatient> getHisPatientList();

	List<HisPatient> selectListToday(HisPatient patient);

	Integer todayCount();

	List<Object> patientPage4List(Map<String, Object> map);

	Integer patientQuery4Count(Map<String, Object> map);

}
