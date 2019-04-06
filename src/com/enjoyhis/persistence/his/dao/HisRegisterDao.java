package com.enjoyhis.persistence.his.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.po.HisRegister;

@Service("hisRegisterDao")
public class HisRegisterDao {

	@Autowired
	HisRegisterMapper hisRegisterMapper;

	public int deleteByPrimaryKey(Long id) {
		return hisRegisterMapper.deleteByPrimaryKey(id);
	}

	public int insert(HisRegister record) {
		return hisRegisterMapper.insert(record);
	}

	public int insertSelective(HisRegister record) {
		return hisRegisterMapper.insertSelective(record);
	}

	public HisRegister selectByPrimaryKey(Long id) {
		return hisRegisterMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(HisRegister record) {
		return hisRegisterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HisRegister record) {
		return hisRegisterMapper.updateByPrimaryKey(record);
	}

	public HisRegister selectOne(HisRegister record) {
		return hisRegisterMapper.selectOne(record);
	}

	public List<HisRegister> selectList(HisRegister record) {
		return hisRegisterMapper.selectList(record);
	}

	public int selectCount(HisRegister record) {
		return hisRegisterMapper.selectCount(record);
	}

	public int deleteSelective(HisRegister record) {
		return hisRegisterMapper.deleteSelective(record);
	}

	/**
	 * 查询今日就诊列表
	 * @author tianfei
	 * @param map
	 * @return
	 */
	public List<Object> findRegister(Map<String, Object> map) {
		return hisRegisterMapper.findRegister(map);
	}

	public int selectRegisterCount(Map<String, Object> map) {
		return hisRegisterMapper.selectRegisterCount(map);
	}
	
	public List<Object> patientReportData(Map<String, Object> map){
		return hisRegisterMapper.patientReportData(map);
	}
	public List<Object> nonarrivalReportData(Map<String, Object> map){
		return hisRegisterMapper.nonarrivalReportData(map);
	}
	public List<Object> selectDateList(HisRegister hr){
		return hisRegisterMapper.selectDateList(hr);
	}

	public Long[] findBookingDocsByDate(Date bookingDate){
		return hisRegisterMapper.findBookingDocsByDate(bookingDate);
	}

	public List<HisRegister> selectRegisterList(HisRegister hisRegister) {
		return hisRegisterMapper.selectRegisterList(hisRegister);
	}

	public List<HisRegister> selectRegisterList(Map<String, Object> map) {
		return hisRegisterMapper.selectRegisterListByMap(map);
	}

}
