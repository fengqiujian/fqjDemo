package com.enjoyhis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enjoyhis.pojo.Process;

import com.enjoyhis.persistence.his.dao.ProcessDao;

@Service("porcessService")
public class PorcessService {

	@Autowired
	private ProcessDao processDao;
	
	public int update(){
		Process p = processDao.findBiger();
		//没有扎帐记录 不可解锁
		if(p==null){
			return 1;
		}
		p.setDateStatus(0);
		return processDao.updateByPrimaryKeySelective(p);
	}
	
}	
