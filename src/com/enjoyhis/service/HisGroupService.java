package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoyhis.persistence.his.dao.HisEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisGroupDao;
import com.enjoyhis.persistence.his.dao.HisGroupEmployeeDao;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisGroup;
import com.enjoyhis.persistence.his.po.HisGroupEmployee;
import com.enjoyhis.pojo.Employee;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.HessianFactoryUtil;

@Service("hisGroupService")
public class HisGroupService {
	@Autowired
	private HisGroupDao hisGroupDao;
	@Autowired
	private HisGroupEmployeeDao hisGroupEmployeeDao;
	@Autowired
	private HisEmployeeDao hisEmployeeDao;
	@Autowired
	private SysSeqService sysSeqService;
	@Autowired
	private SysConfigService sysConfigService;

	public List<HisGroup> page4List(HisGroup hisGroup, String gName, Integer uId, Integer pageNumber, Integer pageSize,Integer unit) {
		hisGroup = new HisGroup();
		hisGroup.setSqlSort("id asc");
		String sql = "and status=1 ";
		if (gName != null && gName != "") {
			sql += " and group_name LIKE '%" + gName + "%'";
		}
		if (uId != null) {
			sql += " and unit_id ='" + uId + "'";
		}else{
			sql += " and unit_id ='" + unit + "'";
		}
		
		hisGroup.setSqlStr(sql);
		hisGroup.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisGroup.setLimitStart(limitStart);
		return hisGroupDao.selectList(hisGroup);
	}
	
	public List<Employee> showGroupPersonnel(Long groupId) {
		HisEmployee he = new HisEmployee();
		he.setIsShow(1);
		he.setStatus(1);
		he.setUserType(1);
		List<HisEmployee> heList = hisEmployeeDao.selectList(he);
		HisGroupEmployee hg = new HisGroupEmployee();
		hg.setGroupId(groupId);
		List<HisGroupEmployee> hgeList = hisGroupEmployeeDao.selectList(hg);
		List<Employee> list = new ArrayList<>();
		for(HisEmployee hes:heList){
			Employee e = new Employee();
			BeanCopyUtil.copyProperties(hes, e);
			e.setIsGroup(0);
			for(HisGroupEmployee hge:hgeList){
				if(e.getId().equals(hge.getEmployeeId())){
					e.setIsGroup(1);
				}
			}
			list.add(e);
		}
		return list;
	}
	
	@Transactional
	public boolean saveGroupPersonnel(Long groupId,String names,Integer isdel){
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		HisGroupEmployee hg = new HisGroupEmployee();
		hg.setGroupId(groupId);
		int i = 0;
		i = hisGroupEmployeeDao.deleteSelective(hg);
		if(isdel>0){
			as.upGroupEmployee(groupId, null, isdel);
			return 1 > 0;
		}
		String[]  strs=names.split(",");
		List<Long> empNameId = new ArrayList<>();
		for(int j=0,len=strs.length;j<len;j++){
		    Long id = Long.parseLong(strs[j].toString());
		    empNameId.add(id);
		}
		List<HisGroupEmployee> list = new ArrayList<>();
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		for(Long ids : empNameId){
			hg = new HisGroupEmployee();
			hg.setEmployeeId(ids);
			hg.setGroupId(groupId);
			hg.setId(sysSeqService.getTableSeq(unit, "his_group_employee"));
			i = hisGroupEmployeeDao.insertSelective(hg);
			i = i/i;
			list.add(hg);
		}
		as.upGroupEmployee(groupId, list, isdel);
		return i > 0;
	}
	
	public Integer findCount(HisGroup hisGroup, String gName, Integer uId, Integer unit) {
		hisGroup = new HisGroup();
		hisGroup.setSqlSort("id asc");
		String sql = "and status=1 ";
		if (gName != null && gName != "") {
			sql += "and group_name LIKE '%" + gName + "%'";
		}
		if (uId != null) {
			sql += " and unit_id ='" + uId + "'";
		}else{
			sql += " and unit_id ='" + unit + "'";
		}
		
		hisGroup.setSqlStr(sql);
		return hisGroupDao.selectCount(hisGroup);
	}

	public HisGroup findById(Long id) {
		return hisGroupDao.selectByPrimaryKey(id);
	}

	public int updateGroupInfo(HisGroup entiyjt) {
		return hisGroupDao.updateByPrimaryKey(entiyjt);
	}

	public int addGroupInfo(HisGroup entiyjt) {
		return hisGroupDao.insert(entiyjt);
	}

	public int deleteGroupInfo(HisGroup hisGroup) {
		return hisGroupDao.deleteSelective(hisGroup);
	}

	public List<HisGroup> findGroupInfoAll(HisGroup hisGroup) {
		return hisGroupDao.selectList(hisGroup);
	}
}
