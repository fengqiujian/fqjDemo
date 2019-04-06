package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoyhis.persistence.his.dao.HisDepartDao;
import com.enjoyhis.persistence.his.dao.HisDocroomDao;
import com.enjoyhis.persistence.his.dao.HisEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisGroupDao;
import com.enjoyhis.persistence.his.dao.HisGroupEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisOrganizDao;
import com.enjoyhis.persistence.his.dao.SysConfigDao;
import com.enjoyhis.persistence.his.po.HisDocroom;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisGroup;
import com.enjoyhis.persistence.his.po.HisGroupEmployee;
import com.enjoyhis.persistence.his.po.HisOrganiz;
import com.enjoyhis.persistence.his.po.SysConfig;
import com.enjoyhis.pojo.Employee;
import com.enjoyhis.pojo.OrganizPojo;
import com.enjoyhis.pojo.SearchItems;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.rmiclient.IHisEmployee;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.MyStringUtil;

@Service("hisEmployeeService")
public class HisEmployeeService {

	public static Map<String, String> cm = new HashMap<String, String>();
	@Autowired
	SysConfigDao sysConfigDao;
	@Autowired
	SysConfigService sysConfigService;
	@Autowired
	HisEmployeeDao hisEmployeeDao;
	@Autowired
	HisOrganizDao hisOrganizDao;
	@Autowired
	HisDocroomDao hisDocroomDao;
	@Autowired
	HisDepartDao hisDepartDao;
	@Autowired
	HisGroupDao hisGroupDao;
	@Autowired
	HisGroupEmployeeDao hisGroupEmployeeDao;
	
	private IHisEmployee heHs = (IHisEmployee) HessianFactoryUtil.getHessianObj(IHisEmployee.class);
	
	/**
	 * 获取医生集合列表
	 * 
	 * @param record
	 * @return
	 */
	public List<Employee> getList(HisEmployee record) {
		List<HisEmployee> selectList = hisEmployeeDao.selectList(record);
		List<Employee> list = new ArrayList<Employee>();
		if (null != selectList && selectList.size() > 0) {
			for (HisEmployee one : selectList) {
				Employee employee = getEmployeeInfo(one);
				list.add(employee);
			}
		}
		return list;
	}

	public HisEmployee getHisEmployeeOne(HisEmployee he) {
		return hisEmployeeDao.selectOne(he);
	}

	public int updateHisEmployeeOne(HisEmployee he) {
		return hisEmployeeDao.updateByPrimaryKeySelective(he);
	}
	@Transactional
	public int updateHisEmployeeOne2(HisEmployee he) {
		int i = heHs.updateEmployee(he);
		i = i/i;
		i = hisEmployeeDao.updateByPrimaryKeySelective(he);
		return i/i;
	}

	public HisEmployee getHisEmployeeOne(Long id) {
		return hisEmployeeDao.selectByPrimaryKey(id);
	}

	public HisEmployee login(HisEmployee he, String roomIp) {
		he = hisEmployeeDao.selectOne(he);
		if (he != null) {
			he.setLastvistTime(new Date());
			he.setCookie(MyStringUtil.getUUID());
			HisDocroom hs = null;
			if (roomIp != null) {
				hs = new HisDocroom();
				hs.setRoomIp(roomIp);
				hs = hisDocroomDao.selectOne(hs);
			}
			if (hs != null) {
				he.setDocroomId(hs.getId());
			}
			hisEmployeeDao.updateByPrimaryKeySelective(he);
		}
		return he;
	}

	public boolean loginOut(Long id) {
		HisEmployee he = hisEmployeeDao.selectByPrimaryKey(id);
		he.setDocroomId(0);
		return hisEmployeeDao.updateByPrimaryKeySelective(he) > 0;
	}

	public HisEmployee login(HisEmployee he) {
		he = hisEmployeeDao.selectOne(he);
		if (he != null) {
			he.setLastvistTime(new Date());
			he.setCookie(MyStringUtil.getUUID());
			hisEmployeeDao.updateByPrimaryKeySelective(he);
		}
		return he;
	}

	public OrganizPojo getOrganizPojo() {
		Map<String, String> cm = getSysConfig();
		OrganizPojo op = new OrganizPojo();
		HisOrganiz ho = hisOrganizDao.selectByPrimaryKey(Integer.parseInt((cm.get("local_unit"))));
		BeanCopyUtil.copyProperties(ho, op);
		op.setXgId(ho.getxId());
		// op.setXgId(ho.getxId());
		return op;
	}

	public Map<String, String> getSysConfig() {
		if (cm != null && cm.size() > 0) {
			return cm;
		}

		SysConfig record = new SysConfig();
		List<SysConfig> list = sysConfigDao.selectList(record);
		for (SysConfig sysConfig : list) {
			cm.put(sysConfig.getKeystr(), sysConfig.getValuestr());
		}
		return cm;

	}

	private Employee getEmployeeInfo(HisEmployee one) {
		Employee employee = new Employee();
		try {
			BeanCopyUtil.copyProperties(one, employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	// -----------------------------------------------------------
	// 根据用户，密码查询

	public HisEmployee loginUser(HisEmployee hisEmployee, String mobile) {
		hisEmployee.setSqlStr(" mobile ='" + mobile + "' ");
		try{
			return hisEmployeeDao.selectOne(hisEmployee);
		}catch(Exception e){
			return null;
		}
		
	}

	public List<HisEmployee> loginPass(HisEmployee hisEmployee, String password) {
		hisEmployee.setSqlStr(" password ='" + password + "' ");
		return hisEmployeeDao.selectList(hisEmployee);
	}

	public List<HisEmployee> page4List(Integer uCode, Integer serchshow, String mob, String empName, HisEmployee record, Integer pageNumber, Integer pageSize) {
		String sql = " and status=1 ";
		// record.setSqlSort("id asc");
		if (mob != null && mob != "") {
			sql += " and mobile LIKE '%" + mob + "%' ";
		}
		if (empName != null && empName != "") {
			sql += " and employee_name LIKE '%" + empName + "%' ";
		}
		if (uCode != null) {
			sql += " and unit_code ='" + uCode + "'";
		}
		if (serchshow == 1) {
			sql += " and is_show='" + serchshow + "'";
		}
		record.setSqlStr(sql);
		record.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		record.setLimitStart(limitStart);
		return hisEmployeeDao.selectList(record);
	}
	public List<HisEmployee> page4List(Integer uCode,  Integer serchshow, String mob, String empName, Integer pageNumber, Integer pageSize) {
		HisEmployee record = new HisEmployee();
		record.setStatus(1);
		String sql = " ";
		// record.setSqlSort("id asc");
		if (mob != null && mob != "") {
			sql += " and mobile LIKE '%" + mob + "%' ";
		}
		if (empName != null && empName != "") {
			sql += " and employee_name LIKE '%" + empName + "%' ";
		}
		if (uCode != null) {
			sql += " and unit_code ='" + uCode + "'";
		}
		if (serchshow == 1) {
			sql += " and is_show='" + serchshow + "'";
		}
		record.setSqlStr(sql);
		record.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		record.setLimitStart(limitStart);
		return hisEmployeeDao.selectList(record);
	}

	public Integer findCountId(HisEmployee record) {
		return hisEmployeeDao.selectCount(record);
	}
	public Integer findCountId(Integer uCode, Integer serchshow, String mob, String empName) {
		HisEmployee record = new HisEmployee();
		record.setStatus(1);
		String sql = " ";
		// record.setSqlSort("id asc");
		if (mob != null && mob != "") {
			sql += " and mobile LIKE '%" + mob + "%' ";
		}
		if (empName != null && empName != "") {
			sql += " and employee_name LIKE '%" + empName + "%' ";
		}
		if (uCode != null) {
			sql += " and unit_code ='" + uCode + "'";
		}
		if (serchshow == 1) {
			sql += " and is_show='" + serchshow + "'";
		}
		record.setSqlStr(sql);
		return hisEmployeeDao.selectCount(record);
	}

	public void addEmployeeInfo(HisEmployee hisEmployee) {
		hisEmployeeDao.insert(hisEmployee);
	}

	public void deleteEmployeeInfo(Long id) {
		hisEmployeeDao.deleteByPrimaryKey(id);
	}

	public List<HisEmployee> selectEmployeeList(HisEmployee hisEmployee) {
		return hisEmployeeDao.selectList(hisEmployee);
	}

	public int UpdateEmployee(HisEmployee hisEmployee) {
		return hisEmployeeDao.updateByPrimaryKeySelective(hisEmployee);
	}
	@Transactional
	public int UpdateEmployee(HisEmployee hisEmployee,Integer unitCode) {
		if(hisEmployee.getIsShow()==0){
//			hisGroupDao hisGroupEmployeeDao sysConfigDao
			unitCode = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
			HisGroupEmployee hge = new HisGroupEmployee();
			hge.setEmployeeId(hisEmployee.getId());
			hge.setSqlStr(" and group_id in (SELECT id FROM his_group WHERE unit_id = "+unitCode+")");
			List<HisGroupEmployee> hges = hisGroupEmployeeDao.selectList(hge);
			List<Long> hgeId = new ArrayList<>();
			for(HisGroupEmployee hgel:hges){
				hgeId.add(hgel.getId());
			}
			for(Long id:hgeId){
				hisGroupEmployeeDao.deleteByPrimaryKey(id);
			}
			AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
			try{
				as.upGroupEmployee2(hgeId);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return hisEmployeeDao.updateByPrimaryKeySelective(hisEmployee);
	}

	public int addEmployeeFy(HisEmployee hisEmployee) {
		return hisEmployeeDao.insertSelective(hisEmployee);
	}

	public HisEmployee findById(Long idjt) {
		return hisEmployeeDao.selectByPrimaryKey(idjt);
	}

	public List<SearchItems> getSearchItemList(HisEmployee hisEmployee) {
		List<SearchItems> list = new ArrayList<SearchItems>();
		List<HisEmployee> lists = hisEmployeeDao.selectList(hisEmployee);
		SearchItems si = null;
		for (HisEmployee hsix : lists) {
			si = new SearchItems();
			si.setCode(hsix.getId().toString());
			si.setName(hsix.getEmployeeName());
			list.add(si);
		}
		return list;
	}

	public int deleteEmployeeAll(HisEmployee hisEmployee) {
		// TODO Auto-generated method stub
		return hisEmployeeDao.deleteSelective(hisEmployee);
	}
}
