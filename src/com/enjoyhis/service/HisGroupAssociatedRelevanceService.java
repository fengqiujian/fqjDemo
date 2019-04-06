package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisGroupEmployeeDao;
import com.enjoyhis.persistence.his.po.HisGroupEmployee;

@Service("hisGroupAssociatedRelevanceService")
public class HisGroupAssociatedRelevanceService {
	@Autowired
	private HisGroupEmployeeDao hisGroupAssociatedRelevanceDao;

	public List<HisGroupEmployee> page4List(HisGroupEmployee hisGroupEmployee, Long gName, Long eId, Integer pageNumber, Integer pageSize) {
		hisGroupEmployee.setSqlSort("id asc");
		// String sql = null;
		if (gName != null) {
			// sql +=" and group_id ='"+gName+"' ";
			hisGroupEmployee.setGroupId(gName);
		}
		if (eId != null) {
			hisGroupEmployee.setEmployeeId(eId);
			// sql +=" and employee_id = '"+eId+"' ";
		}

		// hisGroupAssociatedRelevance.setSqlStr(hisGroupAssociatedRelevance);
		hisGroupEmployee.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisGroupEmployee.setLimitStart(limitStart);
		return hisGroupAssociatedRelevanceDao.selectList(hisGroupEmployee);
	}

	public Integer findCount(HisGroupEmployee hisGroupEmployee) {
		return hisGroupAssociatedRelevanceDao.selectCount(hisGroupEmployee);
	}

	public int deleteGroupInfo(HisGroupEmployee hisGroupEmployee) {
		return hisGroupAssociatedRelevanceDao.deleteSelective(hisGroupEmployee);
	}

	public HisGroupEmployee findById(Long id) {
		// TODO Auto-generated method stub
		return hisGroupAssociatedRelevanceDao.selectByPrimaryKey(id);
	}

	public int updateGroupInfo(HisGroupEmployee entiyjt) {
		// TODO Auto-generated method stub
		return hisGroupAssociatedRelevanceDao.updateByPrimaryKey(entiyjt);
	}

	public int addGroupInfo(HisGroupEmployee entiyjt) {
		// TODO Auto-generated method stub
		return hisGroupAssociatedRelevanceDao.insertSelective(entiyjt);
	}

	public List<HisGroupEmployee> findGroupGroupAssoInfoAll(HisGroupEmployee hisGroupAsso) {
		// hisGroupAsso.setSqlStr(sqlStr);
		return hisGroupAssociatedRelevanceDao.selectList(hisGroupAsso);
	}

}
