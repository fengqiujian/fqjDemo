package com.enjoyhis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisPosDetailsDao;
import com.enjoyhis.persistence.his.po.HisPosDetails;

@Service("posDetailsService")
public class PosDetailsService {

	@Autowired
	private HisPosDetailsDao hisPosDetailsDao;
	@Autowired
	private SysConfigService sysConfigService;// 院区service
	@Autowired
	private SysSeqService sysSeqService;
	
	public List<HisPosDetails> getList(String posName ,String bankName, Integer isLater,Integer pageSize, Integer pageNum){
		HisPosDetails hpd = new HisPosDetails();
		String str = " 1 = 1 ";
		if(posName!=null){
			str += " AND pos_name like '%" + posName + "%'";
		}
		if(bankName!=null){
			str += " AND bank_name like '%" + bankName + "%'";
		}
		if(isLater==1){
			str += " AND status = 1";
		}
		hpd.setSqlStr(str);
		hpd.setLimitCount(pageSize);
		hpd.setLimitStart((pageNum - 1L) * pageSize);
		
		return hisPosDetailsDao.selectList(hpd);
	}
	
	public int getListCount(String posName ,String bankName, Integer isLater) {
		HisPosDetails hpd = new HisPosDetails();
		String str = " 1 = 1 ";
		if(posName!=null){
			str += " AND pos_name like '%" + posName + "%'";
		}
		if(bankName!=null){
			str += " AND bank_name like '%" + bankName + "%'";
		}
		if(isLater==1){
			str += " AND status = 1";
		}
		hpd.setSqlStr(str);
		return hisPosDetailsDao.selectCount(hpd);
	}
	
	public boolean savePos(HisPosDetails hpd){
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
		Long seqNextval = sysSeqService.getTableSeq(unitCode, "his_pos_details");
		hpd.setId(seqNextval);
		hpd.setUnitCode(unitCode);
		hpd.setStatus(1);
		return hisPosDetailsDao.insertSelective(hpd)>0;
	}
	
	public boolean updatePos(Long id,Integer status){
		HisPosDetails hpd = new HisPosDetails();
		hpd.setId(id);
		hpd.setStatus(status);
		return hisPosDetailsDao.updateByPrimaryKeySelective(hpd)>0;
	}
	
}	
