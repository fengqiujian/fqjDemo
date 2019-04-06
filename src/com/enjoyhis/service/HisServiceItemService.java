package com.enjoyhis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemFlDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemXmDao;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.dao.HisStatementDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDetailDao;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisServiceItem;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.persistence.his.po.HisStatementItem;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.pojo.SearchItems;
import com.enjoyhis.pojo.ServiceItemXm;
import com.enjoyhis.pojo.StatementItemDetail;
import com.enjoyhis.util.BeanCopyUtil;

@Service("hisServiceItemService")
public class HisServiceItemService {
	@Autowired
	HisServiceItemDao hisServiceItemDao;
	@Autowired
	HisServiceItemFlDao hisServiceItemFlDao;
	@Autowired
	HisServiceItemXmDao hisServiceItemXmDao;
	@Autowired
	HisStatementDao hisStatementDao;
	@Autowired
	HisStatementItemDao hisStatementItemDao;
	@Autowired
	HisStatementItemDetailDao hisStatementItemDetailDao;
	@Autowired
	HisStatementChargeDao hisStatementChargeDao;
	@Autowired
	HisRegisterDao hisRegisterDao;
	@Autowired
	SysSeqService sysSeqService;

	/**
	 * 获取一级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItem> getAllHisServiceItemList() {
		return hisServiceItemDao.selectList(new HisServiceItem());
	}

	/**
	 * 获取一级处置项名称
	 * 
	 * @return
	 */
	public Map<String, String> getAllHisServiceItemMap() {
		List<HisServiceItem> list = hisServiceItemDao.selectList(new HisServiceItem());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItem hsi : list) {
			map.put(hsi.getItemCode(), hsi.getItemName());
		}
		return map;
	}

	/**
	 * 获取二级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItemFl> getHisServiceItemFlList(HisServiceItemFl hsif) {
		return hisServiceItemFlDao.selectList(hsif);
	}

	/**
	 * 获取二级处置项名称
	 * 
	 * @return
	 */
	public Map<String, String> getAllHisServiceItemFlMap() {
		List<HisServiceItemFl> list = hisServiceItemFlDao.selectList(new HisServiceItemFl());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItemFl hsi : list) {
			map.put(hsi.getItemCode(), hsi.getItemName());
		}
		return map;
	}

	/**
	 * 获取二级处置项ID
	 * 
	 * @return
	 */
	public Map<String, String> getIdHisServiceItemFlMap() {
		List<HisServiceItemXm> list = hisServiceItemXmDao.selectList(new HisServiceItemXm());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItemXm hsi : list) {
			map.put(hsi.getItemCode(), hsi.getParentId());
		}
		return map;
	}

	/**
	 * 获取三级处置项
	 * 
	 * @return
	 */
	public List<HisServiceItemXm> getHisServiceItemXmList(HisServiceItemXm hsif, int unit) {
		List<HisServiceItemXm> list = hisServiceItemXmDao.selectList(hsif);
		for (int i = 0; i < list.size(); i++) {
			HisServiceItemXm hsix = list.get(i);
			if (isNotHave(hsix.getHospCode(), unit)) {
				list.remove(i);
				i--;
			}
		}
		return list;
	}

	private boolean isNotHave(String list, int unit) {
		String[] unitCodes = list.split(",");
		boolean flag = true;
		for (int j = 0; j < unitCodes.length; j++) {
			int units = Integer.parseInt(unitCodes[j]);
			if (unit == units) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 获取三级处置项名称
	 * 
	 * @return
	 */
	public Map<String, String> getAllHisServiceItemXmMap() {
		List<HisServiceItemXm> list = hisServiceItemXmDao.selectList(new HisServiceItemXm());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItemXm hsi : list) {
			map.put(hsi.getItemCode(), hsi.getItemName());
		}
		return map;
	}

	/**
	 * 保存处置项主表
	 * 
	 * @param hs
	 */
	public Long saveHisStatementItem(HisStatementItem hs) {
		hisStatementItemDao.insertSelective(hs);
		return hs.getId();
	}

	/**
	 * 保存结算单主表
	 * 
	 * @param hs
	 */
	public Long saveHisStatement(HisStatement hs) {
		hisStatementDao.insertSelective(hs);
		;
		return hs.getId();
	}

	/**
	 * 查找结算单主表
	 * 
	 * @param hs
	 * @return
	 */
	public HisStatement getHisStatement(HisStatement hs) {
		return hisStatementDao.selectOne(hs);
	}

	public HisStatement getHisStatement(Long hs) {
		return hisStatementDao.selectByPrimaryKey(hs);
	}

	/**
	 * 查找处置单主表
	 * 
	 * @param hs
	 * @return
	 */
	public HisStatementItem getHisStatementItem(HisStatementItem hs) {
		return hisStatementItemDao.selectOne(hs);
	}

	public HisStatementItem getHisStatementItem(Long hs) {
		return hisStatementItemDao.selectByPrimaryKey(hs);
	}

	/**
	 * 保存处置项明细
	 * 
	 * @param hsi
	 */
	public int saveHisStatementItemDetail(HisStatementItemDetail hsid) {
		return hisStatementItemDetailDao.insertSelective(hsid);
	}

	/**
	 * 保存处置项消费明细
	 * 
	 * @param hsc
	 */
	public int saveHisStatementCharge(HisStatementCharge hsc) {
		return hisStatementChargeDao.insertSelective(hsc);
	}

	/**
	 * 保存处置项
	 * 
	 * @param regId
	 * @param patientId
	 * @param sh
	 * @param dz
	 * @param ze
	 * @param list
	 * @param docId
	 * @return
	 */
	@Transactional
	public boolean saveTable(Long regId, Long patientId, BigDecimal dz, BigDecimal ze, List<HisStatementItemDetail> list, Long docId, Map<String, String> map) throws Exception {
		Date date = new Date();
		HisRegister hr = hisRegisterDao.selectByPrimaryKey(regId);
		if (hr.getStatus() != 2) {
			return false;
		}
		HisStatementItem hsi = new HisStatementItem();
		hsi.setRegId(regId);
		hsi.setPatId(patientId);
		hsi.setAccountType(2);
		hsi.setCreateTime(date);
		hsi.setModifyTime(date);
		hsi.setUnitCode(Integer.parseInt(map.get("local_unit")));
		hsi.setTotalAmount(ze);
		hsi.setItemAmount(ze);
		hsi.setStatus(0);
		hsi.setFlag(0);
		hsi.setDocId(docId);
		hsi.setId(sysSeqService.getTableSeq(Integer.parseInt(map.get("local_unit")), "his_statement_item"));
		Long itemId = saveHisStatementItem(hsi);
		int i = 0;
		i = updateReg(regId, 3, date, itemId);
		i = i / i;
		HisStatementItemDetail hsidDelete = new HisStatementItemDetail();
		hsidDelete.setStatementItemid(regId);
		hsidDelete.setIfFormal(0);
		list = hisStatementItemDetailDao.selectList(hsidDelete);
		for (HisStatementItemDetail hsid : list) {
			hsid.setStatementItemid(itemId);
			hsid.setUnitCode(Integer.parseInt(map.get("local_unit")));
			hsid.setDocId(docId);
			hsid.setPatientId(patientId);
			hsid.setAccountType(2);
			hsid.setCreateTime(date);
			hsid.setModifyTime(date);
			hsid.setIsnew(hr.getIsnew());
			hsid.setIfFormal(1);
			i = hisStatementItemDetailDao.updateByPrimaryKeySelective(hsid);
			i = i / i;
		}

		HisStatement hs = new HisStatement();
		hs.setPatId(patientId);
		hs.setAccountType(2);
		hs.setRegId(regId);
		hs.setStatementItemid(itemId);
		hs.setCreateTime(date);
		hs.setModifyTime(date);
		hs.setUnitCode(Integer.parseInt(map.get("local_unit")));
		hs.setTotalAmount(ze);
		hs.setDocId(docId);
		hs.setOperator(docId);
		hs.setStatus(0);
		hs.setFlag(0);
		hs.setId(sysSeqService.getTableSeq(Integer.parseInt(map.get("local_unit")), "his_statement"));
		Long statementId = saveHisStatement(hs);
		i = updateItem(itemId, statementId);
		if (dz.compareTo(BigDecimal.ZERO) == 1) {
			HisStatementCharge hsc = new HisStatementCharge();
			hsc.setStatementId(statementId);
			hsc.setUnitCode(Integer.parseInt(map.get("local_unit")));
			hsc.setPaymentType("YSDZ");
			hsc.setRealAmount(dz);
			hsc.setCreateTime(date);
			hsc.setModifyTime(date);
			hsc.setId(sysSeqService.getTableSeq(Integer.parseInt(map.get("local_unit")), "his_statement_charge"));
			i = saveHisStatementCharge(hsc);
		}

		return i > 0;
	}

	/**
	 * 保存处置项
	 * 
	 * @param regId
	 * @param patientId
	 * @param sh
	 * @param dz
	 * @param ze
	 * @param list
	 * @param docId
	 * @return
	 */
	@Transactional
	public boolean saveTable2(Long regId, Long patientId, List<HisStatementItemDetail> list, Long docId, Map<String, String> map) {
		int i = 0;
		int j = 0;
		HisRegister hr = hisRegisterDao.selectByPrimaryKey(regId);
		if (hr.getStatus() != 2) {
			return false;
		}

		for (HisStatementItemDetail hsid : list) {
			if (j == 0) {
				HisStatementItemDetail hsids = new HisStatementItemDetail();
				hsids.setStatementItemid(regId);
				hsids.setIfFormal(0);
				hsids.setTooth(hsid.getTooth());
				List<HisStatementItemDetail> hsidss = hisStatementItemDetailDao.selectList(hsids);
				if (hsidss.size() > 0) {
					i = hisStatementItemDetailDao.deleteSelective(hsids);
					i = i / i;
				}
				j++;
			}
			hsid.setStatementItemid(regId);
			hsid.setUnitCode(Integer.parseInt(map.get("local_unit")));
			hsid.setDocId(docId);
			hsid.setAccountType(2);
			hsid.setIfFormal(0);
			hsid.setQty(getQty(hsid.getTooth()));
			hsid.setItemAmount(getItemAmount(hsid.getPrice(), hsid.getQty()));
			hsid.setId(sysSeqService.getTableSeq(Integer.parseInt(map.get("local_unit")), "his_statement_item_detail"));
			i = saveHisStatementItemDetail(hsid);
			i = i / i;
		}
		return i > 0;
	}

	private int getQty(String msg) {
		String[] strs = msg.split(",");
		return (strs.length);
	}

	private BigDecimal getItemAmount(BigDecimal p, int i) {
		return p.multiply(new BigDecimal(i));
	}

	@Transactional
	public boolean changeTooth(Long regId, String tooth, String newtooth) {
		// hisStatementItemDetailDao
		HisStatementItemDetail hsid = new HisStatementItemDetail();
		hsid.setTooth(tooth);
		hsid.setStatementItemid(regId);
		hsid.setIfFormal(0);
		List<HisStatementItemDetail> list = hisStatementItemDetailDao.selectList(hsid);
		int i = 0;
		for (HisStatementItemDetail hsids : list) {
			hsids.setTooth(newtooth);
			i = hisStatementItemDetailDao.updateByPrimaryKey(hsids);
			i = i / i;
		}
		return i > 0;
	}

	@Transactional
	public int updateReg(Long regId, int status, Date date, Long itemId) {
		HisRegister hisRegister = hisRegisterDao.selectByPrimaryKey(regId);
		hisRegister.setStatus(status);
		hisRegister.setModifyTime(date);
		hisRegister.setStatementItemid(itemId);
		int i = hisRegisterDao.updateByPrimaryKey(hisRegister);
		return i / i;
	}

	public int updateItem(Long itemId, Long statementId) {
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(itemId);
		hsi.setStatementId(statementId);
		return hisStatementItemDao.updateByPrimaryKey(hsi);
	}

	public ServiceItemXm getItemForTable(String code) {
		ServiceItemXm six = new ServiceItemXm();
		HisServiceItemXm hsix = hisServiceItemXmDao.selectByPrimaryKey(code);
		BeanCopyUtil.copyProperties(hsix, six);
		Map<String, String> fMap = getAllHisServiceItemMap();
		Map<String, String> sMap = getAllHisServiceItemFlMap();
		six.setItemParentName(sMap.get(hsix.getParentId()));
		HisServiceItemFl hsif = hisServiceItemFlDao.selectByPrimaryKey(hsix.getParentId());
		six.setFirCode(hsif.getParentId());
		six.setFirName(fMap.get(hsif.getParentId()));
		return six;
	}

	public List<SearchItems> getSearchItemList(HisServiceItemXm hisx, int unit) {
		List<SearchItems> list = new ArrayList<SearchItems>();
		List<HisServiceItemXm> lists = hisServiceItemXmDao.selectList(hisx);
		for (int i = 0; i < lists.size(); i++) {
			HisServiceItemXm hsix = lists.get(i);
			if (isNotHave(hsix.getHospCode(), unit)) {
				lists.remove(i);
				i--;
			}
		}
		Map<String, String> fMap = getAllHisServiceItemMap();
		Map<String, String> sMap = getAllHisServiceItemFlMap();
		SearchItems si = null;
		for (HisServiceItemXm hsix : lists) {
			si = new SearchItems();
			si.setCode(hsix.getItemCode());
			HisServiceItemFl hsif = hisServiceItemFlDao.selectByPrimaryKey(hsix.getParentId());
			si.setName(hsix.getItemName() + "/" + hsix.getPrice());
			si.setName2("/" + fMap.get(hsif.getParentId()) + "(1级)/" + sMap.get(hsix.getParentId()) + "(2级)");
			list.add(si);
		}
		return list;
	}

	/**
	 * 根据挂号单ID查询处置项
	 * 
	 * @param regId
	 * @return
	 */
	public List<StatementItemDetail> getHisStatementItemDetailList(Long regId) {
		HisRegister hr = hisRegisterDao.selectByPrimaryKey(regId);
		List<StatementItemDetail> list = new ArrayList<StatementItemDetail>();
		List<HisStatementItemDetail> lists = new ArrayList<HisStatementItemDetail>();
		if (hr.getStatus() != 2) {
			HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(hr.getStatementItemid());
			HisStatementItemDetail hsid = new HisStatementItemDetail();
			hsid.setStatementItemid(hsi.getId());
			lists = hisStatementItemDetailDao.selectList(hsid);
		} else {
			HisStatementItemDetail hsid = new HisStatementItemDetail();
			hsid.setStatementItemid(regId);
			hsid.setIfFormal(0);
			hsid.setSqlSort(" tooth asc");
			lists = hisStatementItemDetailDao.selectList(hsid);
		}
		Map<String, String> firName = getAllHisServiceItemMap();
		Map<String, String> secName = getAllHisServiceItemFlMap();
		Map<String, String> secCode = getIdHisServiceItemFlMap();
		Map<String, String> thrName = getAllHisServiceItemXmMap();
		StatementItemDetail sdi = null;
		for (HisStatementItemDetail hsdi : lists) {
			sdi = new StatementItemDetail();
			String itemName = firName.get(hsdi.getItemId());
			String itemNameXm = thrName.get(hsdi.getItemSubId());
			String itemNameFl = secName.get(secCode.get(hsdi.getItemSubId()));
			BeanCopyUtil.copyProperties(hsdi, sdi);
			sdi.setItemName(itemName);
			sdi.setItemNameFl(itemNameFl);
			sdi.setItemNameXm(itemNameXm);
			list.add(sdi);
		}
		return list;
	}

	/**
	 * 根据挂号单ID查询临时处置项
	 * 
	 * @param regId
	 * @return
	 */
	public List<StatementItemDetail> getHisStatementItemDetailListLS(Long regId, String tooth) {
		List<StatementItemDetail> list = new ArrayList<StatementItemDetail>();
		List<HisStatementItemDetail> lists = new ArrayList<HisStatementItemDetail>();
		HisStatementItemDetail hsid = new HisStatementItemDetail();
		hsid.setStatementItemid(regId);
		hsid.setIfFormal(0);
		hsid.setTooth(tooth);
		hsid.setSqlSort(" tooth asc");
		lists = hisStatementItemDetailDao.selectList(hsid);

		Map<String, String> firName = getAllHisServiceItemMap();
		Map<String, String> secName = getAllHisServiceItemFlMap();
		Map<String, String> secCode = getIdHisServiceItemFlMap();
		Map<String, String> thrName = getAllHisServiceItemXmMap();
		StatementItemDetail sdi = null;
		for (HisStatementItemDetail hsdi : lists) {
			sdi = new StatementItemDetail();
			String itemName = firName.get(hsdi.getItemId());
			String itemNameXm = thrName.get(hsdi.getItemSubId());
			String itemNameFl = secName.get(secCode.get(hsdi.getItemSubId()));
			BeanCopyUtil.copyProperties(hsdi, sdi);
			sdi.setItemName(itemName);
			sdi.setItemNameFl(itemNameFl);
			sdi.setItemNameXm(itemNameXm);
			list.add(sdi);
		}
		return list;
	}

	/**
	 * 修改处置项主表
	 * 
	 * @param hsi
	 */
	public void updateHisStatementItem(HisStatementItem hsi) {
		hisStatementItemDao.updateByPrimaryKeySelective(hsi);
	}

	/**
	 * 修改处置项明细
	 * 
	 * @param hsi
	 */
	public void updateHisStatementItemDetail(HisStatementItemDetail hsi) {
		hisStatementItemDetailDao.updateByPrimaryKeySelective(hsi);
	}

	/**
	 * 修改结算单主表
	 * 
	 * @param hsi
	 */
	public void updateHisStatement(HisStatement hsi) {
		hisStatementDao.updateByPrimaryKeySelective(hsi);
	}

	/**
	 * 清除临时处置项
	 * 
	 * @param regId
	 */
	public boolean clearLSCZX(Long regId, String tooth) {
		// hisStatementItemDetailDao
		HisStatementItemDetail hsid = new HisStatementItemDetail();
		hsid.setStatementItemid(regId);
		if (!tooth.equals("-")) {
			hsid.setTooth(tooth);
		}
		hsid.setIfFormal(0);
		return hisStatementItemDetailDao.deleteSelective(hsid) > 0;
	}

	/**
	 * 清除临时处置项
	 * 
	 * @param regId
	 */
	public boolean updateLSqty(Long id, int qyt) {
		// hisStatementItemDetailDao
		HisStatementItemDetail hsid = hisStatementItemDetailDao.selectByPrimaryKey(id);
		HisStatementItem si = hisStatementItemDao.selectByPrimaryKey(hsid.getStatementItemid());
		if (si == null) {
			HisRegister hr = hisRegisterDao.selectByPrimaryKey(hsid.getStatementItemid());
			if (hr.getStatus() != 2) {
				return false;
			}
		}
		hsid.setQty(qyt);
		hsid.setItemAmount(hsid.getPrice().multiply(new BigDecimal(qyt)));
		return hisStatementItemDetailDao.updateByPrimaryKeySelective(hsid) > 0;
	}

	/**
	 * 修改结算单明细
	 * 
	 * @param hsi
	 */
	public void updateHisStatementCharge(HisStatementCharge hsi) {
		hisStatementChargeDao.updateByPrimaryKeySelective(hsi);
	}

	/**
	 * 获取处置单明细列表
	 * 
	 * @param hsid
	 * @return
	 */
	public List<HisStatementItemDetail> getHisStatementItemDetailList(HisStatementItemDetail hsid) {
		return hisStatementItemDetailDao.selectList(hsid);
	}

	/**
	 * 获取结算单明细列表
	 * 
	 * @param hsc
	 * @return
	 */
	public List<HisStatementCharge> getHisStatementItemChargeList(HisStatementCharge hsc) {
		return hisStatementChargeDao.selectList(hsc);
	}

	public BigDecimal getMoney(Long id) throws Exception {
		HisRegister hr = hisRegisterDao.selectByPrimaryKey(id);
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(hr.getStatementItemid());
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(hsi.getStatementId());
		hsc = hisStatementChargeDao.selectList(hsc).get(0);
		return hsc.getRealAmount();
	}

	// -------------------------------------------------

	public List<HisServiceItemXm> page4ListXm(String sessionCode, Boolean daTy, HisServiceItemXm hisServiceItemXm, String iNames, String charges, Integer pageNumber, Integer pageSize) {
		hisServiceItemXm.setSqlSort(" item_code asc ");
		String sqls = " and is_show = 1 and status=1 and hosp_code LIKE '%" + sessionCode + "%'";
		// hisServiceItemXm.setSqlStr();
		// if (daTy) {
		// hisServiceItemXm.setSqlStr(" and is_show = 1 ");
		// } else {
		// hisServiceItemXm.setSqlStr(" and is_show = 1 ");
		// }

		if (iNames != null && iNames != "") {
			sqls += " and item_jtname LIKE '%" + iNames + "%'";
		}
		if (charges != null && charges != "") {
			sqls += "and parent_id LIKE '%" + charges + "%'";
		}
		hisServiceItemXm.setSqlStr(sqls);
		hisServiceItemXm.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisServiceItemXm.setLimitStart(limitStart);
		return hisServiceItemXmDao.selectList(hisServiceItemXm);
	}

	// 二级处置项
	public List<HisServiceItemFl> page4ListFl(HisServiceItemFl hisServiceItemFl, String iNames, String charges, Integer pageNumber, Integer pageSize) {
		hisServiceItemFl.setSqlSort(" item_code asc ");
		hisServiceItemFl.setSqlStr(" and status = 1 ");

		if (iNames != null && iNames != "") {
			hisServiceItemFl.setSqlStr(" and item_name LIKE '%" + iNames + "%'");
		}
		if (charges != null && charges != "") {
			hisServiceItemFl.setSqlStr(" and parent_id LIKE '%" + charges + "%'");
		}

		hisServiceItemFl.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		hisServiceItemFl.setLimitStart(limitStart);
		return hisServiceItemFlDao.selectList(hisServiceItemFl);
	}

	// 二级处置项
	public Integer findCountFl(HisServiceItemFl hisServiceItemFl) {
		hisServiceItemFl.setSqlStr("status=1");
		return hisServiceItemFlDao.selectCount(hisServiceItemFl);
	}

	public Integer findCount(HisServiceItemXm hisServiceItemXm) {
		hisServiceItemXm.setSqlStr("status = 1");
		return hisServiceItemXmDao.selectCount(hisServiceItemXm);
	}

	// 三级处置项添加
	public int updateStatusXm(HisServiceItemXm hisServiceItemXm) {
		return hisServiceItemXmDao.updateByPrimaryKey(hisServiceItemXm);
	}

	// 二级更新
	public int updateStatusFl(HisServiceItemFl hisServiceItemFl) {
		return hisServiceItemFlDao.updateByPrimaryKey(hisServiceItemFl);
	}

	public HisServiceItemXm findById(String idjt) {
		return hisServiceItemXmDao.selectByPrimaryKey(idjt);
	}

	public void updatItemFlXm(HisServiceItemXm entiyjt) {
		hisServiceItemXmDao.updateByPrimaryKey(entiyjt);

	}

	public int updateByPrimaryKey(HisServiceItemXm entiyjt) {
		return hisServiceItemXmDao.updateByPrimaryKeySelective(entiyjt);
	}

	public int updatItemFlXms(HisServiceItemXm entiyjt) {
		return hisServiceItemXmDao.updateByPrimaryKey(entiyjt);

	}

	// 三级处置项查询，集合返回
	public List<HisServiceItemXm> selectItemXmInfo(HisServiceItemXm hisServiceItemXm) {
		return hisServiceItemXmDao.selectList(hisServiceItemXm);
	}
	// 查询

	public HisServiceItemFl findByIdFl(String itemCode) {
		return hisServiceItemFlDao.selectByPrimaryKey(itemCode);
	}

	public void addItemFlXm(HisServiceItemXm entiyjt) {
		hisServiceItemXmDao.insert(entiyjt);

	}

	public int addItemFlXms(HisServiceItemXm entiyjt) {
		return hisServiceItemXmDao.insertSelective(entiyjt);

	}

	public HisServiceItemFl findByIdToName(String itemCode) {
		return hisServiceItemFlDao.selectByPrimaryKey(itemCode);
	}

	public int updatItemXm(HisServiceItemXm hisServiceItemXm) {
		return hisServiceItemXmDao.updateByPrimaryKey(hisServiceItemXm);
	}

	public int addItemXlInfo(HisServiceItemXm hisServiceItemXm) {
		return hisServiceItemXmDao.insert(hisServiceItemXm);
	}

	public HisServiceItemFl selectFlName(String parentId) {
		return hisServiceItemFlDao.selectByPrimaryKey(parentId);
	}

	// 查询
	public HisServiceItem findByIdItem(String parentId) {
		return hisServiceItemDao.selectByPrimaryKey(parentId);
	}

	// 二级更新
	public int updatItemFl(HisServiceItemFl hisServiceItemFl) {
		return hisServiceItemFlDao.updateByPrimaryKey(hisServiceItemFl);
	}

	// 二级添加
	public int addItemFlInfo(HisServiceItemFl hisServiceItemFl) {
		return hisServiceItemFlDao.insert(hisServiceItemFl);
	}

	// 三级处置项项菜单
	public HisServiceItemXm findByIdXm(String itemcode) {
		return hisServiceItemXmDao.selectByPrimaryKey(itemcode);
	}

	// 二级id 校验
	public String checkById(String id) {
		HisServiceItemFl st = hisServiceItemFlDao.selectByPrimaryKey(id);
		String flag = "";
		if (st != null) {
			flag = "1";
		} else {
			flag = "2";
		}
		return flag;
	}

	public int addItemFl(HisServiceItemFl entiyjt) {
		return hisServiceItemFlDao.insertSelective(entiyjt);
	}

	public int deleteFlInfo(HisServiceItemFl hisServiceItemFl) {
		return hisServiceItemFlDao.deleteSelective(hisServiceItemFl);
	}

	public int deleteFlXmInfo(HisServiceItemXm hisServiceItemXm) {
		return hisServiceItemXmDao.deleteSelective(hisServiceItemXm);
	}

	public int insertAll(List<HisServiceItemXm> list) {
		return hisServiceItemXmDao.insertAll(list);
	}

	public int updateAll(List<HisServiceItemXm> list) {
		return hisServiceItemXmDao.updateAll(list);
	}
}
