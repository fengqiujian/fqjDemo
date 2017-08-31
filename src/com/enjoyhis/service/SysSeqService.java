package com.enjoyhis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoyhis.persistence.his.dao.SysSeqDao;
import com.enjoyhis.persistence.his.po.SysSeq;

@Service("sysSeqService")
public class SysSeqService {

	@Autowired
	SysSeqDao sysSeqDao;

	@Transactional
	private Long getSeqNextval(String seqname) {
		SysSeq record = new SysSeq();
		record.setName(seqname);
		SysSeq sysSeq = sysSeqDao.selectOne(record);
		sysSeq.setCurrid(sysSeq.getCurrid() + sysSeq.getIncrement());
		sysSeqDao.updateByPrimaryKeySelective(sysSeq);
		return sysSeq.getCurrid();
	}

	public Long getTableSeq(Integer unitCode, String table_name) {
		Long seq = getSeqNextval(table_name);
		if (unitCode > 1000) {
			return Long.parseLong(seq + "" + unitCode.longValue());
		} else if (unitCode >= 100) // unitCode最大999
			return Long.parseLong(seq + "1" + unitCode);
		else if (unitCode >= 10)
			return Long.parseLong(seq + "10" + unitCode);
		else
			return Long.parseLong(seq + "100" + unitCode);
	}

}
