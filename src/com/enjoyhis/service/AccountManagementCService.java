package com.enjoyhis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.dao.HisStatementDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDao;
import com.enjoyhis.persistence.his.dao.HisStatementItemDetailDao;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.persistence.his.po.HisStatementItem;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisMqEnum;

@Service("accountManagementCService")
public class AccountManagementCService {
	@Autowired
	private HisPatientService hisPatientService;
	@Autowired
	private HisServiceItemService hisServiceItemService;
	@Autowired
	private SysSeqService sysSeqService;
	@Autowired
	HisStatementDao hisStatementDao;
	@Autowired
	HisStatementChargeDao hisStatementChargeDao;
	@Autowired
	HisStatementItemDao hisStatementItemDao;
	@Autowired
	HisRegisterDao hisRegisterDao;
	@Autowired
	HisStatementItemDetailDao hisStatementItemDetailDao;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private HisMqService mq;

	public void saveDemo(AccountService as) throws Exception {
		System.out.print("1:");
		System.out.println(as.saveDemo1() ? "1" : "0");
		// System.out.print("2:");
		// System.out.println(as.saveDemo2()?"1":"0");
	}

	/**
	 * 预充值
	 * 
	 * @param patId
	 * @param actId
	 * @param totalAmount
	 * @param as
	 * @param payAmount
	 * @param givenAmount
	 * @param unit
	 * @param operator
	 * @param listC
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean preCharge(Long patId, Integer actId, BigDecimal totalAmount, AccountService as, BigDecimal payAmount, BigDecimal givenAmount, int unit, Long operator, List<HisStatementCharge> listC) throws Exception {

		Date date = new Date();
		// 更新云端客户账目
		HisPatient hp = as.selectByPrimaryKey(patId);
		hp.setAccountAmount(hp.getAccountAmount().add(totalAmount));
		hp.setGivenAmount(hp.getGivenAmount().add(givenAmount));
		hp.setOriginalAmount(hp.getOriginalAmount().add(payAmount));
		hp.setModifyTime(date);
		int i = as.updateByPrimaryKeySelective(hp);
		if (i < 1) {
			return i > 0;
		}
		// 新建本地结算总表
		HisStatement hs = new HisStatement();
		hs.setActId(actId);
		hs.setAccountType(9);
		hs.setPatId(patId);
		hs.setOperator(operator);
		hs.setTotalAmount(totalAmount);
		hs.setPayAmount(payAmount);
		hs.setUnitCode(unit);
		hs.setCreateTime(date);
		hs.setModifyTime(date);
		hs.setStatus(1);
		hs.setFlag(0);
		hs.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		Long sId = hisPatientService.insertStatement(hs);
		try{
			as.insertYC(hs, null);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(hs.getId(), HisMqEnum.his_statement);
		}
		// 新建本地结算明细表
		for (HisStatementCharge hscs : listC) {
			hscs.setStatementId(sId);
			hscs.setAccountType(9);
			hscs.setUnitCode(unit);
			hscs.setCreateTime(date);
			hscs.setModifyTime(date);
			hscs.setOperator(operator);
			hscs.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hscs);
			try{
				as.insertYC(null, hscs);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hscs.getId(), HisMqEnum.his_statement_charge);
			}
		}
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(sId);
		hsc.setAccountType(9);
		hsc.setUnitCode(unit);
		hsc.setCreateTime(date);
		hsc.setModifyTime(date);
		hsc.setOperator(operator);
		hsc.setPaymentType("ZSJE");
		hsc.setRealAmount(givenAmount);
		hsc.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
		hisPatientService.insertHisStatementCharge(hsc);
		try{
			as.insertYC(null, hsc);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(hsc.getId(), HisMqEnum.his_statement_charge);
		}
			return i > 0;
		}

	/**
	 * 退预存
	 * 
	 * @param patId
	 * @param unit
	 * @param operator
	 * @param totalAmount
	 * @param payAmount
	 * @param givenAmount
	 * @param remark
	 * @param listC
	 * @param as
	 * @return
	 */
	@Transactional
	public boolean preReCharge(Long patId, int unit, Long operator, BigDecimal totalAmount, String remark, List<HisStatementCharge> listC, AccountService as) throws Exception {
		// 更新云端客户账目
		Date date = new Date();
		HisPatient hp = as.selectByPrimaryKey(patId);
		BigDecimal payAmount = hisPatientService.getPayAmount(hp, totalAmount);
		BigDecimal givenAmount = totalAmount.subtract(payAmount);
		hp.setAccountAmount(hp.getAccountAmount().subtract(totalAmount));
		hp.setGivenAmount(hp.getGivenAmount().subtract(givenAmount));
//		hp.setOriginalAmount(hp.getOriginalAmount().subtract(payAmount));
		hp.setModifyTime(date);
		int i = as.updateByPrimaryKeySelective(hp);
		if (i < 1) {
			return i > 0;
		}
		HisStatement hs = new HisStatement();
		hs.setAccountType(10);
		hs.setPatId(patId);
		hs.setOperator(operator);
		hs.setTotalAmount(getBackBigDecimal(totalAmount));
		hs.setPayAmount(getBackBigDecimal(payAmount));
		hs.setUnitCode(unit);
		hs.setCreateTime(date);
		hs.setModifyTime(date);
		hs.setStatus(1);
		hs.setFlag(0);
		hs.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		Long sId = hisPatientService.insertStatement(hs);
		try{
			as.insertYC(hs, null);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(hs.getId(), HisMqEnum.his_statement);
		}
		for (HisStatementCharge hscs : listC) {
			hscs.setRealAmount(getBackBigDecimal(hscs.getRealAmount()));
			hscs.setStatementId(sId);
			hscs.setAccountType(10);
			hscs.setUnitCode(unit);
			hscs.setCreateTime(date);
			hscs.setModifyTime(date);
			hscs.setOperator(operator);
			hscs.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hscs);
			try{
				as.insertYC(null, hscs);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hscs.getId(), HisMqEnum.his_statement_charge);
			}
		}
		return i > 0;
	}

	/**
	 * 转账
	 * 
	 * @param as
	 * @param unit
	 * @param patId
	 * @param targetPatId
	 * @param operator
	 * @param remark
	 * @param totalAmount
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean transferAccounts(AccountService as, int unit, Long patId, Long targetPatId, Long operator, String remark, BigDecimal totalAmount) throws Exception {
		HisPatient hp = as.selectByPrimaryKey(patId);
		boolean flag = as.transferAccounts(unit, patId, targetPatId, operator, remark, totalAmount);
		if (flag) {
			Date date = new Date();
			BigDecimal payAmount = (hp.getOriginalAmount().multiply(totalAmount)).divide(hp.getAccountAmount(), 2, BigDecimal.ROUND_HALF_UP);
			BigDecimal givenAmount = totalAmount.subtract(payAmount);
			HisStatement hsa = new HisStatement();
			hsa.setPatId(patId);
			hsa.setAccountType(11);
			hsa.setCreateTime(date);
			hsa.setModifyTime(date);
			hsa.setUnitCode(unit);
			hsa.setRemark(remark);
			hsa.setTotalAmount(getBackBigDecimal(totalAmount));
			hsa.setOperator(operator);
			hsa.setPayAmount(getBackBigDecimal(totalAmount));
			hsa.setStatus(1);
			hsa.setFlag(0);
			hsa.setId(sysSeqService.getTableSeq(unit, "his_statement"));
			Long sId = hisPatientService.insertStatement(hsa);
			try{
				as.insertYC(hsa, null);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsa.getId(), HisMqEnum.his_statement);
			}
			HisStatementCharge hsca = new HisStatementCharge();
			hsca.setStatementId(sId);
			hsca.setUnitCode(unit);
			hsca.setPaymentType("yskzr");
			hsca.setRealAmount(getBackBigDecimal(payAmount));
			hsca.setAccountType(11);
			hsca.setOperator(operator);
			hsca.setCreateTime(date);
			hsca.setModifyTime(date);
			hsca.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsca);
			try{
				as.insertYC(null, hsca);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsca.getId(), HisMqEnum.his_statement_charge);
			}
			hsca.setPaymentType("ZSJE");
			hsca.setRealAmount(getBackBigDecimal(givenAmount));
			hsca.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsca);
			try{
				as.insertYC(null, hsca);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsca.getId(), HisMqEnum.his_statement_charge);
			}
			hsa.setPatId(targetPatId);
			hsa.setParentId(sId);
			hsa.setTotalAmount(totalAmount);
			hsa.setPayAmount(totalAmount);
			hsa.setId(sysSeqService.getTableSeq(unit, "his_statement"));
			sId = hisPatientService.insertStatement(hsa);
			try{
				as.insertYC(hsa, null);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsa.getId(), HisMqEnum.his_statement);
			}
			hsca.setStatementId(sId);
			hsca.setPaymentType("yskzr");
			hsca.setRealAmount(payAmount);
			hsca.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsca);
			try{
				as.insertYC(null, hsca);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsca.getId(), HisMqEnum.his_statement_charge);
			}
			hsca.setPaymentType("ZSJE");
			hsca.setRealAmount(givenAmount);
			hsca.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsca);
			try{
				as.insertYC(null, hsca);
			}catch(HessianRuntimeException e){
				mq.mqMsgToJt(hsca.getId(), HisMqEnum.his_statement_charge);
			}
		}
		return flag;
	}

	/**
	 * 取反
	 * 
	 * @param bd
	 * @return
	 */
	private BigDecimal getBackBigDecimal(BigDecimal bd) {
		BigDecimal back = new BigDecimal("-1");
		return bd.multiply(back);
	}

	/**
	 * 处置项结账
	 * 
	 * @param remark
	 * @param map
	 * @param hisStatementChargeList 结算详情list
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean checkoutHisStatementChargeList(AccountService accountService, int unit, String remark, Map<String, String> map, Long statementItemid, Long operatorId, List<HisStatementCharge> hisStatementChargeList, List<HisStatementCharge> listc, String kindId) throws Exception {
		Date date = new Date();
		BigDecimal sh = BigDecimal.ZERO;
		BigDecimal qk = BigDecimal.ZERO;
		BigDecimal jykye = BigDecimal.ZERO;
		BigDecimal jykqk = BigDecimal.ZERO;
		BigDecimal ick = BigDecimal.ZERO;
		// 根据处置单ID查询处置单信息
		HisStatementItem hsi = hisPatientService.getStatementItemOne(statementItemid);
		Long patId = hsi.getPatId(); // 患者id
		Long sId = hsi.getStatementId(); // 结算单id
		Long regId = hsi.getRegId(); // 挂号单id
		String icNo = null;
		String payment = null;
		String zkNo = null;
		List<HisStatementItemDetail> listHdid = null; // 处置单详情列表
		for (HisStatementCharge hisStatementCharge : hisStatementChargeList) {
			if (hisStatementCharge.getPaymentType().equals("yskzr")) {
				jykye = jykye.add(hisStatementCharge.getRealAmount());
			} else if (hisStatementCharge.getPaymentType().equals("QK")) {
				jykqk = jykqk.add(hisStatementCharge.getRealAmount());
			} else if (hisStatementCharge.getPaymentType().equals("ICK")) {
				ick = ick.add(hisStatementCharge.getRealAmount());
				icNo = hisStatementCharge.getSerialNumber();
			} else {
				payment = hisStatementCharge.getPaymentType();
				zkNo = hisStatementCharge.getSerialNumber();
				if (listHdid == null) {
					listHdid = hisPatientService.getHisStatementItemDetailList(statementItemid);
				}
			}
		}
		BigDecimal payAmount = BigDecimal.ZERO;
		if (hisStatementChargeList.size() > 0) {
			payAmount = accountService.getPayAmount(patId, operatorId, unit, jykye, jykqk, icNo, ick, regId, sId, payment, zkNo, listHdid, kindId);
		}

		hisPatientService.deleteHisStatementCharge(sId);
		for (HisStatementCharge hsc : listc) {
			String pType = map.get(hsc.getPaymentType());
			if (pType.equals("ZKYH")) {
			} else if (pType.equals("MLLX")) {
			} else if (pType.equals("SCHD")) {
			} else if (pType.equals("QKLX")) {
				qk = qk.add(hsc.getRealAmount());
			} else {
				sh = sh.add(hsc.getRealAmount());
			}
			hsc.setStatementId(sId);
			hsc.setAccountType(2);
			hsc.setOperator(operatorId);
			hsc.setModifyTime(date);
			hsc.setCreateTime(date);
			hsc.setUnitCode(unit);
			if (hsc.getPaymentType().equals("yskzr")) {
				HisStatementCharge hscss = new HisStatementCharge();
				BeanCopyUtil.copyProperties(hsc, hscss);
				BigDecimal totalAmount = hscss.getRealAmount();
				BigDecimal givenAmount = totalAmount.subtract(payAmount);
				hsc.setRealAmount(givenAmount);
				hscss.setRealAmount(payAmount);
				hscss.setPaymentType("ZSJE");
				hscss.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
				hisPatientService.insertHisStatementCharge(hscss);
			}
			/**
			 * else if IC卡 优惠券预留
			 */
			hsc.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsc);
		}
		HisStatement hs = hisPatientService.getStatementOne(sId);
		regId = hs.getRegId();
		hs.setUnitCode(unit);
		hs.setModifyTime(date);
		hs.setStatus(1);
		hs.setRemark(remark);
		hs.setDebtAmount(qk);
		hs.setPayAmount(sh.add(qk));
		hs.setOperator(operatorId);
		hisPatientService.updateStatement(hs);
		int i = hisServiceItemService.updateReg(regId, 4, date, statementItemid);
		i = i / i;
		i = updateRegOStOSti(regId, sId, statementItemid, accountService);
		i = i/i;
		// 根据卡号kindId，查询如果是有卡无卡号的卡，就将此卡置为isdelete
		// accountService.updateDelDisckind(kindId);
		return i > 0;
	}

	private int updateRegOStOSti(Long regId, Long sId, Long siId, AccountService accountService) {
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(sId);
		HisStatementItemDetail hsci = new HisStatementItemDetail();
		hsci.setStatementItemid(siId);
		List<HisStatementCharge> scList = hisStatementChargeDao.selectList(hsc);
		List<HisStatementItemDetail> sidList = hisStatementItemDetailDao.selectList(hsci);
		HisRegister reg = hisRegisterDao.selectByPrimaryKey(regId);
		HisStatement s = hisStatementDao.selectByPrimaryKey(sId);
		HisStatementItem si = hisStatementItemDao.selectByPrimaryKey(siId);
		int i = 1;
		try{
			accountService.updateRegOStOSti(reg, s, si, scList, sidList);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(reg.getId(), HisMqEnum.his_register);
			mq.mqMsgToJt(s.getId(), HisMqEnum.his_statement);
			mq.mqMsgToJt(si.getId(), HisMqEnum.his_statement_item);
			for(HisStatementCharge sc:scList){
				mq.mqMsgToJt(sc.getId(), HisMqEnum.his_statement_charge);
			}
			for(HisStatementItemDetail sid:sidList){
				mq.mqMsgToJt(sid.getId(), HisMqEnum.his_statement_item_detail);
			}
			mq.mqMsgToJt(reg.getPatId(), s.getPayAmount(), 1);
		}
		return i;
	}

	/**
	 * 处置单调整
	 * 
	 * @param regId
	 * @param status
	 * @param statementItemId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean disposalOrderAdjustment(AccountService as, int unit, Long regId, Integer status, Long statementItemId, Long operatorId, String remark, Map<String, String> map) throws Exception {
		Date date = new Date();
		HisRegister hisRegister = hisRegisterDao.selectByPrimaryKey(regId);
		// 找到处置单
		HisStatementItem hisStatementItem = hisServiceItemService.getHisStatementItem(statementItemId);
		// 根据处置单Id获取处置单明细
		HisStatementItemDetail hisStatementItemDetail = new HisStatementItemDetail();
		hisStatementItemDetail.setStatementItemid(statementItemId);
		List<HisStatementItemDetail> hsidList = hisServiceItemService.getHisStatementItemDetailList(hisStatementItemDetail);
		// 新建处置单
		HisStatementItem newHisStatementItem = new HisStatementItem();
		BeanCopyUtil.copyProperties(hisStatementItem, newHisStatementItem);
		newHisStatementItem.setCreateTime(date);
		newHisStatementItem.setModifyTime(date);
		newHisStatementItem.setAccountType(12);
		newHisStatementItem.setStatementId(Refund2(as, unit, statementItemId, operatorId, 12, map,hisRegister.getStatus()));
		newHisStatementItem.setId(sysSeqService.getTableSeq(unit, "his_statement_item"));
		newHisStatementItem.setRemark(remark);
		// 给新建处置单增加parent_id
		newHisStatementItem.setParentId(statementItemId);
		// 保存
		hisServiceItemService.saveHisStatementItem(newHisStatementItem);
		// 更新对应挂号单里的statement_item_id，更改挂号单状态
		hisRegister.setStatementItemid(newHisStatementItem.getId());
		hisRegister.setStatus(2);
		hisRegister.setModifyTime(date);
		hisRegisterDao.updateByPrimaryKey(hisRegister);
		// 更新对应结算单里的statement_item_id
		HisStatement hisStatement = hisStatementDao.selectByPrimaryKey(newHisStatementItem.getStatementId());
		hisStatement.setStatementItemid(newHisStatementItem.getId());
		hisStatementDao.updateByPrimaryKey(hisStatement);
		// 给新增处置项添加处置项明细
		List<HisStatementItemDetail> list = new ArrayList<HisStatementItemDetail>();
		for (HisStatementItemDetail hsids : hsidList) {
			hsids.setAccountType(12);
			hsids.setCreateTime(date);
			hsids.setModifyTime(date);
			hsids.setStatementItemid(newHisStatementItem.getId());
			hsids.setId(sysSeqService.getTableSeq(unit, "his_statement_item_detail"));
			hisServiceItemService.saveHisStatementItemDetail(hsids);
			list.add(hsids);
		}
		for (HisStatementItemDetail hsids : hsidList) {
			hsids.setStatementItemid(regId);
			hsids.setIfFormal(0);
			hsids.setId(sysSeqService.getTableSeq(unit, "his_statement_item_detail"));
			hisServiceItemService.saveHisStatementItemDetail(hsids);
		}
		try{
			as.adjustmentDisposalOrder1(newHisStatementItem, hisRegister, hisStatement, list);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(newHisStatementItem.getId(), HisMqEnum.his_statement_item);
			mq.mqMsgToJt(hisRegister.getId(), HisMqEnum.his_register);
			mq.mqMsgToJt(hisStatement.getId(), HisMqEnum.his_statement);
			for(HisStatementItemDetail sid:list){
				mq.mqMsgToJt(sid.getId(), HisMqEnum.his_statement_item_detail);
			}
		}
		return true;
	}

	@Transactional
	public Long Refund2(AccountService as, int unit, Long statementItemid, Long operatorId, int strats, Map<String, String> map,Integer status) throws Exception {
		Date date = new Date();
		HisStatementItem hisStatementItem = hisPatientService.getStatementItemOne(statementItemid);
		Long statementId = hisStatementItem.getStatementId();
		HisStatement hs = hisPatientService.getStatementOne(hisStatementItem.getStatementId());
		List<HisStatementCharge> list = hisPatientService.getChargeList(statementItemid);
		BigDecimal yjkqk = BigDecimal.ZERO;
		BigDecimal yjkbj = BigDecimal.ZERO;
		BigDecimal yjkzj = BigDecimal.ZERO;
		BigDecimal ick = BigDecimal.ZERO;
		String icKNo = null;
		boolean flag = false;
		for (HisStatementCharge hsc : list) {
			String pType = map.get(hsc.getPaymentType());
			HisStatementCharge hscs = new HisStatementCharge();
			BeanCopyUtil.copyProperties(hsc, hscs);
			if (hscs.getPaymentType().equals("yskzr")) {
				yjkbj = yjkbj.add(hscs.getRealAmount());
				flag = true;
			}
			if (hscs.getPaymentType().equals("ZSJE")) {
				yjkzj = yjkzj.add(hscs.getRealAmount());
				flag = true;
			}
			if (hscs.getPaymentType().equals("QK")) {
				BigDecimal hz = as.getDisAmount(statementId);
				yjkqk = yjkqk.add(hscs.getRealAmount());
				yjkqk = yjkqk.subtract(hz);
				flag = true;
			}
			if (hscs.getPaymentType().equals("ICK")) {
				ick = hscs.getRealAmount();
				icKNo = hscs.getSerialNumber();
				flag = true;
			}
			if (pType.equals("SCHD")) {
				if (hscs.getPaymentType().equals("DZYHJ")) {
				} else if (hscs.getPaymentType().equals("QTFXJ")) {
				} else {
					flag = true;
				}
			}
		}
		if (flag) {
			as.Refund(hisStatementItem.getPatId(), yjkqk, yjkbj, yjkzj, ick, icKNo, statementId);
		}

		HisStatement hss = new HisStatement();
		BeanCopyUtil.copyProperties(hs, hss);
		hss.setFlag(1);
		hss.setOperator(operatorId);
		hss.setModifyTime(date);
		hisPatientService.updateStatement(hss);
		hs.setParentId(hs.getId());
		hs.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		hs.setFlag(0);
		hs.setOperator(operatorId);
		hs.setModifyTime(date);
		hs.setCreateTime(date);
		hs.setAccountType(strats);
		hs.setTotalAmount(getBackBigDecimal(hs.getTotalAmount()));
		hs.setPayAmount(getBackBigDecimal(hs.getPayAmount()));
		hs.setDebtAmount(getBackBigDecimal(hs.getDebtAmount()));
		hs.setPayoff(getBackBigDecimal(hs.getPayoff()));
		Long newId = hisPatientService.insertStatement(hs);
		List<HisStatementCharge> listc = new ArrayList<HisStatementCharge>();
		for (HisStatementCharge hsc : list) {
			hsc.setCreateTime(date);
			hsc.setModifyTime(date);
			hsc.setStatementId(newId);
			hsc.setRealAmount(getBackBigDecimal(hsc.getRealAmount()));
			hsc.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsc);
			listc.add(hsc);
		}
		try{
			as.adjustmentDisposalOrder2(hss, hs, listc,status);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(hss.getId(), HisMqEnum.his_statement);
			mq.mqMsgToJt(hs.getId(), HisMqEnum.his_statement);
			for(HisStatementCharge sc:listc){
				mq.mqMsgToJt(sc.getId(), HisMqEnum.his_statement_charge);
			}
			if(status==4){
				mq.mqMsgToJt(hss.getPatId(), hss.getPayAmount(), 2);
			}
		}
		return newId;
	}

	/**
	 * 换欠款
	 * 
	 * @param date
	 * @param unit
	 * @param patId
	 * @param listC
	 */
	@Transactional
	public void Arrears(String remack, Long operatorId, Date date, int unit, Long patId, List<HisStatementCharge> listC, AccountService as) throws Exception {
		BigDecimal ze = BigDecimal.ZERO;
		for (HisStatementCharge hsc : listC) {
			ze = ze.add(hsc.getRealAmount());
		}
		HisStatement hs = new HisStatement();
		hs.setPatId(patId);
		hs.setRemark(remack);
		hs.setAccountType(3);
		hs.setCreateTime(date);
		hs.setModifyTime(date);
		hs.setUnitCode(unit);
		hs.setTotalAmount(ze);
		hs.setOperator(operatorId);
		hs.setPayAmount(ze);
		hs.setStatus(1);
		hs.setFlag(0);
		hs.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		Long sId = hisServiceItemService.saveHisStatement(hs);
		List<HisStatementCharge> list = new ArrayList<>();
		for (HisStatementCharge hsc : listC) {
			hsc.setStatementId(sId);
			hsc.setUnitCode(unit);
			hsc.setAccountType(3);
			hsc.setOperator(operatorId);
			hsc.setCreateTime(date);
			hsc.setModifyTime(date);
			hsc.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisServiceItemService.saveHisStatementCharge(hsc);
			list.add(hsc);
		}
		as.Arrears(patId, ze, hs, list);
	}

	/**
	 * 收费单调整（结算单调整）
	 * 
	 * @param as
	 * @param statementItemId
	 * @param operatorId
	 * @throws Exception
	 */
	@Transactional
	public Long Refund(AccountService as, int unit, Long statementItemId, Long operatorId, int accountType, String remark, Map<String, String> map) throws Exception {
		Date date = new Date();
		HisStatementItem hisStatementItem = hisPatientService.getStatementItemOne(statementItemId);
		HisStatement hisStatement = hisPatientService.getStatementOne(hisStatementItem.getStatementId());
		List<HisStatementCharge> list = hisPatientService.getChargeList(statementItemId);
		BigDecimal yjkqk = BigDecimal.ZERO;
		BigDecimal yjkbj = BigDecimal.ZERO;
		BigDecimal yjkzj = BigDecimal.ZERO;
		BigDecimal ick = BigDecimal.ZERO;
		String icKNo = null;
		boolean flag = false;
		for (HisStatementCharge hisStatementCharge : list) {
			String pType = map.get(hisStatementCharge.getPaymentType());
			HisStatementCharge newHisStatementCharge = new HisStatementCharge();
			BeanCopyUtil.copyProperties(hisStatementCharge, newHisStatementCharge);
			if (newHisStatementCharge.getPaymentType().equals("yskzr")) {
				yjkbj = yjkbj.add(newHisStatementCharge.getRealAmount());
				flag = true;
			}
			if (newHisStatementCharge.getPaymentType().equals("ZSJE")) {
				yjkzj = yjkzj.add(newHisStatementCharge.getRealAmount());
				flag = true;
			}
			if (newHisStatementCharge.getPaymentType().equals("QK")) {
				BigDecimal hz = as.getDisAmount(hisStatement.getId());
				yjkqk = yjkqk.add(newHisStatementCharge.getRealAmount());
				yjkqk = yjkqk.subtract(hz);
				flag = true;
			}
			if (newHisStatementCharge.getPaymentType().equals("ICK")) {
				ick = newHisStatementCharge.getRealAmount();
				icKNo = newHisStatementCharge.getSerialNumber();
				flag = true;
			}
			if (pType.equals("SCHD")) {
				if (newHisStatementCharge.getPaymentType().equals("DZYHJ")) {
				} else if (newHisStatementCharge.getPaymentType().equals("QTFXJ")) {
				} else {
					flag = true;
				}
			}
		}
		Long patId = hisStatementItem.getPatId();
		BigDecimal pay = BigDecimal.ZERO;
		if (flag) {
			as.Refund(hisStatementItem.getPatId(), yjkqk, yjkbj, yjkzj, ick, icKNo, hisStatementItem.getStatementId());
		}

		// 设置为原结算单为退单状态。
		pay = hisStatement.getPayAmount();
		HisStatement hss = hisStatement;
		HisStatement hsstjt = new HisStatement();
		hss.setFlag(1);
		hss.setOperator(operatorId);
		hss.setModifyTime(date);
		hisPatientService.updateStatement(hss);
		BeanCopyUtil.copyProperties(hss, hsstjt);

		// 新建结算单1，这个
		HisStatement newHisStatement1 = hisStatement;
		HisStatement newHisStatement1tjt = new HisStatement();

		newHisStatement1.setParentId(hisStatement.getId());
		newHisStatement1.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		newHisStatement1.setFlag(0);
		newHisStatement1.setOperator(operatorId);
		newHisStatement1.setModifyTime(date);
		newHisStatement1.setCreateTime(date);
		newHisStatement1.setAccountType(accountType); // 结账单调整
		newHisStatement1.setTotalAmount(getBackBigDecimal(hisStatement.getTotalAmount()));
		newHisStatement1.setPayAmount(getBackBigDecimal(hisStatement.getPayAmount()));
		newHisStatement1.setDebtAmount(getBackBigDecimal(hisStatement.getDebtAmount()));
		newHisStatement1.setPayoff(getBackBigDecimal(hisStatement.getPayoff()));
		newHisStatement1.setRemark(remark);
		BeanCopyUtil.copyProperties(newHisStatement1, newHisStatement1tjt);
		Long newId = hisPatientService.insertStatement(newHisStatement1);
		// 新建结算单2，这个是后边要用的
		HisStatement newHisStatement2 = newHisStatement1;
		HisStatement newHisStatement2tjt = new HisStatement();
		newHisStatement2.setParentId(newId);
		newHisStatement2.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		newHisStatement2.setTotalAmount(getBackBigDecimal(newHisStatement1.getTotalAmount()));
		newHisStatement2.setPayAmount(getBackBigDecimal(newHisStatement1.getPayAmount()));
		newHisStatement2.setDebtAmount(getBackBigDecimal(newHisStatement1.getDebtAmount()));
		newHisStatement2.setPayoff(getBackBigDecimal(newHisStatement1.getPayoff()));
		newHisStatement2.setRemark(remark);
		newHisStatement2.setStatus(0);
		newHisStatement2.setAccountType(2); // 代收费状态
		BeanCopyUtil.copyProperties(newHisStatement2, newHisStatement2tjt);
		Long newId2 = hisPatientService.insertStatement(newHisStatement2);
		hisStatementItem.setStatementId(newId2);
		HisStatementItem hisStatementItemtjt = new HisStatementItem();
		BeanCopyUtil.copyProperties(hisStatementItem, hisStatementItemtjt);
		hisPatientService.updateStatementItem(hisStatementItem);
		List<HisStatementCharge> listtjt = new ArrayList<HisStatementCharge>();
		for (HisStatementCharge hsc : list) {
			hsc.setCreateTime(date);
			hsc.setModifyTime(date);
			hsc.setStatementId(newId);
			hsc.setRealAmount(getBackBigDecimal(hsc.getRealAmount()));
			hsc.setId(sysSeqService.getTableSeq(unit, "his_statement_charge"));
			hisPatientService.insertHisStatementCharge(hsc);
			listtjt.add(hsc);
		}
		// 更新对应挂号单里的statement_item_id，更改挂号单状态
		HisRegister hisRegister = hisRegisterDao.selectByPrimaryKey(newHisStatement2.getRegId());
		hisRegister.setStatus(3);
		hisRegister.setModifyTime(date);
		hisRegisterDao.updateByPrimaryKey(hisRegister);
		try{
			as.adjustmentSettlementSheet(hsstjt, newHisStatement1tjt, newHisStatement2tjt, hisStatementItemtjt, listtjt, hisRegister);
		}catch(HessianRuntimeException e){
			mq.mqMsgToJt(hsstjt.getId(), HisMqEnum.his_statement);
			mq.mqMsgToJt(newHisStatement1tjt.getId(), HisMqEnum.his_statement);
			mq.mqMsgToJt(newHisStatement2tjt.getId(), HisMqEnum.his_statement);
			mq.mqMsgToJt(hisStatementItemtjt.getId(), HisMqEnum.his_statement_item);
			for(HisStatementCharge sc:listtjt){
				mq.mqMsgToJt(sc.getId(), HisMqEnum.his_statement_charge);
			}
			mq.mqMsgToJt(hisRegister.getId(), HisMqEnum.his_register);
			mq.mqMsgToJt(patId, pay, 2);
		}
		return newId;
	}

	/**
	 * 更新云端数据(定时任务)
	 * 
	 * @return
	 */
	public boolean updateCloudStatement() {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		List<Long> idList = as.getCloudStatementId(unit);
		List<HisStatement> hsList = new ArrayList<HisStatement>();
		List<HisStatementCharge> hscList = new ArrayList<HisStatementCharge>();
		List<HisStatementItem> hsiList = new ArrayList<HisStatementItem>();
		List<HisStatementItem> hsiListu = new ArrayList<HisStatementItem>();
		List<HisStatementItemDetail> hsidList = new ArrayList<HisStatementItemDetail>();
		List<HisRegister> hrList = new ArrayList<HisRegister>();
		List<HisRegister> hrListu = new ArrayList<HisRegister>();
		HisStatement hss = new HisStatement();
		if (idList.get(0) != null) {
			hss.setSqlStr(" id > " + idList.get(0));
		}
		hss.setSqlSort(" id asc");
		hsList = hisStatementDao.selectList(hss);
		HisStatementCharge hscs = new HisStatementCharge();
		if (idList.get(1) != null) {
			hscs.setSqlStr(" id > " + idList.get(1));
		}
		hscs.setSqlSort(" id asc");
		hscList = hisStatementChargeDao.selectList(hscs);
		HisStatementItem hsis = new HisStatementItem();
		if (idList.get(2) != null) {
			hsis.setSqlStr(" id > " + idList.get(2));
		}
		hsis.setSqlSort(" id asc");
		hsiList = hisStatementItemDao.selectList(hsis);
		HisStatementItemDetail hsids = new HisStatementItemDetail();
		if (idList.get(3) != null) {
			hsids.setSqlStr(" id > " + idList.get(3));
		}
		hsids.setSqlSort(" id asc");
		hsidList = hisStatementItemDetailDao.selectList(hsids);
		HisRegister hrs = new HisRegister();
		if (idList.get(4) != null) {
			hrs.setSqlStr(" id > " + idList.get(4));
		}
		hrs.setSqlSort(" id asc");
		hrList = hisRegisterDao.selectList(hrs);
		for (HisStatement hs : hsList) {
			if (idList.get(2) == null) {
				break;
			}
			if (hs.getStatementItemid() <= idList.get(2)) {
				HisStatementItem hsi = new HisStatementItem();
				hsi.setId(hs.getStatementItemid());
				hsi.setStatementId(hs.getId());
				hsiListu.add(hsi);
			}
		}
		for (HisStatementItem hsi : hsiList) {
			if (idList.get(4) == null) {
				break;
			}
			if (hsi.getRegId() <= idList.get(4)) {
				HisRegister hr = new HisRegister();
				hr.setId(hsi.getRegId());
				hr.setStatementItemid(hsi.getId());
			}
		}
		return as.saveCloudStatement(hsList, hscList, hsiList, hsidList, hrList, hsiListu, hrListu);
	}

	public boolean accountingRefund(int unit, String remark, Long operatorId, Long statementItemid, BigDecimal money) {
		int i = 0;
		HisStatement hsis = new HisStatement();
		hsis.setStatementItemid(statementItemid);
		hsis.setAccountType(13);
		List<HisStatement> list = hisStatementDao.selectList(hsis);
		if (list != null && list.size() > 0) {
			return false;
		}
		HisStatementItem hsi = hisStatementItemDao.selectByPrimaryKey(statementItemid);
		Date date = new Date();
		Long sId = hsi.getStatementId();
		HisStatement hs = hisStatementDao.selectByPrimaryKey(sId);
		hs.setId(sysSeqService.getTableSeq(unit, "his_statement"));
		hs.setAccountType(13);
		hs.setParentId(sId);
		hs.setCreateTime(date);
		hs.setModifyTime(date);
		hs.setTotalAmount(getBackBigDecimal(money));
		hs.setPayAmount(getBackBigDecimal(money));
		hs.setRemark(remark);
		hs.setOperator(operatorId);
		hs.setStatus(0);
		hs.setFlag(0);
		hs.setActId(null);
		hs.setPrintNum(0);
		hs.setPayoff(BigDecimal.ZERO);
		hs.setDebtAmount(BigDecimal.ZERO);
		hs.setDocId(null);
		i = hisStatementDao.insertSelective(hs);
		i = i/i;
		mq.mqMsgToJt(hs.getId(), HisMqEnum.his_statement);
		return i > 0;
	}
}
