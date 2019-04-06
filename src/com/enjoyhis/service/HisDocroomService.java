package com.enjoyhis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisDocroomDao;
import com.enjoyhis.persistence.his.po.HisDocroom;

@Service("hisDocroomService")
public class HisDocroomService {

	@Autowired
	private HisDocroomDao hisDocroomDao;

	public List<HisDocroom> selectList(HisDocroom hisDocroom) {
		List<HisDocroom> list = hisDocroomDao.selectList(hisDocroom);
		return list;
	}

	public int addRoom(HisDocroom hisDocroom) {
		return hisDocroomDao.insert(hisDocroom);
	}

	public int deleteSelective(Integer id) {
		return hisDocroomDao.deleteByPrimaryKey(id);
	}

	public HisDocroom findById(Integer id) {
		return hisDocroomDao.selectByPrimaryKey(id);
	}

	public int modifyRoomInformation(HisDocroom hisDocroom) {
		return hisDocroomDao.updateByPrimaryKey(hisDocroom);
	}

	public List<HisDocroom> page4List(HisDocroom hisDocroom, String rName, String rIp, Integer pageNumber, Integer pageSize) {
		hisDocroom.setSqlSort("id desc");
		String sql = "";
		if (rName != "" && rName != null) {
			sql += " and room_name LIKE '%" + rName + "%' ";
			// hisDocroom.setSqlStr("and room_name LIKE '%"+rName+"%'");
		}
		if (rIp != null & rIp != "") {
			sql += " and room_ip LIKE '%" + rIp + "%' ";
			// hisDocroom.setSqlStr("and room_ip LIKE '%"+rIp+"%'" );
		}
		hisDocroom.setSqlStr(sql);
		hisDocroom.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisDocroom.setLimitStart(limitStart);
		return hisDocroomDao.selectList(hisDocroom);
	}

	public Integer findCount(HisDocroom record) {
		return hisDocroomDao.selectCount(record);
	}

	public void updateDocRoom(HisDocroom entiyjt) {
		hisDocroomDao.updateByPrimaryKey(entiyjt);
	}

	public HisDocroom findIpByInfo(HisDocroom hisDocroom) {
		return hisDocroomDao.selectOne(hisDocroom);
	}

}
