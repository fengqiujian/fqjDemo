package com.enjoyhis.persistence.his.dao;

import java.util.List;

import com.enjoyhis.persistence.base.DBRecord;

public interface ReportMapper {
	List<Object> getChargeDetailist(DBRecord db);
	List<Object> getWriteOfflist(DBRecord db);
}