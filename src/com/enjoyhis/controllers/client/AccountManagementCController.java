package com.enjoyhis.controllers.client;

import java.io.IOException;   
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianConnectionException;
import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.po.HisDisckind;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.pojo.Debts;
import com.enjoyhis.pojo.HisStatementPojo;
import com.enjoyhis.pojo.PatientPj;
import com.enjoyhis.pojo.Statement;
import com.enjoyhis.pojo.StatementItemDetail;
import com.enjoyhis.rmiclient.AccountService;
import com.enjoyhis.service.AccountManagementCService;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.SysUtil;

@Controller
@RequestMapping(value = "client/accountC")
public class AccountManagementCController {

	@Autowired 
	private HisPatientService hisPatientService;
	@Autowired
	private AccountManagementCService accountManagementCService;
	@Autowired 
	private SysConfigService sysConfigService;
	@RequestMapping(value = "getCallList.json")
	public void getCallList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, sysConfigService.getSysConfigList());
	}

	/**
	 * 获取患者信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getPatient.json")
	public void getPatient(HttpServletRequest request, HttpServletResponse response) {
		Long id = getJson(request).getLong("id");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		PatientPj hpps = null;
		try {
			hpps = as.getPatentPj(id);
			if (hpps == null) {
				hpps = hisPatientService.getPatientPj(id);
				hpps.setAccountAmount(BigDecimal.ZERO);
				hpps.setOriginalAmount(BigDecimal.ZERO);
				hpps.setGivenAmount(BigDecimal.ZERO);
				request.setAttribute(Constants.jsonResultCode, -4);
			}
		} catch (HessianConnectionException e) {
			hpps = hisPatientService.getPatientPj(id);
			hpps.setAccountAmount(BigDecimal.ZERO);
			hpps.setOriginalAmount(BigDecimal.ZERO);
			hpps.setGivenAmount(BigDecimal.ZERO);
			request.setAttribute(Constants.jsonResultCode, -1);
		} catch (HessianRuntimeException e) {
			hpps = hisPatientService.getPatientPj(id);
			hpps.setAccountAmount(BigDecimal.ZERO);
			hpps.setOriginalAmount(BigDecimal.ZERO);
			hpps.setGivenAmount(BigDecimal.ZERO);
			request.setAttribute(Constants.jsonResultCode, -1);
		}
		request.setAttribute(Constants.jsonResultData, hpps);
	}

	/**
	 * 获取消费字典
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisPaymentList.json")
	public void getHisPaymentList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, hisPatientService.getHisPaymentList());
	}

	/**
	 * 获取活动字典（预充值用）
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisPrepayactList.json")
	public void getHisPrepayactList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, hisPatientService.getHisPrepayactList());
	}

	/**
	 * 预充值
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "preCharge.json")
	public void preCharge(HttpServletRequest request, HttpServletResponse response, String data) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Long patId = getJson(data).getLong("patId");
		Integer actId = getJson(data).getInteger("actId");
		String sTotalAmount = getJson(data).getString("totalAmount");
		String sPayAmount = getJson(data).getString("payAmount");
		String sGivenAmount = getJson(data).getString("givenAmount");
		BigDecimal totalAmount = new BigDecimal(sTotalAmount);
		BigDecimal payAmount = new BigDecimal(sPayAmount);
		BigDecimal givenAmount = new BigDecimal(sGivenAmount);
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		Long operator = CookieUtil.getCookieId(request, "ID");
		data = getJson(data).getString("content");
		List<HisStatementCharge> listC = toList(data, HisStatementCharge.class);
		try {
			accountManagementCService.preCharge(patId, actId, totalAmount, as, payAmount, givenAmount, unit, operator, listC);
			request.setAttribute(Constants.jsonResultCode, 0);
		} catch (Exception e) {
			// e.printStackTrace();
			request.setAttribute(Constants.jsonResultCode, -1);
		}
	}

	/**
	 * 退预充值
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "preReCharge.json")
	public void preReCharge(HttpServletRequest request, HttpServletResponse response, String data) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Long patId = getJson(data).getLong("patId");
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		Long operator = CookieUtil.getCookieId(request, "ID");
		String sTotalAmount = getJson(data).getString("totalAmount");
		BigDecimal totalAmount = new BigDecimal(sTotalAmount);
		String remark = null;
		try {
			remark = getJson(data).getString("remark");
		} catch (NullPointerException e) {
		}
		data = getJson(data).getString("content");
		List<HisStatementCharge> listC = toList(data, HisStatementCharge.class);
		try {
			accountManagementCService.preReCharge(patId, unit, operator, totalAmount, remark, listC, as);
			request.setAttribute(Constants.jsonResultCode, 0);
		} catch (Exception e) {
			request.setAttribute(Constants.jsonResultCode, -1);
		}
	}

	/**
	 * 计算应退本金
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "subPreReCharge.json")
	public void subPreReCharge(HttpServletRequest request, HttpServletResponse response, String data) {
		String sAccountAmount = getJson(data).getString("AccountAmount");
		String sGivenAmount = getJson(data).getString("GivenAmount");
		String sTKAmount = getJson(data).getString("TKAmount");
		BigDecimal accountAmount = new BigDecimal(sAccountAmount);
		BigDecimal tKAmount = new BigDecimal(sTKAmount);
		BigDecimal GivenAmount = new BigDecimal(sGivenAmount);
		BigDecimal zj = (GivenAmount.multiply(tKAmount)).divide(accountAmount, 2, BigDecimal.ROUND_HALF_DOWN);
		
		request.setAttribute(Constants.jsonResultData, tKAmount.subtract(zj));
	}

	/**
	 * 转账
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "transferAccounts.json")
	public void transferAccounts(HttpServletRequest request, HttpServletResponse response, String data) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		Long patId = getJson(data).getLong("patId");
		Long targetPatId = getJson(data).getLong("targetPatId");
		if (patId - targetPatId == 0L) {
			request.setAttribute(Constants.jsonResultCode, -9);
			return;
		}
		Long operator = CookieUtil.getCookieId(request, "ID");
		String remark = null;
		try {
			remark = getJson(data).getString("remark");
		} catch (NullPointerException e) {
		}
		String sTotalAmount = getJson(data).getString("totalAmount");
		BigDecimal totalAmount = new BigDecimal(sTotalAmount);
		try {
			boolean flag = accountManagementCService.transferAccounts(as, unit, patId, targetPatId, operator, remark, totalAmount);
			if (flag) {
				request.setAttribute(Constants.jsonResultCode, 0);
			} else {
				request.setAttribute(Constants.jsonResultCode, -5);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Constants.jsonResultCode, -1);
		}

	}

	/**
	 * 获取消费明细列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisStatementChargeList.json")
	public void getHisStatementChargeList(HttpServletRequest request, HttpServletResponse response) {
		Long statementItemid = getJson(request).getLong("statementItemid");
		List<HisStatementCharge> lists = hisPatientService.getChargeList(statementItemid);
		List<HisStatementCharge> list = new ArrayList<>();
		HisStatementCharge hsc;
		for (HisStatementCharge hscs : lists) {
			hsc = new HisStatementCharge();
			hsc.setPaymentType(hscs.getPaymentType());
			hsc.setPaymentSubtype(hscs.getPaymentSubtype());
			hsc.setRealAmount(hscs.getRealAmount());
			hsc.setSerialNumber(hscs.getSerialNumber());
			hsc.setCreateTime(hscs.getCreateTime());
			hsc.setModifyTime(hscs.getModifyTime());
			list.add(hsc);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("remark", hisPatientService.getRemark(statementItemid));
		request.setAttribute(Constants.jsonResultData, map);
	}
	/**
	 * 获取预存转账-集团
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getPreDepositTransferjt.json")
	public void getPreDepositTransferjt(HttpServletRequest request, HttpServletResponse response) {
		Long statementId = getJson(request).getLong("statementId");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		HisStatement lists = as.getPreDepositTransferjt(statementId);
		Statement s = hisPatientService.getPreDepositTransferjt(lists);
		request.setAttribute(Constants.jsonResultData, s);
	}
	/**
	 * 获取消费明细列表-集团
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisStatementChargeListjt.json")
	public void getHisStatementChargeListjt(HttpServletRequest request, HttpServletResponse response) {
		Long statementId = getJson(request).getLong("statementId");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		List<HisStatementCharge> lists = as.getChargeListjt(statementId);
		List<HisStatementCharge> list = new ArrayList<>();
		HisStatementCharge hsc;
		for (HisStatementCharge hscs : lists) {
			hsc = new HisStatementCharge();
			hsc.setPaymentType(hscs.getPaymentType());
			hsc.setPaymentSubtype(hscs.getPaymentSubtype());
			hsc.setRealAmount(hscs.getRealAmount());
			hsc.setSerialNumber(hscs.getSerialNumber());
			hsc.setCreateTime(hscs.getCreateTime());
			hsc.setModifyTime(hscs.getModifyTime());
			list.add(hsc);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("remark", hisPatientService.getRemark(statementId));
		request.setAttribute(Constants.jsonResultData, map);
	}
	/**
	 * 获取预充值活动名称
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getActName.json")
	public void getActName(HttpServletRequest request, HttpServletResponse response) {
		Long statementId = getJson(request).getLong("statementId");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		String actName = hisPatientService.getActName(statementId,as);
		request.setAttribute(Constants.jsonResultData, actName);
	}

	/**
	 * 获取处置项明细
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisStatementItemList.json")
	public void getHisStatementItemList(HttpServletRequest request, HttpServletResponse response) {
		Long statementItemid = getJson(request).getLong("statementItemid");
		String token = UUID.randomUUID().toString();
		request.getSession().setAttribute("sessionID", token);
		
		// System.out.println(statementItemid);
		List<StatementItemDetail> lists = hisPatientService.getHisStatementItemList(statementItemid);
		List<StatementItemDetail> list = new ArrayList<StatementItemDetail>();
		StatementItemDetail hsip;
		String jsonString = null;
		for (StatementItemDetail hsips : lists) {
			hsip = new StatementItemDetail();
			hsip.setDocName(hsips.getDocName());
			hsip.setId(hsips.getId());
			hsip.setTooth(hsips.getTooth());
			hsip.setItemName(hsips.getItemName());
			hsip.setItemSubId(hsips.getItemSubId());
			hsip.setItemNameXm(hsips.getItemNameXm());
			hsip.setItemName(hsips.getItemName());
			hsip.setPrice(hsips.getPrice());
			hsip.setUnit(hsips.getUnit());
			hsip.setDocId(hsips.getDocId());
			hsip.setQty(hsips.getQty());
			hsip.setItemAmount(hsips.getItemAmount());
			hsip.setStatementItemid(hsips.getStatementItemid());
			hsip.setCreateTime(hsips.getCreateTime());
			hsip.setModifyTime(hsips.getModifyTime());
			list.add(hsip);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", list);
			jsonObject.put("token", token);
			jsonString = JSONUtils.toJSONString(jsonObject);
			
		}
		request.setAttribute(Constants.jsonResultData, jsonString);
	}
	/**
	 * 获取处置项明细-集团
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisStatementItemListjt.json")
	public void getHisStatementItemListjt(HttpServletRequest request, HttpServletResponse response) {
		Long statementItemid = getJson(request).getLong("statementItemid");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		String token = UUID.randomUUID().toString();
		request.getSession().setAttribute("sessionID", token);
		
		// System.out.println(statementItemid);
		List<StatementItemDetail> lists = as.getHisStatementItemListjt(statementItemid);
		List<StatementItemDetail> list = new ArrayList<StatementItemDetail>();
		StatementItemDetail hsip;
		String jsonString = null;
		for (StatementItemDetail hsips : lists) {
			hsip = new StatementItemDetail();
			hsip.setDocName(hsips.getDocName());
			hsip.setId(hsips.getId());
			hsip.setTooth(hsips.getTooth());
			hsip.setItemName(hsips.getItemName());
			hsip.setItemSubId(hsips.getItemSubId());
			hsip.setItemNameXm(hsips.getItemNameXm());
			hsip.setPrice(hsips.getPrice());
			hsip.setUnit(hsips.getUnit());
			hsip.setDocId(hsips.getDocId());
			hsip.setQty(hsips.getQty());
			hsip.setItemAmount(hsips.getItemAmount());
			hsip.setStatementItemid(hsips.getStatementItemid());
			hsip.setCreateTime(hsips.getCreateTime());
			hsip.setModifyTime(hsips.getModifyTime());
			list.add(hsip);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", list);
			jsonObject.put("token", token);
			jsonString = JSONUtils.toJSONString(jsonObject);
			
		}
		request.setAttribute(Constants.jsonResultData, jsonString);
	}
	/**
	 * 绑定折扣卡
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "bindingDiscount.json")
	public void bindingDiscount(HttpServletRequest request, HttpServletResponse response, String data) {
		Long patId = getJson(data).getLong("patId");
		String disccardno = getJson(data).getString("disccardno");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		int i = 0;
		try{
			i = as.bindingDiscount(disccardno, patId);
		}catch(Exception e){
			request.setAttribute(Constants.jsonResultCode, -20);
			return;
		}
		request.setAttribute(Constants.jsonResultData, i);
	}
	/**
	 * 绑定折扣卡
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "bindingDiscount2.json")
	public void bindingDiscount2(HttpServletRequest request, HttpServletResponse response, String data) {
		String disccardno = getJson(data).getString("disccardno");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		String str = "";
		try{
			str = as.bindingDiscount2(disccardno);
		}catch(Exception e){
			request.setAttribute(Constants.jsonResultCode, -20);
			return;
		}
		request.setAttribute(Constants.jsonResultData, str);
	}

	/**
	 * 获取活动(与患者绑定)
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "activity.json")
	public void activity(HttpServletRequest request, HttpServletResponse response, String data) {
		/**
		 * 说明 data参数留着备用区分以后用到的患者对应活动 暂时先获取所有活动
		 */
		Long patId = getJson(data).getLong("patId");
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		List<HisDisckind> list = new ArrayList<>();
		try{
			list =as.activity(patId, unit);
		}catch(Exception e){
			request.setAttribute(Constants.jsonResultCode, -20);
			return;
		}
		request.setAttribute(Constants.jsonResultData, list);
	}

	/**
	 * 获取减款金额
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "getDiscountAmount.json")
	public void getDiscountAmount(HttpServletRequest request, HttpServletResponse response, String data) {
		String paymentID = getJson(data).getString("paymentID");
		Long statementItemid = getJson(data).getLong("statementItemid");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		String[] paymentIDs = paymentID.split("=");
		String payment = null;
		String cardNo = null;
		try {
			payment = paymentIDs[0];
			cardNo = paymentIDs[1];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		List<HisStatementItemDetail> list = hisPatientService.getHisStatementItemDetailList(statementItemid);
		BigDecimal bd = as.getDiscountAmount(payment, cardNo, list);
		if(bd.compareTo(BigDecimal.ZERO)<=0){
			request.setAttribute(Constants.jsonResultCode, -10);
			return;
		}
		request.setAttribute(Constants.jsonResultData, bd);
	}


	/**
	 * 处置项结账
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "/checkoutHisStatementChargeList.json")
	public void checkoutHisStatementChargeList(HttpServletRequest request, HttpServletResponse response, String data) {
		//取本地
		String sessionToken = (String) request.getSession().getAttribute("sessionID"); 
		String token = null;
		try {
			token = getJson(data).getString("token");
			if (sessionToken.equals(token)) {
				sessionToken="";
			}else {
				request.setAttribute(Constants.jsonResultCode, -110);
				return;
			}
		} catch (NullPointerException e) {
			request.setAttribute(Constants.jsonResultCode, -110);
			return;
		}
		
		
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		String kindId = "";
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		String remark = null;
		try {
			remark = getJson(data).getString("remark");
		} catch (NullPointerException e) {
		}
		Long operatorId = Long.parseLong(cookie.getValue());
		Long statementItemid = getJson(data).getLong("statementItemid");
		data = getJson(data).getString("content");
		Map<String, String> map = hisPatientService.getHisPaymentMap();
		List<HisStatementCharge> hisStatementChargeList = toList(data, HisStatementCharge.class);
		if (hisStatementChargeList.size() == 0) {
			request.setAttribute(Constants.jsonResultCode, -1);
			return;
		}
		List<HisStatementCharge> list = new ArrayList<HisStatementCharge>();
		// 卡卷类序列号
		String zkNo = null;
		for (int i = 0; i < hisStatementChargeList.size(); i++) {
			HisStatementCharge hsc = hisStatementChargeList.get(i);
			if (hsc.getPaymentType().equals("yskzr")) {
				list.add(hsc);
			} else if (hsc.getPaymentType().equals("QK")) {
				list.add(hsc);
			} else if (hsc.getPaymentType().equals("ICK")) {
				list.add(hsc);
			} else if (hsc.getPaymentSubtype() != null) {
				if (hsc.getPaymentSubtype().equals("SCHD")) {
					String[] paymentIDs = hsc.getPaymentType().split("=");
					String payment = "YHHD";
					kindId = paymentIDs[0];
					try {
						zkNo = paymentIDs[1];
					} catch (ArrayIndexOutOfBoundsException e) {
					}
					hsc.setPaymentType(payment);
					hsc.setPaymentSubtype(null);
					hsc.setSerialNumber(zkNo);
					list.add(hsc);
				}
			}
		}
		try {
			// 结算，如果卡卷类结帐方式的卡为“有卡无卡号”类型，则将此卡设置为不可用。
			accountManagementCService.checkoutHisStatementChargeList(as, unit, remark, map, statementItemid, operatorId, list, hisStatementChargeList, kindId);
			request.setAttribute(Constants.jsonResultCode, 0);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Constants.jsonResultCode, -1);
		}
	}

	/**
	 * 处置单调整
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "disposalOrderAdjustment.json")
	public void disposalOrderAdjustment(HttpServletRequest request, HttpServletResponse response, String data) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		Long operatorId = Long.parseLong(cookie.getValue());
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Long regId = getJson(data).getLong("regId");
		Integer status = getJson(data).getInteger("status");
		Long statementItemid = getJson(data).getLong("statementItemid");
		String remark = getJson(data).getString("remark");
		Map<String, String> map = hisPatientService.getHisPaymentMap();
		try {
			accountManagementCService.disposalOrderAdjustment(as, unit, regId, status, statementItemid, operatorId, remark, map);
			request.setAttribute(Constants.jsonResultCode, 0);
		} catch (Exception e) {
			request.setAttribute(Constants.jsonResultCode, -1);
			e.printStackTrace();
		}
	}

	/**
	 * 还欠款
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "Arrears.json")
	public void Arrears(HttpServletRequest request, HttpServletResponse response, String data) {
		Date date = new Date();
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long operatorId = Long.parseLong(cookie.getValue());
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		Long patId = getJson(data).getLong("patId");
		String remack = getJson(data).getString("remark");
		data = getJson(data).getString("content");
		List<HisStatementCharge> listC = toList(data, HisStatementCharge.class);
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		try {
			accountManagementCService.Arrears(remack, operatorId, date, unit, patId, listC, as);
			request.setAttribute(Constants.jsonResultCode, 0);
		} catch (Exception e) {
			request.setAttribute(Constants.jsonResultCode, -1);
		}
	}
	
	/**
	 * 获取还欠款列表
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "getArrearsList.json")
	public void getArrearsList(HttpServletRequest request, HttpServletResponse response, String data) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Long patId = getJson(data).getLong("patId");
		request.setAttribute(Constants.jsonResultData,as.getChangeArrearsList(patId));
	}
	
	
	/**
	 * 获取还欠款信息
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "ArrearsInformation.json")
	public void ArrearsInformation(HttpServletRequest request, HttpServletResponse response, String data) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Long statementId = getJson(data).getLong("statementId");
		request.setAttribute(Constants.jsonResultData,as.arrearsInformation(statementId));
	}
	
	/**
	 * 查询欠款条目
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "Debts.json")
	public void Debts(HttpServletRequest request, HttpServletResponse response, String data) {
		HisStatement hs = new HisStatement();
		hs.setPatId(getJson(data).getLong("patId"));
		hs.setSqlStr(" and debt_amount > payoff");
		// getHisStatementList
		List<HisStatementPojo> list = hisPatientService.getHisStatementList(hs);
		BigDecimal bd = BigDecimal.ZERO;
		for (HisStatementPojo hsp : list) {
			bd = bd.add(hsp.getDebtAmount());
			bd = bd.subtract(hsp.getPayoff());
		}
		Debts de = new Debts();
		de.setAmount(bd);
		de.setList(list);
		request.setAttribute(Constants.jsonResultData, de);
	}

	/**
	 * 收费单调整
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "Refund.json")
	public void Refund(HttpServletRequest request, HttpServletResponse response, String data) {
		Long statementItemid = getJson(data).getLong("statementItemid");
		String remark = getJson(data).getString("remark");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		Map<String, String> map = hisPatientService.getHisPaymentMap();
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long operatorId = Long.parseLong(cookie.getValue());
		try {
			accountManagementCService.Refund(as, unit, statementItemid, operatorId, 4,remark, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 获取X光列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getHisXtXray.json")
	public void getHisXtXray(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, hisPatientService.getHisXtXrayList());
	}

	private JSONObject getJson(HttpServletRequest request) {
		String reqStr = "";
		try {
			reqStr = SysUtil.getInputStr(request.getInputStream(), request.getContentLength());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONUtils.toJSONObject(reqStr);
	}

	private <T> List<T> toList(String reqStr, Class<T> beanClass) {
		return JSONUtils.toList(reqStr, beanClass);
	}

	private JSONObject getJson(String data) {
		data = data.replaceAll("#", "&");
		return JSONUtils.toJSONObject(data);
	}

	@RequestMapping(value = "saveDemo.json")
	public void saveDemo(HttpServletRequest request, HttpServletResponse response) {
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		try {
			accountManagementCService.saveDemo(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "loadIcCard.json")
	public void loadIcCard(HttpServletRequest request, HttpServletResponse response, String data) {
		String disccardno = getJson(data).getString("disccardno");
		Long patId = getJson(data).getLong("patId");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		request.setAttribute(Constants.jsonResultData, as.loadIcCard(disccardno, patId));
	}

	/**
	 * 会计退费
	 * @param request
	 * @param response
	 * @param data
	 */
	@RequestMapping(value = "accountingRefund.json")
	public void accountingRefund(HttpServletRequest request, HttpServletResponse response, String data) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		String sMoney = getJson(data).getString("money");
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		String remark = null;
		try {
			remark = getJson(data).getString("remark");
		} catch (NullPointerException e) {
		}
		Long operatorId = Long.parseLong(cookie.getValue());
		Long statementItemid = getJson(data).getLong("statementItemid");
		BigDecimal money = new BigDecimal(sMoney);
		boolean flag = accountManagementCService.accountingRefund(unit, remark, operatorId, statementItemid, money);
		if (flag) {
			request.setAttribute(Constants.jsonResultCode, 0);
		} else {
			request.setAttribute(Constants.jsonResultCode, -6);
		}
	}

	/**
	 * 此方法没有被调用
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "demoUpdate.json")
	public void demoUpdate(HttpServletRequest request, HttpServletResponse response) {
		accountManagementCService.updateCloudStatement();
	}

	/**
	 * 获取结算单ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getStatementId.json")
	public void getStatementId(HttpServletRequest request, HttpServletResponse response,String data) {
		Long statementItemid = getJson(data).getLong("statementItemid");
		request.setAttribute(Constants.jsonResultData, hisPatientService.getStatementId(statementItemid));
	}

	/**
	 * 获取结算单ID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getStatementIdByItemId.json")
	public void getStatementIdByItemId(HttpServletRequest request, HttpServletResponse response,String data) {
		Long statementItemid = getJson(data).getLong("statementItemid");
		request.setAttribute(Constants.jsonResultData, hisPatientService.getStatementId(statementItemid));
	}
	
	@RequestMapping(value = "getPosList.json")
	public void getPosList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, hisPatientService.getPosList());
	}
	
	@RequestMapping(value = "getOperator.json")
	public void getOperator(HttpServletRequest request, HttpServletResponse response,String data) {
		Long statementId = getJson(data).getLong("statementId");
		AccountService as = (AccountService) HessianFactoryUtil.getHessianObj(AccountService.class);
		request.setAttribute(Constants.jsonResultData, hisPatientService.getEmployeeName(statementId,as));
	}
	
	/**
	 * 获取当前时间
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getNow.json")
	public void getNow(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData,DateUtils.date2Str(new Date(), DateUtils.datetimeFormat));
	}

}