package com.enjoyhis.service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.dao.HisEmployeeDao;
import com.enjoyhis.persistence.his.dao.HisPatientCaseDao;
import com.enjoyhis.persistence.his.dao.HisPatientDao;
import com.enjoyhis.persistence.his.dao.HisPaymentDao;
import com.enjoyhis.persistence.his.dao.HisPosDetailsDao;
import com.enjoyhis.persistence.his.dao.HisPrepayactDao;
import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemFlDao;
import com.enjoyhis.persistence.his.dao.HisServiceItemXmDao;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDelDao;
import com.enjoyhis.persistence.his.dao.HisStatementDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDetailDao;
import com.enjoyhis.persistence.his.dao.HisXtXrayDao;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.persistence.his.po.HisPayment;
import com.enjoyhis.persistence.his.po.HisPosDetails;
import com.enjoyhis.persistence.his.po.HisPrepayact;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisServiceItem;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.persistence.his.po.HisStatementItem;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.persistence.his.po.HisXtXray;
import com.enjoyhis.pojo.Employee;
import com.enjoyhis.pojo.HisPatientPojo;
import com.enjoyhis.pojo.HisStatementPojo;
import com.enjoyhis.pojo.PatientPj;
import com.enjoyhis.pojo.Register;
import com.enjoyhis.pojo.Statement;
import com.enjoyhis.pojo.StatementItemDetail;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.rmiclient.HesRegisterService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.LogUtils;
import com.enjoyhis.util.YPUtil;
import com.enjoyhis.util.excel.PrintExcelUtil;

@Service("hisPatientService")
public class HisPatientService {
	@Autowired
	HisPatientDao hisPatientDao;
	@Autowired
	HisEmployeeDao hisEmployeeDao;
	@Autowired
	HisStatementDao hisStatementDao;
	@Autowired
	HisStatementItemDao hisStatementItemDao;
	@Autowired
	HisStatementItemDetailDao hisStatementItemDetailDao;
	@Autowired
	HisPaymentDao hisPaymentDao;
	@Autowired
	HisPrepayactDao hisPrepayactDao;
	@Autowired
	HisStatementChargeDao hisStatementChargeDao;
	@Autowired
	HisStatementChargeDelDao hisStatementChargeDelDao;
	@Autowired
	HisServiceItemDao hisServiceItemDao;
	@Autowired
	HisServiceItemFlDao hisServiceItemFlDao;
	@Autowired
	HisServiceItemXmDao hisServiceItemXmDao;
	@Autowired
	HisXtXrayDao hisXtXrayDao;
	@Autowired
	private SysConfigService sysConfigService;// 院区service
	@Autowired
	private SysSeqService sysSeqService;
	@Autowired
	private HisRegisterDao hisRegisterDao;
	@Autowired
	private HisPosDetailsDao hisPosDetailsDao;
	@Autowired
	private HisRegisterService hisRegisterService;
	@Autowired
	private HisPatientCaseDao hisPatientCaseDao;

	private AccountService accountService = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
	private HesPatientService hesPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);
	private HesRegisterService hesRegisterService = (HesRegisterService) HessianFactoryUtil.getHessianObj(HesRegisterService.class);
	private Logger log = LogUtils.CLIENT_TRACE;

	public Map<String, String> getHisServiceItemMap() {
		List<HisServiceItem> list = hisServiceItemDao.selectList(new HisServiceItem());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItem hsif : list) {
			map.put(hsif.getItemCode(), hsif.getItemName());
		}
		return map;
	}

	public Map<String, String> getHisServiceItemFlMap() {
		List<HisServiceItemFl> list = hisServiceItemFlDao.selectList(new HisServiceItemFl());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItemFl hsif : list) {
			map.put(hsif.getItemCode(), hsif.getItemName());
		}
		return map;
	}

	public Map<String, String> getHisServiceItemXmMap() {
		List<HisServiceItemXm> list = hisServiceItemXmDao.selectList(new HisServiceItemXm());
		Map<String, String> map = new HashMap<String, String>();
		for (HisServiceItemXm hsif : list) {
			map.put(hsif.getItemCode(), hsif.getItemName());
		}
		return map;
	}

	/**
	 * 获取打折方式的map
	 * 
	 * @return
	 */
	public Map<String, String> getHisPaymentMap() {
		List<HisPayment> list = hisPaymentDao.selectList(new HisPayment());
		Map<String, String> map = new HashMap<String, String>();
		for (HisPayment hsif : list) {
			map.put(hsif.getTypeCode(), hsif.getPayType());
		}
		return map;
	}

	public Map<Long, String> getHisEmployeeMap() {
		List<HisEmployee> list = hisEmployeeDao.selectList(new HisEmployee());
		Map<Long, String> map = new HashMap<Long, String>();
		for (HisEmployee he : list) {
			map.put(he.getId(), he.getEmployeeName());
		}
		return map;
	}

	public boolean isDoc(Long id) {
		HisEmployee he = hisEmployeeDao.selectByPrimaryKey(id);
		int i = he.getUserType();
		return i != 1;
	}

	public HisPatientPojo getHisPatient(Long id) {
		HisPatient hisPatient = hisPatientDao.selectByPrimaryKey(id);
		return getPatientInfo(hisPatient);
	}

	public List<HisStatementItemDetail> getHisStatementItemDetailList(Long id) {
		StatementItemDetail sid = new StatementItemDetail();
		sid.setStatementItemid(id);
		sid.setSqlSort(" id asc");
		return hisStatementItemDetailDao.selectList(sid);
	}

	public List<StatementItemDetail> getHisStatementItemList(Long id) {
		Map<Long, String> map = getHisEmployeeMap();
		Map<String, String> hisServiceItemMap = getHisServiceItemMap();
		Map<String, String> hisServiceItemXmMap = getHisServiceItemXmMap();
		StatementItemDetail sid = new StatementItemDetail();
		sid.setStatementItemid(id);
		sid.setSqlSort(" id asc");
		List<HisStatementItemDetail> lists = hisStatementItemDetailDao.selectList(sid);
		List<StatementItemDetail> list = new ArrayList<StatementItemDetail>();
		StatementItemDetail hsip;
		for (HisStatementItemDetail hsis : lists) {
			hsip = new StatementItemDetail();
			BeanCopyUtil.copyProperties(hsis, hsip);
			if (hsip.getTooth() == null) {// test
				hsip.setTooth("-");
			} else if (hsip.getTooth().equals(null)) {
				hsip.setTooth("-");
			}
			hsip.setDocName(map.get(hsis.getDocId()));
			hsip.setItemName(hisServiceItemMap.get(hsis.getItemId()));
			hsip.setItemNameXm(hisServiceItemXmMap.get(hsis.getItemSubId()));
			list.add(hsip);
		}
		return list;
	}

	public String getActName(Long id, AccountService as) {
		HisStatement hs = as.selectByPrimaryKey4hs(id);
		Integer act = hs.getActId();
		if (act == null) {
			return "无";
		} else {
			return hisPrepayactDao.selectByPrimaryKey(act).getActname();
		}
	}

	/**
	 * 获取收费字典项目
	 * 
	 * @return
	 */
	public List<HisPayment> getHisPaymentList() {
		return hisPaymentDao.selectList(new HisPayment());
	}

	/**
	 * 获取收费字典项目（绑定患者）
	 * 
	 * @return
	 */
	public List<HisPayment> getPaymentList() {
		HisPayment hp = new HisPayment();
		hp.setTypeName("SCHD");
		return hisPaymentDao.selectList(hp);
	}

	/**
	 * 获取活动字典
	 * 
	 * @return
	 */
	public List<HisPrepayact> getHisPrepayactList() {
		HisPrepayact hp = new HisPrepayact();
		hp.setStatus(1);
		return hisPrepayactDao.selectList(hp);
	}

	public List<HisStatementCharge> getHisStatementChargeList(Long id) {
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(id);
		return hisStatementChargeDao.selectList(hsc);
	}

	/**
	 * 通过处置项ID查询收费明细
	 * 
	 * @param id
	 * @return
	 */
	public List<HisStatementCharge> getChargeList(Long id) {
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(id);
		HisStatementCharge hsc = new HisStatementCharge();
		if (hsi != null) {
			hsc.setStatementId(hsi.getStatementId());
			return hisStatementChargeDao.selectList(hsc);
		}
		return new ArrayList<>();
	}

	public Statement getPreDepositTransferjt(HisStatement id) {
		Statement s = new Statement();
		s.setTotalAmount(id.getTotalAmount());
		s.setPatName(hisPatientDao.selectByPrimaryKey(id.getPatId()).getPatName());
		s.setRemark(id.getRemark());
		return s;
	}

	public String getStatementId(Long id) {
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(id);
		HisStatement hs = hisStatementDao.selectByPrimaryKey(hsi.getStatementId());
		if (hs.getOldStatementCode() == null) {
			return hs.getId().toString();
		} else {
			return hs.getOldStatementCode();
		}
	}

	public String getStatementIdByItemId(Long id) {
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(id);
		HisStatement hs = hisStatementDao.selectByPrimaryKey(hsi.getStatementId());
		return hs.getId().toString();
	}

	public String getEmployeeName(Long statementId, AccountService as) {
		HisStatement hs = as.selectByPrimaryKey4hs(statementId);
		HisEmployee he = hisEmployeeDao.selectByPrimaryKey(hs.getOperator());
		if (he == null) {
			return null;
		} else {
			return he.getEmployeeName();
		}
	}

	public List<HisPosDetails> getPosList() {
		HisPosDetails hpd = new HisPosDetails();
		hpd.setStatus(1);
		List<HisPosDetails> list = hisPosDetailsDao.selectList(hpd);
		for (HisPosDetails hpds : list) {
			hpds.setBankName(null);
			hpds.setBankNo(null);
			hpds.setStatus(null);
			hpds.setUnitCode(null);
		}
		return list;
	}

	public String getRemark(Long id) {
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(id);
		HisStatement hsc = new HisStatement();
		if (hsi != null) {
			hsc = hisStatementDao.selectByPrimaryKey(hsi.getStatementId());
		}
		return hsc.getRemark();
	}

	public List<HisStatementPojo> getHisStatementList(Long id) {
		Map<Long, String> map = getHisEmployeeMap();
		HisStatement hs = new HisStatement();
		hs.setPatId(id);
		List<HisStatement> lists = hisStatementDao.selectList(hs);
		List<HisStatementPojo> list = new ArrayList<HisStatementPojo>();
		HisStatementPojo hsp;
		for (HisStatement hst : lists) {
			hsp = new HisStatementPojo();
			BeanCopyUtil.copyProperties(hst, hsp);
			hsp.setDocName(map.get(hst.getDocId()));
			hsp.setStatusName(hst.getStatus() == 1 ? "已结" : "未结");
			// 1挂号，2处置收费，3收欠费，4结算单调整，9预存，10退预存，11预存转账，12处置单调整
			String msg;
			switch (hst.getAccountType()) {
			case 1:
				msg = "挂号";
				break;
			case 2:
				msg = "处置收费";
				break;
			case 3:
				msg = "收欠费";
				break;
			case 4:
				msg = "结算单调整";
				break;
			case 9:
				msg = "预存";
				break;
			case 10:
				msg = "退预存";
				break;
			case 11:
				msg = "预存转账";
				break;
			case 13:
				msg = "会计退费";
				break;
			default:
				msg = "处置单调整";
				break;
			}
			hsp.setAccountTypeName(msg);
			list.add(hsp);
		}
		return list;
	}

	public List<HisStatement> getStatementList(HisStatement hs) {
		return hisStatementDao.selectList(hs);
	}

	public HisStatement getStatementOne(HisStatement hs) {
		return hisStatementDao.selectOne(hs);
	}

	public HisStatementItem getStatementItemOne(Long hs) {
		return hisStatementItemDao.selectByPrimaryKey(hs);
	}

	public HisStatement getStatementOne(Long hs) {
		return hisStatementDao.selectByPrimaryKey(hs);
	}

	public int getStatementCount(HisStatement hs) {
		return hisStatementDao.selectCount(hs);
	}

	public void updateStatement(HisStatement hs) {
		hisStatementDao.updateByPrimaryKeySelective(hs);
	}

	public void updateStatementItem(HisStatementItem hs) {
		hisStatementItemDao.updateByPrimaryKeySelective(hs);
	}

	public Long insertStatement(HisStatement hs) {
		hisStatementDao.insertSelective(hs);
		return hs.getId();
	}

	public List<HisStatementPojo> getHisStatementList(HisStatement hs) {
		Map<Long, String> map = getHisEmployeeMap();
		List<HisStatement> lists = hisStatementDao.selectList(hs);
		List<HisStatementPojo> list = new ArrayList<HisStatementPojo>();
		HisStatementPojo hsp;
		for (HisStatement hst : lists) {
			hsp = new HisStatementPojo();
			BeanCopyUtil.copyProperties(hst, hsp);
			hsp.setDocName(map.get(hst.getDocId()));
			hsp.setStatusName(hst.getStatus() == 1 ? "已结" : "未结");
			hsp.setDiscountAmount(getDiscountAmount(hst));
			list.add(hsp);
		}
		return list;
	}

	/**
	 * 根据患者id,查询患者信息
	 * 
	 * @param patId
	 * @return
	 */
	public HisPatientPojo findById(Long patId) {
		HisPatient hisPatient = hisPatientDao.selectByPrimaryKey(patId);
		if (null == hisPatient) {
			return null;
		}
		HisPatientPojo hisPatientPojo = getPatientInfo(hisPatient);
		return hisPatientPojo;
	}

	/**
	 * 获取患者信息
	 * 
	 * @param hisPatient
	 * @return
	 */
	private HisPatientPojo getPatientInfo(HisPatient hisPatient) {
		HisPatientPojo hisPatientPojo = new HisPatientPojo();
		if (null == hisPatient)
			return hisPatientPojo;
		try {
			BeanCopyUtil.copyProperties(hisPatient, hisPatientPojo);
			if (null != hisPatientPojo.getBirthday()) {
				String fmtBirthday = DateUtils.date_sdf.format(hisPatientPojo.getBirthday());
				hisPatientPojo.setFmtBirthday(fmtBirthday);
			}
			if (null != hisPatientPojo.getLastappointDate()) {
				String fmtlastappointDate = DateUtils.date_sdf.format(hisPatientPojo.getLastappointDate());
				hisPatientPojo.setFmtlastappointDate(fmtlastappointDate);
			}
			if (null != hisPatientPojo.getLasthospDate()) {
				String fmtlasthospDate = DateUtils.date_sdf.format(hisPatientPojo.getLasthospDate());
				hisPatientPojo.setFmtlasthospDate(fmtlasthospDate);
			}
			if (null != hisPatientPojo.getNewlyDate()) {
				String fmtNewlyDate = DateUtils.date_sdf.format(hisPatientPojo.getNewlyDate());
				hisPatientPojo.setFmtNewlyDate(fmtNewlyDate);
			}
			if (null != hisPatientPojo.getMaindocId()) {
				HisEmployee hisEmployee = hisEmployeeDao.selectByPrimaryKey(hisPatientPojo.getMaindocId());
				if (hisEmployee != null) {
					hisPatientPojo.setDocName(hisEmployee.getEmployeeName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hisPatientPojo;
	}

	public PatientPj getPatientPj(Long id) {
		HisPatient hisPatient = hisPatientDao.selectByPrimaryKey(id);
		if (null == hisPatient) {
			return null;
		}
		PatientPj hisPatientPojo = new PatientPj();
		BeanCopyUtil.copyProperties(hisPatient, hisPatientPojo);
		return hisPatientPojo;
	}

	/**
	 * 保存患者信息
	 * 
	 * @param record
	 */
	public void save(HisPatient record) {
		hisPatientDao.insertSelective(record);
	}

	public List<HisPatientPojo> findPatientList(HisPatient record) {
		try {
			List<HisPatient> selectList = hisPatientDao.selectList(record);
			List<HisPatientPojo> list = new ArrayList<HisPatientPojo>();
			if (null != selectList && selectList.size() > 0) {
				for (HisPatient one : selectList) {
					HisPatientPojo patient = getPatientInfo(one);
					list.add(patient);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 新建HisStatementCharge
	 * 
	 * @param hsc
	 */
	public void insertHisStatementCharge(HisStatementCharge hsc) {
		hisStatementChargeDao.insertSelective(hsc);
	}

	/**
	 * 删除HisStatementCharge
	 * 
	 * @param statementId
	 */
	public boolean deleteHisStatementCharge(Long statementId) {
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(statementId);
		List<HisStatementCharge> list = hisStatementChargeDao.selectList(hsc);
		if (list.size() == 0) {
			return true;
		}
		int i = 0;
		for (HisStatementCharge hscs : list) {
			i = hisStatementChargeDao.deleteByPrimaryKey(hscs.getId());
			if (i == 0) {
				break;
			}
		}
		return i > 0;
	}

	/**
	 * 条件查询患者总纪录数
	 * 
	 * @param record
	 * @return
	 */
	public Integer findPatientCount(HisPatient record) {
		return hisPatientDao.selectCount(record);
	}

	/**
	 * 获取优惠金额
	 * 
	 * @param hsp
	 * @return
	 */
	private BigDecimal getDiscountAmount(HisStatement hsp) {
		if (hsp.getPayAmount() == null) {
			hsp.setPayAmount(new BigDecimal("0"));
		}
		if (hsp.getDebtAmount() == null) {
			hsp.setDebtAmount(new BigDecimal("0"));
		}
		if (hsp.getTotalAmount() == null) {
			hsp.setTotalAmount(new BigDecimal("0"));
		}
		BigDecimal bd = hsp.getPayAmount().add(hsp.getDebtAmount());
		return hsp.getTotalAmount().subtract(bd);
	}

	/**
	 * 根据患者姓名或者拼音简写、电话查询患者 注：默认是本地数据，如果有条件查询再从集团查
	 * 
	 * @param name
	 * @param mobile
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public List<Object> page4List(String name, String mobile, Long docId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("docId", docId);
		if (null == pageNumber && null == pageSize) {
			map.put("limitCount", 20);
			map.put("limitStart", 0);
		} else {
			map.put("limitCount", pageSize);
			map.put("limitStart", (pageSize * (pageNumber - 1)));
		}

		List<Object> page4List = null;
		if (docId == null) {
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(mobile)) {
				try {
					page4List = hesPatientService.patientPage4List(map);
				} catch (HessianRuntimeException e) {
					log.error("hession掉服务器接口错误");
				}
			}
			if (page4List == null || page4List.size() == 0) {
				page4List = hisPatientDao.patientPage4List(map);
			}
		} else {
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(mobile)) {
				try {
					page4List = hesPatientService.page4List(map);
				} catch (HessianRuntimeException e) {
					log.error("hession掉服务器接口错误");
				}
			}
			if (page4List == null || page4List.size() == 0) {
				page4List = hisPatientDao.page4List(map);
			}
		}

		return page4List;
	}

	/**
	 * 条件查询患者总记录数
	 * 
	 * @param name
	 * @param mobile
	 * @param docId
	 * @return
	 */
	public Integer query4Count(String name, String mobile, Long docId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("docId", docId);
		Integer page4Count = null;
		if (docId == null) {
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(mobile)) {
				try {
					page4Count = hesPatientService.patientQuery4Count(map);
				} catch (HessianRuntimeException e) {
					log.error("hession掉服务器接口错误");
				}
			}
			if (page4Count == null || page4Count == 0) {
				page4Count = hisPatientDao.patientPage4Count(map);
			}
		} else {
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(mobile)) {
				try {
					page4Count = hesPatientService.query4Count(map);
				} catch (HessianRuntimeException e) {
					log.error("hession掉服务器接口错误");
				}
			}
			if (page4Count == null || page4Count == 0) {
				page4Count = hisPatientDao.page4Count(map);
			}
		}
		return page4Count;
	}

	/**
	 * 获取消费本金
	 * 
	 * @param id
	 * @param totalAmount
	 * @return
	 */
	public BigDecimal getPayAmount(Long id, BigDecimal totalAmount) {
		HisPatient hp = hisPatientDao.selectByPrimaryKey(id);
		return totalAmount.subtract(hp.getGivenAmount().multiply(totalAmount).divide(hp.getAccountAmount(), 2, BigDecimal.ROUND_HALF_UP));
	}

	public BigDecimal getPayAmount(HisPatient hp, BigDecimal totalAmount) {
		return totalAmount.subtract(hp.getGivenAmount().multiply(totalAmount).divide(hp.getAccountAmount(), 2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 修改当前用户账目(减少)
	 * 
	 * @param id
	 * @param totalAmount
	 * @param givenAmount
	 */
	public void updatePatientPreCharge(Long id, BigDecimal totalAmount, BigDecimal payAmount, BigDecimal givenAmount) {
		HisPatient hp = hisPatientDao.selectByPrimaryKey(id);
		hp.setAccountAmount(hp.getAccountAmount().subtract(totalAmount));
		hp.setOriginalAmount(hp.getOriginalAmount().subtract(payAmount));
		hp.setGivenAmount(hp.getGivenAmount().subtract(givenAmount));
		hisPatientDao.updateByPrimaryKeySelective(hp);
	}

	/**
	 * 修改账户
	 * 
	 * @param hp
	 */
	public int updateByPrimaryKeySelective(HisPatient hp) {
		return hisPatientDao.updateByPrimaryKeySelective(hp);
	}

	/**
	 * 保存患者信息操作
	 * 
	 * @param type 1预约 2挂号
	 */
	public HisPatient save(Integer type, HisPatient patient) {
		Date now = DateUtils.getDate();
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
		if (type == 1) {// 预约
			// 预约的时候是临时编号，不能走pat_no的正式编号，现从patient取
			Long tempno = sysSeqService.getTableSeq(unitCode, "his_patient");
			String tempnoS = tempno.toString();
			if (unitCode > 1000) {
				tempnoS = tempnoS.substring(0, tempnoS.length() - unitCode.toString().length());
			} else if (unitCode >= 100) {
				tempnoS = tempnoS.substring(0, tempnoS.length() - 1 - unitCode.toString().length());
			} else if (unitCode >= 10) {
				tempnoS = tempnoS.substring(0, tempnoS.length() - 2 - unitCode.toString().length());
			} else {
				tempnoS = tempnoS.substring(0, tempnoS.length() - 3 - unitCode.toString().length());
			}
			if (tempnoS.length() > 5) {
				patient.setPatNo("TEMP" + "10" + unitCode + DateUtils.date2Str(now, DateUtils.yyyyMMdd).substring(2, 8) + tempnoS.substring(tempnoS.length() - 5, tempnoS.length()));
			} else {
				patient.setPatNo("TEMP" + "10" + unitCode + DateUtils.date2Str(now, DateUtils.yyyyMMdd).substring(2, 8) + tempnoS);
			}
		} else {
			Long patNo = sysSeqService.getTableSeq(unitCode, "his_patient_no");
			String patientNo = patNo.toString();
			if (unitCode > 1000) {
				patientNo = patientNo.substring(0, patientNo.length() - unitCode.toString().length());
			} else if (unitCode >= 100) {
				patientNo = patientNo.substring(0, patientNo.length() - 1 - unitCode.toString().length());
			} else if (unitCode >= 10) {
				patientNo = patientNo.substring(0, patientNo.length() - 2 - unitCode.toString().length());
			} else {
				patientNo = patientNo.substring(0, patientNo.length() - 3 - unitCode.toString().length());
			}

			if (patientNo.length() > 5) {
				patient.setPatNo("10" + unitCode + DateUtils.date2Str(now, DateUtils.yyyyMMdd).substring(2, 8) + patientNo.substring(patientNo.length() - 5, patientNo.length()));
			} else {
				patient.setPatNo("10" + unitCode + DateUtils.date2Str(now, DateUtils.yyyyMMdd).substring(2, 8) + patientNo);
			}
		}

		String pym = YPUtil.CNChar2PY(patient.getPatName());

		Long seqNextval = sysSeqService.getTableSeq(unitCode, "his_patient");
		patient.setId(seqNextval);
		patient.setPym(pym);
		if (patient.getMaindocId() != null) {
			HisEmployee maindoc = hisEmployeeDao.selectByPrimaryKey(patient.getMaindocId());
			patient.setMaindocName(maindoc.getEmployeeName());
		}
		patient.setUnitCode(unitCode);
		patient.setCreateTime(now);
		patient.setModifyTime(now);
		hisPatientDao.insertSelective(patient);
		log.info("新增了一条patient" + patient);
		return patient;
	}

	/**
	 * 修改患者信息操作
	 * 
	 * @return
	 */
	public HisPatient update(Long id, String patName, String patNo, String userSex, String persid, Integer age, String birthday, String mobile, String tel, String email, String address, String source, String introducer, Long maindocId, String newlyAsk, String allergicHis, String remark) {
		HisPatient record = new HisPatient();
		Date now = DateUtils.getDate();
		String pym = YPUtil.CNChar2PY(patName);
		record.setId(id);
		record.setPatName(patName);
		record.setPatNo(patNo);
		record.setUserSex(userSex);
		record.setPym(pym);
		if (null != persid)
			record.setPersid(persid);
		if (null != age)
			record.setAge(age);
		if (null != birthday)
			record.setBirthday(DateUtils.str2Date(birthday, DateUtils.date_sdf));
		if (null != mobile)
			record.setMobile(mobile);
		if (null != tel)
			record.setTel(tel);
		if (null != email)
			record.setEmail(email);
		if (null != address)
			record.setAddress(address);
		if (null != source)
			record.setSource(source);
		if (null != introducer)
			record.setIntroducer(introducer);
		if (null != maindocId) {
			record.setMaindocId(maindocId);
			HisEmployee maindoc = hisEmployeeDao.selectByPrimaryKey(maindocId);
			record.setMaindocName(maindoc.getEmployeeName());
		}
		if (null != newlyAsk)
			record.setNewlyAsk(newlyAsk);
		if (null != allergicHis)
			record.setAllergicHis(allergicHis);
		if (null != remark)
			record.setRemark(remark);
		record.setModifyTime(now);
		hisPatientDao.updateByPrimaryKeySelective(record);
		return hisPatientDao.selectByPrimaryKey(id);
	}

	public boolean insertFy2Jt(Long id, BigDecimal payAmount, Integer status) throws MalformedURLException {
		boolean flag = false;
		try {
			flag = accountService.hisPatientUpadte(id, payAmount, status);
		} catch (HessianRuntimeException e) {
			log.error("hession同步错误，请检查网络。");
		}
		return flag;
	}

	public boolean insertFy2Jt(Long id, HisMqEnum hisMqEnum) throws MalformedURLException {
		boolean flag = false;
		try {
			switch (hisMqEnum) {
			case his_register:
				flag = accountService.exception4hisRegister(get2hisRegister(id));
				break;
			case his_statement:
				flag = accountService.exception4hisStatement(get2hisStatement(id));
				break;
			case his_statement_charge:
				flag = accountService.exception4hisStatementCharge(get2hisStatementCharge(id));
				break;
			case his_statement_item:
				flag = accountService.exception4hisStatementItem(get2hisStatementItem(id));
				break;
			case his_statement_item_detail:
				flag = accountService.exception4hisStatementItemDetail(get2hisStatementItemDetail(id));
				break;
			case his_patient_case:
				flag = accountService.exception4hisPatientCase(get2hisPatientCase(id));
				break;
			case his_patient:
				flag = accountService.exception4hisPatient(get2hisPatient(id));
				break;
			default:
				flag = true;
			}
		} catch (HessianRuntimeException e) {
			log.error("hession同步错误，请检查网络。");
		}
		return flag;
	}

	public HisRegister get2hisRegister(Long msg) {
		HisRegister hr = hisRegisterDao.selectByPrimaryKey(msg);
		return hr;
	}

	public HisStatement get2hisStatement(Long msg) {
		HisStatement hs = hisStatementDao.selectByPrimaryKey(msg);
		return hs;
	}

	public HisStatementCharge get2hisStatementCharge(Long msg) {
		HisStatementCharge hs = hisStatementChargeDao.selectByPrimaryKey(msg);
		return hs;
	}

	public HisStatementItem get2hisStatementItem(Long msg) {
		HisStatementItem hs = hisStatementItemDao.selectByPrimaryKey(msg);
		return hs;
	}

	public HisPatient get2hisPatient(Long msg) {
		HisPatient hs = hisPatientDao.selectByPrimaryKey(msg);
		return hs;
	}

	public HisPatientCase get2hisPatientCase(Long msg) {
		HisPatientCase hs = hisPatientCaseDao.selectByPrimaryKey(msg);
		return hs;
	}

	public HisStatementItemDetail get2hisStatementItemDetail(Long msg) {
		HisStatementItemDetail hs = hisStatementItemDetailDao.selectByPrimaryKey(msg);
		return hs;
	}

	@SuppressWarnings({ "rawtypes" })
	public PageResultForBootstrap page4Data(String name, String mobile, Long docId, Integer pageNumber, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("docId", docId);
		if (null == pageNumber && null == pageSize) {
			map.put("limitCount", null);
			map.put("limitStart", null);
		} else {
			map.put("limitCount", pageSize);
			map.put("limitStart", (pageSize * (pageNumber - 1)));
		}
		// 先从云上查询患者，如果云有本地无，挂号或预约操作时需要向本地添加数据
		Map<String, Object> map2 = new HashMap<>();
		if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(mobile)) {
			try {
				map2 = hesPatientService.page4Data(map);
			} catch (HessianRuntimeException e) {
				log.error("hession掉服务器接口错误");
			}
		}

		PageResultForBootstrap page = new PageResultForBootstrap();
		if (null != map2 && null != map2.get("list") && null != map2.get("count")) {
			page.setRows((List) map2.get("list"));
			page.setTotal((Integer) map2.get("count"));
		} else {// 如果集团没有查到数据，需要从本地查询患者
			List<Object> list = hisPatientDao.patientPage4List(map);
			Integer count = hisPatientDao.patientPage4Count(map);
			page = new PageResultForBootstrap();
			page.setTotal(count);
			page.setRows(list);
		}
		return page;
	}

	public List<HisXtXray> getHisXtXrayList() {
		return hisXtXrayDao.selectList(new HisXtXray());
	}

	/**
	 * 查询患者就诊记录
	 * 
	 * @author tianfei
	 * @param pid
	 * @param maindocId
	 * @return
	 */
	public List<Register> registerByPid(Long pid, Long maindocId) {
		List<Register> list = null;
		try {
			list = hesRegisterService.registerByPid(pid, maindocId);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
		}
		if (list != null && list.size() > 0) {
			return list;
		}
		HisRegister record = new HisRegister();
		record.setPatId(pid);
		List<Register> query4List = hisRegisterService.query4List(record);
		return query4List;
	}

	public byte[] getCardDetailListExcel(List<Object> list) throws Exception {
		List<Map<String, String>> resultList = new ArrayList<>();
		for (Object o : list) {
			Map<String, String> map = (Map<String, String>) o;
			resultList.add(map);
		}

		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("日期");
		header.add("医生");
		header.add("处置项金额");
		header.add("优惠金额");
		header.add("应收金额");
		header.add("欠费金额");

		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String, String> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.get("times"));
			treeMap.put("C", object.get("NAME"));
			treeMap.put("D", object.get("czxje"));
			treeMap.put("E", object.get("yhje"));
			treeMap.put("F", object.get("shje"));
			treeMap.put("G", object.get("qkje"));
			dataList.add(treeMap);
		}

		map.put(PrintExcelUtil.HEADERINFO, header);
		map.put(PrintExcelUtil.DATAINFON, dataList);
		wb = PrintExcelUtil.saveExcelData(wb, map);
		// PrintExcelUtil.setColumnAlign(wb,
		// "right","B","F","G","H","I","J","O","P");
		// PrintExcelUtil.setColumnAlign(wb, "left", "C");
		// PrintExcelUtil.setColumnwidth(wb, 4000, "B", "C");
		byte[] bs = PrintExcelUtil.writeExcel(wb);
		return bs;
	}

	public List<Object> selectDateList(String strDate, String endDate, Long docCode) {
		HisStatement hs = new HisStatement();
		String sqlStr = " 1=1 " + " AND TO_DAYS(create_time)>=TO_DAYS('" + strDate + "') " + " AND TO_DAYS(create_time)<=TO_DAYS('" + endDate + "') ";
		if (docCode > 0) {
			sqlStr += " AND doc_id = " + docCode;
		} else {
			sqlStr += " AND doc_id IS NOT NULL ";
		}
		hs.setSqlStr(sqlStr);
		return hisStatementDao.selectDateList(hs);
	}

	public BigDecimal getDocMaxDz(Long id) {
		HisRegister hp = hisRegisterDao.selectByPrimaryKey(id);
		HisEmployee he = hisEmployeeDao.selectByPrimaryKey(hp.getDentistId());
		return he.getDiscRate();
	}

	// 保存患者信息
	public int savaRegisterInfo(Register register) {
		return hisRegisterDao.insert(register);
	}
}
