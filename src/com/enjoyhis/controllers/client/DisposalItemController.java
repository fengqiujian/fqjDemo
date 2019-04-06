package com.enjoyhis.controllers.client;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;
import com.enjoyhis.persistence.his.po.HisStatementItemDetail;
import com.enjoyhis.service.HisPatientService;
import com.enjoyhis.service.HisServiceItemService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.JSONUtils;

/**
 * 处置项
 * 
 * @author fqj
 */
@Controller
@RequestMapping(value = "client/DisposalItem")
public class DisposalItemController {

	@Autowired
	private HisPatientService hisPatientService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private HisServiceItemService hisServiceItemService;
	/**
	 * 页面跳转
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param patientId(患者ID)
	 * @param id(挂号ID)
	 * @return
	 */
	@RequestMapping(value = "gotoPage.htm")
	public String gotoPage(Model model, HttpServletRequest request, HttpServletResponse response, Long patientId, Long id) {
		model.addAttribute("patientId", patientId);
		model.addAttribute("id", id);
		model.addAttribute("zk", hisPatientService.getDocMaxDz(id));
		return "client/patient/disposalItem";
	}

	@RequestMapping(value = "gotoPage2.htm")
	public String gotoPage2(Model model, HttpServletRequest request, HttpServletResponse response, Long patientId, Long id) {
		model.addAttribute("patientId", patientId);
		model.addAttribute("id", id);
		BigDecimal money = BigDecimal.ZERO;
		try {
			money = hisServiceItemService.getMoney(id);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		model.addAttribute("money", money);
		return "client/patient/pendingDisposal";
	}

	@RequestMapping(value = "getHisStatementItemDetailList.json")
	public void getHisStatementItemDetailList(HttpServletRequest request, HttpServletResponse response, Long regId) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		try {
			page.setRows(hisServiceItemService.getHisStatementItemDetailList(regId));
		} catch (Exception e) {

		}
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 获取所有一级处置项
	 * 
	 * @param request
	 * @param response
	 * @param patientId
	 */
	@RequestMapping(value = "HisPatient.json")
	public void HisPatient(HttpServletRequest request, HttpServletResponse response, Long patientId) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisServiceItemService.getAllHisServiceItemList());
		page.setObj(hisPatientService.getHisPatient(patientId));
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "getHisServiceItemFlList.json")
	public void getHisServiceItemFlList(HttpServletRequest request, HttpServletResponse response, String itemCode) {
		HisServiceItemFl hsif = new HisServiceItemFl();
		hsif.setParentId(itemCode);
		hsif.setStatus(1);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisServiceItemService.getHisServiceItemFlList(hsif));
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 清除处置项
	 * 
	 * @param request
	 * @param response
	 * @param itemCode
	 */
	@RequestMapping(value = "clearLSCZX.json")
	public void clearLSCZX(HttpServletRequest request, HttpServletResponse response, Long regId, String tooth) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setSuccess(hisServiceItemService.clearLSCZX(regId, tooth));
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 修改临时次数
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @param qty
	 */
	@RequestMapping(value = "updateLSqty.json")
	public void updateLSqty(HttpServletRequest request, HttpServletResponse response, Long id, int qty) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setSuccess(hisServiceItemService.updateLSqty(id, qty));
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 获取临时列表
	 * 
	 * @param request
	 * @param response
	 * @param regId
	 * @param tooth
	 */
	@RequestMapping(value = "getHisStatementItemDetailListLS.json")
	public void getHisStatementItemDetailListLS(HttpServletRequest request, HttpServletResponse response, Long regId, String tooth) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		try {
			page.setRows(hisServiceItemService.getHisStatementItemDetailListLS(regId, tooth));
		} catch (Exception e) {

		}
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "getHisServiceItemXmList.json")
	public void getHisServiceItemXmList(HttpServletRequest request, HttpServletResponse response, String itemCode) {
		HisServiceItemXm hsix = new HisServiceItemXm();
		if (itemCode == null) {
			itemCode = "";
		}
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		hsix.setParentId(itemCode);
		hsix.setStatus(1);
		hsix.setIsShow(1);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisServiceItemService.getHisServiceItemXmList(hsix, unit));
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 医生保存处置单
	 * 
	 * @param request
	 * @param response
	 * @param regId
	 * @param patientId
	 * @param sh
	 * @param dz
	 * @param ze
	 * @param list
	 */
	@RequestMapping(value = "saveTable.json")
	public void saveTable(HttpServletRequest request, HttpServletResponse response, Long regId, Long patientId, BigDecimal sh, BigDecimal dz, BigDecimal ze, String list) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long docId = Long.parseLong(cookie.getValue());
		List<HisStatementItemDetail> lists = null;
		Map<String, String> map = sysConfigService.getSysConfig();
		PageResultForBootstrap page = new PageResultForBootstrap();
		boolean flag;
		if (StringUtils.isNotEmpty(list)) {
			// lists = JSONUtils.toList(list, HisStatementItemDetail.class);
			try {
				flag = hisServiceItemService.saveTable(regId, patientId, dz, ze, lists, docId, map);
				page.setSuccess(flag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 如果处置项为空，则直接将此用户设置为完成状态
			try {
				hisServiceItemService.updateReg(regId, 4, new Date(), null);
				page.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 医生保存临时
	 * 
	 * @param request
	 * @param response
	 * @param regId
	 * @param patientId
	 * @param sh
	 * @param dz
	 * @param ze
	 * @param list
	 */
	@RequestMapping(value = "saveTable2.json")
	public void saveTable2(HttpServletRequest request, HttpServletResponse response, Long regId, Long patientId, String list) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		Long docId = Long.parseLong(cookie.getValue());
		List<HisStatementItemDetail> lists = null;
		Map<String, String> map = sysConfigService.getSysConfig();
		PageResultForBootstrap page = new PageResultForBootstrap();
		boolean flag;
		if (StringUtils.isNotEmpty(list)) {
			lists = JSONUtils.toList(list, HisStatementItemDetail.class);
			try {
				flag = hisServiceItemService.saveTable2(regId, patientId, lists, docId, map);
				page.setSuccess(flag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 如果处置项为空，则直接将此用户设置为完成状态
			try {
				page.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 修改牙位
	 * 
	 * @param request
	 * @param response
	 * @param regId
	 * @param tooth
	 * @param newtooth
	 */
	@RequestMapping(value = "changeTooth.json")
	public void changeTooth(HttpServletRequest request, HttpServletResponse response, Long regId, String tooth, String newtooth) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		boolean flag;
		flag = hisServiceItemService.changeTooth(regId, tooth, newtooth);
		page.setSuccess(flag);
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "getItemForTable.json")
	public void getItemForTable(HttpServletRequest request, HttpServletResponse response, String code) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setObj(hisServiceItemService.getItemForTable(code));
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "getSearchItemList.json")
	public void getSearchItemList(HttpServletRequest request, HttpServletResponse response, String pym) {
		HisServiceItemXm hisx = new HisServiceItemXm();
		hisx.setStatus(1);
		hisx.setIsShow(1);
		hisx.setSqlStr(" and item_name like '%" + pym + "%' or pym like '%" + pym + "%'");
		hisx.setLimitStart(0L);
		hisx.setLimitCount(10);
		PageResultForBootstrap page = new PageResultForBootstrap();
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		page.setRows(hisServiceItemService.getSearchItemList(hisx, unit));
		request.setAttribute(Constants.pageResultData, page);
	}
}