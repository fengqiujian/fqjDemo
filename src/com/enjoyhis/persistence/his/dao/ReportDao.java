package com.enjoyhis.persistence.his.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.base.DBRecord;

@Service("reportDao")
public class ReportDao {

	@Autowired
	ReportMapper reportMapper;

	public List<Object> getChargeDetailist(DBRecord record) {
		return reportMapper.getChargeDetailist(record);
	}

	public List<Object> getWriteOfflist(DBRecord record) {
		return reportMapper.getWriteOfflist(record);
	}
}
