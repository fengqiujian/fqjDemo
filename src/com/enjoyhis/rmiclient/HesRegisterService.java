package com.enjoyhis.rmiclient;

import java.util.List;
import java.util.Map;

import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.pojo.Register;

public interface HesRegisterService {

	/**
	 * 集团获取就诊列表
	 * 
	 * @param map
	 * @return
	 */
	List<Object> findRegister(Map<String, Object> map);

	/**
	 * 根据ID查询register
	 * @param id
	 * @return
	 */
	Register findById(Long id);

	/**
	 * 查询患者就诊记录
	 * 
	 * @param pid
	 * @return
	 */
	List<Register> registerByPid(Long pid, Long maindocId);

	/**
	 * 将最新信息同步到集团
	 * 
	 * @param pid
	 * @return
	 */
	boolean insertRegister2jt(HisRegister register);

	HisRegister findNextBooking(Long patId);

	List<HisRegister> getRegisterByUnitCode(String unitCode);
}
