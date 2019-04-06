package com.enjoyhis.persistence.his.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.enjoyhis.persistence.his.po.HisRegister;

public interface HisRegisterMapper {

	int deleteByPrimaryKey(Long id);

	int insert(HisRegister record);

	int insertSelective(HisRegister record);

	HisRegister selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(HisRegister record);

	int updateByPrimaryKey(HisRegister record);

	HisRegister selectOne(HisRegister record);

	List<HisRegister> selectList(HisRegister record);

	int selectCount(HisRegister record);
	
	int selectRegisterCount(Map<String, Object> map);

	int deleteSelective(HisRegister record);

	List<Object> findRegister(Map<String, Object> map);

	List<Object> patientReportData(Map<String, Object> map);

	List<Object> nonarrivalReportData(Map<String, Object> map);

	List<Object> selectDateList(HisRegister hr);

	Long[] findBookingDocsByDate(Date bookingDate);

	List<HisRegister> selectRegisterList(HisRegister hisRegister);

	List<HisRegister> selectRegisterListByMap(Map<String, Object> map);
}