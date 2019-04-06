package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.enjoyhis.persistence.his.po.HisEmployeeBookingitem;

public interface HisEmployeeBookingitemMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(HisEmployeeBookingitem record);

	int insertSelective(HisEmployeeBookingitem record);

	HisEmployeeBookingitem selectByPrimaryKey(Integer id);

	HisEmployeeBookingitem selectByBean(HisEmployeeBookingitem items);
	
	int updateByPrimaryKeySelective(HisEmployeeBookingitem record);

	int updateByPrimaryKey(HisEmployeeBookingitem record);

	int deleteByDoctorId(@Param("doctorId")Long doctorId, @Param("id")Integer id);

	List<HisEmployeeBookingitem> findList(Long doctorId);

}