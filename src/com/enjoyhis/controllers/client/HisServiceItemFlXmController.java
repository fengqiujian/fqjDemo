package com.enjoyhis.controllers.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;
import com.enjoyhis.pojo.ServiceItemXm;
import com.enjoyhis.rmiclient.IHisServiceItemFlXm;
import com.enjoyhis.service.HisServiceItemService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;
import com.enjoyhis.util.PingyinUtil;

/**
 * 
 * @Name: HisServiceItemXmController
 * @Description: 处置项三级菜单更能
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 */
@Controller
@RequestMapping("/client/serviceitemflxm")
public class HisServiceItemFlXmController {
	final Logger logger = LoggerFactory.getLogger(HisServiceItemFlXmController.class);
	IHisServiceItemFlXm URL = (IHisServiceItemFlXm) HessianFactoryUtil.getHessianObj(IHisServiceItemFlXm.class);
	@Autowired
	private HisServiceItemService hisServiceItemService;

	/**
	 * 显示所有数据
	 * 
	 * @param hisServiceItemXm
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/serviceitem_xm.htm")
	public String findAll(HisServiceItemXm hisServiceItemXm, HttpServletRequest request, HttpServletResponse response) {
		return "/client/serviceitemflxm/serviceitemxm";
	}

	/**
	 * 查询所有数据
	 * 
	 * @param hisServiceItemXm
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find_xm_List.json", method = RequestMethod.POST)
	public void findSkipList(boolean dataty, HisServiceItemXm hisServiceItemXm, String iNames, String charges, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		// 获取session中的unitCode
		String sessionCode = request.getSession().getAttribute("login_unit_code").toString();
		// sc = sessionCode;
		List<HisServiceItemXm> list = hisServiceItemService.page4ListXm(sessionCode, dataty, hisServiceItemXm, iNames, charges, pageNumber, pageSize);
		List<ServiceItemXm> lists = new ArrayList<>();
		for (HisServiceItemXm entity : list) {
			lists.add(copyPro(entity));
		}

		Integer count = hisServiceItemService.findCount(hisServiceItemXm);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(lists);
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 添加数据、修改数据
	 * 
	 * @param hisServiceItemXm
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "add_itemxm.json")
	public void addItemXm(HisServiceItemXm hisServiceItemXm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemcode = hisServiceItemXm.getItemCode();
		BigDecimal price = hisServiceItemXm.getPrice();
		String itna = hisServiceItemXm.getItemName();
		hisServiceItemXm.setItemName(itna);

		/*
		 * String jtname = hisServiceItemXm.getItemJtname();
		 * hisServiceItemXm.setItemJtname(jtname);
		 */
		hisServiceItemXm.setPrice(price);
		hisServiceItemXm.setItemCode(itemcode);
		String pym = PingyinUtil.getPinYinHeadChar(hisServiceItemXm.getItemName());
		hisServiceItemXm.setPym(pym);
		// 如果是增加或是修改，根据ID进行判断(jituan )，如果ID存在就更新，如果ID不存在就插入
		if (itemcode != null && itemcode != "") {
			HisServiceItemXm hisServiceItemXmsInfo = null;
			try {
				hisServiceItemXmsInfo = URL.findByIdXmInfo(itemcode);
				// 通过分院当前的ID查询集团并修改集团的别名
				hisServiceItemXmsInfo.setItemName(itna);
				URL.updatItemXmPrice(hisServiceItemXmsInfo);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}

			HisServiceItemXm hisServiceItemXms = hisServiceItemService.findByIdXm(itemcode);
			// 根据ID查询，如果查询到就更新，未查询到就添加
			if (hisServiceItemXms != null) {
				// 更新
				hisServiceItemXm.setHospCode(hisServiceItemXms.getHospCode());
				hisServiceItemXm.setCreateTime(hisServiceItemXms.getCreateTime());
				hisServiceItemXm.setItemJtname(hisServiceItemXms.getItemJtname());
				hisServiceItemXm.setParentId(hisServiceItemXms.getParentId());
				hisServiceItemXm.setModifyTime(new Date());
				int idstatus = hisServiceItemService.updatItemXm(hisServiceItemXm);
				response.getWriter().println(idstatus);
			}
		}
	}

	/**
	 * 处置项信息回显
	 * 
	 * @param hisServiceItemXm
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify_itemxm.json")
	public void modifyItemXm(HisServiceItemXm hisServiceItemXm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemcode = hisServiceItemXm.getItemCode();
		if (StringUtils.isNotBlank(itemcode)) {
			hisServiceItemXm.setModifyTime(new Date());
			hisServiceItemXm = hisServiceItemService.findByIdXm(itemcode);
		}
		request.setAttribute(Constants.jsonResultData, hisServiceItemXm);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 收费科目上级名称处理
	 * 
	 * @param formPro
	 * @return serviceItemFl
	 */
	public ServiceItemXm copyPro(HisServiceItemXm formPro) {
		ServiceItemXm serviceItemXm = new ServiceItemXm();
		BeanCopyUtil.copyProperties(formPro, serviceItemXm);
		HisServiceItemFl hisServiceItemFl = hisServiceItemService.selectFlName(formPro.getParentId());
		if (hisServiceItemFl != null) {
			serviceItemXm.setItemParentName(hisServiceItemFl.getItemName());
		}
		return serviceItemXm;
	}

	// 数据同步，且价格与本地相同时用本地更新集团
	@Transactional
	public int JtDataTOFy() {
		int num = 0;
		List<HisServiceItemXm> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return num;
		}

		if (listjt != null) {
			List<HisServiceItemXm> update = new ArrayList<>();
			List<HisServiceItemXm> creat = new ArrayList<>();
			
			for (HisServiceItemXm entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
				String idjt = entiyjt.getItemCode();
//				BigDecimal jtprc = entiyjt.getPrice();
//				String jn = entiyjt.getItemName();
				HisServiceItemXm listfy = hisServiceItemService.findById(idjt);
				if (listfy != null) {
					listfy.setItemCode(entiyjt.getItemCode());
//					listfy.setItemName(entiyjt.getItemName());
					listfy.setPym(entiyjt.getPym());
					listfy.setParentId(entiyjt.getParentId());
					listfy.setLevel(entiyjt.getLevel());
					listfy.setJtPrice(entiyjt.getJtPrice());
//					listfy.setPrice(entiyjt.getPrice());
					listfy.setUnit(entiyjt.getUnit());
					listfy.setCreateTime(entiyjt.getCreateTime());
					listfy.setModifyTime(entiyjt.getModifyTime());
					listfy.setStatus(entiyjt.getStatus());
//					listfy.setIsShow(entiyjt.getIsShow());
					listfy.setHospCode(entiyjt.getHospCode());
					listfy.setItemJtname(entiyjt.getItemJtname());
					//update.add(listfy);
					num = hisServiceItemService.updateByPrimaryKey(listfy);
					num = num/num;
//					String fn = listfy.getItemJtname();
//					Date modifyTimeFys = listfy.getModifyTime();
//					if (!jn.equals(fn)) {
//						hisServiceItemService.updatItemFlXm(entiyjt);
//						num = URL.updatItemXmPrice(entiyjt);
//					}
//					if ((listfy.getPrice()) != jtprc) {
//						entiyjt.setPrice(listfy.getPrice());
//						entiyjt.setModifyTime(new Date());
//						num = URL.updatItemXmPrice(entiyjt);
//						hisServiceItemService.updatItemFlXm(entiyjt);
//					} else {
//						if (modifyTimeFys != null && modifyTimeJts != null) {
//							if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//								URL.updatItemXmPrice(entiyjt);
//								num = hisServiceItemService.updatItemFlXms(entiyjt);
//							}
//						}
//					}
				} else {
					entiyjt.setItemName(entiyjt.getItemJtname());
					entiyjt.setPrice(entiyjt.getJtPrice());
//					creat.add(listfy);
					num = hisServiceItemService.addItemFlXms(entiyjt);
					num = num/num;
				}
			}
//			if(creat.size()>0){
//				num = hisServiceItemService.insertAll(creat);
//				num = num /num;
//			}
//			if(update.size()>0){
//				num = hisServiceItemService.updateAll(update);
//				num = num /num;
//			}
		}
		return num;
	}
}
