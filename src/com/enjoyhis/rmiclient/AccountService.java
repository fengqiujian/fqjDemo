package com.enjoyhis.rmiclient;

import java.math.BigDecimal;
import java.util.List;

import com.enjoyhis.persistence.his.po.ArrearsInformation;
import com.enjoyhis.persistence.his.po.ChangeArrears;
import com.enjoyhis.persistence.his.po.HisDisckind;
import com.enjoyhis.persistence.his.po.HisGroupEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.persistence.his.po.HisStatementItem;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.pojo.HisStatementPojo;
import com.enjoyhis.pojo.LoadICCard;
import com.enjoyhis.pojo.PatientPj;
import com.enjoyhis.pojo.StatementItemDetail;

public interface AccountService {

	void sayHello(String name);

	PatientPj getPatentPj(Long id);

	HisPatient selectByPrimaryKey(Long id);
	
	HisStatement selectByPrimaryKey4hs(Long id);

	int updateByPrimaryKeySelective(HisPatient hp);

	boolean transferAccounts(int unit, Long patId, Long targetPatId, Long operator, String remark, BigDecimal totalAmount);

	/**
	 * 预充值消费
	 * 
	 * @param hsc
	 */
	void preChargeOfConsumption(Long patId, BigDecimal totalAmount, BigDecimal payAmount, BigDecimal givenAmount);

	/**
	 * 获取消费本金 同时修改云端账户
	 * 
	 * @param id
	 * @param totalAmount
	 * @return
	 */
	BigDecimal getPayAmount(Long id, Long operator, Integer unitCode, BigDecimal yjk, BigDecimal yjkqk, String ickNo, BigDecimal ick, Long regId, Long sId, String payment, String zkk, List<HisStatementItemDetail> list, String kindId);

	/**
	 * 欠款
	 * 
	 * @param id
	 * @param totalAmount
	 * @return
	 */
	boolean Arrears(Long id, BigDecimal totalAmount, HisStatement hs, List<HisStatementCharge> list);

	/**
	 * 退款
	 * 
	 * @param id
	 * @param yjkqk
	 * @param yjkbj
	 * @param yjkzj
	 * @param ick
	 * @param icKNo
	 * @param sId
	 * @return
	 */
	boolean Refund(Long id, BigDecimal yjkqk, BigDecimal yjkbj, BigDecimal yjkzj, BigDecimal ick, String icKNo, Long sId);

	/**
	 * 获取云端结算单 处置单 及 明细 最大ID
	 * 
	 * @return
	 */
	List<Long> getCloudStatementId(int unit);

	/**
	 * 保存到云端
	 * 
	 * @param hsList
	 * @param hscList
	 * @param hsiList
	 * @param hsidList
	 * @return
	 */
	boolean saveCloudStatement(List<HisStatement> hsList, List<HisStatementCharge> hscList, List<HisStatementItem> hsiList, List<HisStatementItemDetail> hsidList, List<HisRegister> hrList, List<HisStatementItem> hsiListu, List<HisRegister> hrListu);

	boolean saveDemo1();

	boolean saveDemo2();

	boolean saveDemo3();

	/**
	 * IC卡绑定用户
	 * 
	 * @param disccardno
	 * @param patId
	 * @return
	 */
	LoadICCard loadIcCard(String disccardno, Long patId);

	/**
	 * 折扣卡绑定用户
	 * 
	 * @param disccardno
	 * @param patId
	 * @return
	 */
	int bindingDiscount(String disccardno, Long patId);

	/**
	 * 折扣卡绑定用户2
	 * 
	 * @param disccardno
	 * @return
	 */
	String bindingDiscount2(String disccardno);

	/**
	 * 获取活动列表
	 * 
	 * @return
	 */
	List<HisDisckind> activity(Long patId, Integer unitCode);

	/**
	 * 获取减款金额
	 * 
	 * @param paymentCode
	 * @param disccardno
	 * @param list
	 * @return
	 */
	BigDecimal getDiscountAmount(String paymentCode, String disccardno, List<HisStatementItemDetail> list);

	/**
	 * 将卡号置为已删除
	 * 
	 * @param kindId
	 */
	void updateDelDisckind(String kindId);

	/**
	 * 上行同步结算完成的挂号单、处置单、处置单明细、结算单、结算单明细
	 * 
	 * @param regId
	 * @param sId
	 * @param siId
	 * @param scList
	 * @param sidList
	 * @return
	 */
	int updateRegOStOSti(HisRegister regId, HisStatement sId, HisStatementItem siId, List<HisStatementCharge> scList, List<HisStatementItemDetail> sidList);

	/**
	 * 上行同步调整结算单
	 * 
	 * @param hsstjt
	 * @param newHisStatement1tjt
	 * @param newHisStatement2tjt
	 * @param hisStatementItemtjt
	 * @param listtjt
	 * @param hisRegister
	 * @return
	 */
	int adjustmentSettlementSheet(HisStatement hsstjt, HisStatement newHisStatement1tjt, HisStatement newHisStatement2tjt, HisStatementItem hisStatementItemtjt, List<HisStatementCharge> listtjt, HisRegister hisRegister);

	/**
	 * 上行同步调整处置单（处理处置单部分）
	 * 
	 * @param newHisStatementItem
	 * @param hisRegister
	 * @param hisStatement
	 * @param list
	 * @return
	 */
	int adjustmentDisposalOrder1(HisStatementItem newHisStatementItem, HisRegister hisRegister, HisStatement hisStatement, List<HisStatementItemDetail> list);

	/**
	 * 上行同步调整处置单（处理结算单部分）
	 * 
	 * @param hss
	 * @param hs
	 * @param listc
	 * @return
	 */
	int adjustmentDisposalOrder2(HisStatement hss, HisStatement hs, List<HisStatementCharge> listc,Integer status);

	BigDecimal getDisAmount(Long statementId);

	int insertYC(HisStatement hs, HisStatementCharge hsc);

	List<HisStatementPojo> getHisStatementList(Long id);

	List<StatementItemDetail> getHisStatementItemListjt(Long id);

	List<HisStatementCharge> getChargeListjt(Long id);

	HisStatement getPreDepositTransferjt(Long id);
	

	/**
	 * 获取还欠款列表
	 * 
	 * @param patId
	 * @return
	 */
	List<ChangeArrears> getChangeArrearsList(Long patId);

	ArrearsInformation arrearsInformation(Long statementId);

	boolean upGroupEmployee(Long groupId, List<HisGroupEmployee> list, Integer isdel);

	boolean upGroupEmployee2(List<Long> list);
	
	boolean exception4hisRegister(HisRegister msg);

	boolean exception4hisStatement(HisStatement msg);

	boolean exception4hisStatementCharge(HisStatementCharge msg);

	boolean exception4hisStatementItem(HisStatementItem msg);

	boolean exception4hisPatient(HisPatient msg);
	
	boolean exception4hisPatientCase(HisPatientCase msg);

	boolean exception4hisStatementItemDetail(HisStatementItemDetail msg);
	
	boolean hisPatientUpadte(Long id,BigDecimal payAmount,Integer status);
}
