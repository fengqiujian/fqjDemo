package com.enjoyhis.controllers.client;

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

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisServiceItem;
import com.enjoyhis.persistence.his.po.HisServiceItemFl;
import com.enjoyhis.persistence.his.po.HisServiceItemXm;
import com.enjoyhis.pojo.ServiceItemFl;
import com.enjoyhis.rmiclient.IHisServiceItemFl;
import com.enjoyhis.service.HisServiceItemService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;
import com.enjoyhis.util.PingyinUtil;

/**
 * 
 * @Name:HisServiceItemFlController
 * @Description: 处置项二级菜单处理
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 * @Parameters: 参数
 * @Return: 返回的页面
 */
@Controller
@RequestMapping("/client/serviceitemflxm")
public class HisServiceItemFlController {

	final Logger logger = LoggerFactory.getLogger(HisServiceItemFlController.class);
	IHisServiceItemFl URL = (IHisServiceItemFl) HessianFactoryUtil.getHessianObj(IHisServiceItemFl.class);
	@Autowired
	private HisServiceItemService hisServiceItemService;

	/**
	 * 显示所有数据列表
	 * 
	 * @param hisServiceItemFl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/service_fl.htm")
	public String findAll(HisServiceItemFl hisServiceItemFl, HttpServletRequest request, HttpServletResponse response) {
		return "/client/serviceitemflxm/serviceitemfl";
	}

	/**
	 * 条件查询或查询所有数据
	 * 
	 * @param hisServiceItemFl
	 * @param iNames
	 * @param iCodes
	 * @param pageNumber
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/find_fl_List.json")
	public void findFlList(HisServiceItemFl hisServiceItemFl, String iNames, String charges, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<HisServiceItemFl> list = hisServiceItemService.page4ListFl(hisServiceItemFl, iNames, charges, pageNumber, pageSize);

		// 处理父及名称
		List<ServiceItemFl> lists = new ArrayList<>();
		for (HisServiceItemFl entity : list) {
			lists.add(copyPro(entity));
		}

		Integer count = hisServiceItemService.findCountFl(hisServiceItemFl);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(lists);
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 删除活动项(更改status状态)
	 * 
	 * @param hisServiceItem
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete_item_fl.htm")
	public void deleteItemFl(HisServiceItemFl hisServiceItemFl, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ItemCode = hisServiceItemFl.getItemCode();
		Integer status = hisServiceItemFl.getStatus();
		// 更改状态值
		if (status == 1) {
			status = 0;
		}
		hisServiceItemFl.setItemCode(ItemCode);
		hisServiceItemFl.setStatus(status);
		int statusValue = hisServiceItemService.updateStatusFl(hisServiceItemFl);
		hisServiceItemFl = hisServiceItemService.findByIdFl(ItemCode);
		Integer statusFl = hisServiceItemFl.getStatus();
		// 保证当前状态和子状态一致
		if (statusFl == 0) {
			String parentId = ItemCode;
			HisServiceItemXm hisServiceItemXm = new HisServiceItemXm();
			hisServiceItemXm.setParentId(parentId);
			List<HisServiceItemXm> list = hisServiceItemService.selectItemXmInfo(hisServiceItemXm);
			for (HisServiceItemXm entiy : list) {
				Integer xmstatus = entiy.getStatus();
				String id = entiy.getItemCode();
				if (xmstatus == 1) {
					xmstatus = 0;
				}
				entiy.setItemCode(id);
				entiy.setStatus(xmstatus);
				hisServiceItemService.updateStatusXm(entiy);
			}

		}

		response.getWriter().println(statusValue);
	}

	/**
	 * 收费科目上级名称处理
	 * 
	 * @param formPro
	 * @return serviceItemFl
	 */
	public ServiceItemFl copyPro(HisServiceItemFl formPro) {
		ServiceItemFl serviceItemFl = new ServiceItemFl();
		BeanCopyUtil.copyProperties(formPro, serviceItemFl);
		// HisServiceItem hisServiceItem = hisServiceItemService.findById(formPro.getParentId());
		HisServiceItem hisServiceItem = hisServiceItemService.findByIdItem(formPro.getParentId());
		if (hisServiceItem != null) {
			serviceItemFl.setItemParentName(hisServiceItem.getItemName());
		}
		return serviceItemFl;
	}

	/**
	 * 添加二级处置项
	 * 
	 * @param hisServiceItemFl
	 * @param request
	 * @param response
	 * @throws Exception
	 */

	@RequestMapping("add_ItemFl.json")
	public void addItemFl(HisServiceItemFl hisServiceItemFl, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemcode = request.getParameter("itemCode");
		String pym = PingyinUtil.getPinYinHeadChar(hisServiceItemFl.getItemName());
		hisServiceItemFl.setPym(pym);
		// 如果是增加或是修改，根据ID进行判断，如果ID存在就更新，如果ID不存在就插入
		if (itemcode != null && itemcode != "") {
			HisServiceItemFl hisServiceItemFls = hisServiceItemService.findByIdFl(itemcode);
			// 根据ID查询，如果查询到就更新，未查询到就添加
			if (hisServiceItemFls != null) {
				hisServiceItemFl.setItemCode(itemcode);
				// 更新
				hisServiceItemFls.setModifyTime(new Date());
				int idstatus = hisServiceItemService.updatItemFl(hisServiceItemFl);
				response.getWriter().println(idstatus);
			} else {
				// 直接插入数据
				hisServiceItemFl.setItemCode(itemcode);
				hisServiceItemFl.setCreateTime(new Date());
				hisServiceItemFl.setModifyTime(new Date());
				int idstatus = hisServiceItemService.addItemFlInfo(hisServiceItemFl);
				response.getWriter().println(idstatus);
			}
		}
	}

	/**
	 * 校验ID
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "check_item_id.htm")
	public void checkId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if (id != null) {
			String flag = hisServiceItemService.checkById(id);
			response.getWriter().println(flag);
		} else {
			response.getWriter().println("");
		}
	}

	/**
	 * 修改二级处置项信息前回显
	 * 
	 * @param hisStation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/echos_ItemFl.json")
	public void modifyItemFl(HisServiceItemFl hisServiceItemFl, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentCode = request.getParameter("itemCode");
		if (StringUtils.isNotBlank(currentCode)) {
			hisServiceItemFl.setModifyTime(new Date());
			hisServiceItemFl = hisServiceItemService.findByIdFl(currentCode);
		}
		request.setAttribute(Constants.jsonResultData, hisServiceItemFl);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	// 数据同步，且价格与本地相同时用本地更新集团
	@Transactional
	public int JtDataTOFyFl() {
		List<HisServiceItemFl> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisServiceItemService.deleteFlInfo(null);
		if (listjt != null) {
			for (HisServiceItemFl entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				String idjt = entiyjt.getItemCode();
//				HisServiceItemFl listfy = hisServiceItemService.findByIdFl(idjt);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setItemCode(idjt);
//					if (modifyTimeFys != null && modifyTimeJts != null) {
//						if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//							num = hisServiceItemService.updatItemFl(entiyjt);
//							URL.updatItemFl(entiyjt);
//						}
//					}
//
//				} else {
					num = hisServiceItemService.addItemFl(entiyjt);
					num = num/num;
			}
		}
		return num;
	}
}
