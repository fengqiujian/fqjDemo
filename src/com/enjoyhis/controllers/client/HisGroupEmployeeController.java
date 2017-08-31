package com.enjoyhis.controllers.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisGroupEmployee;
import com.enjoyhis.rmiclient.IHisGroupAssociatedRelevance;
import com.enjoyhis.service.HisGroupAssociatedRelevanceService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping(value = "/client/employeegroup")
public class HisGroupEmployeeController {
	final Logger logger = LoggerFactory.getLogger(HisGroupEmployeeController.class);
	IHisGroupAssociatedRelevance URL = (IHisGroupAssociatedRelevance) HessianFactoryUtil.getHessianObj(IHisGroupAssociatedRelevance.class);
	@Autowired
	private HisGroupAssociatedRelevanceService hisGroupEmployeeService;

	// 显示所有数据
	@RequestMapping(value = "/empgrou_main.htm")
	public String findAll(HisGroupEmployee hisGroupEmployee, HttpServletRequest request, HttpServletResponse response) {
		return "/client/employeegroup/empgroupmanaer";
	}

	// 查询所有数据
	@RequestMapping(value = "/find_List.json")
	public void findSkipList(HisGroupEmployee hisGroupEmployee, Long gName, Long eId, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<HisGroupEmployee> list = hisGroupEmployeeService.page4List(hisGroupEmployee, gName, eId, pageNumber, pageSize);
		Integer count = hisGroupEmployeeService.findCount(hisGroupEmployee);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@Transactional
	public int JtToFy() {
		List<HisGroupEmployee> listjt = null;
		try {
			listjt = URL.findGroupAssociatedRelevanceAll(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		hisGroupEmployeeService.deleteGroupInfo(new HisGroupEmployee());
		int num = 0;
		if (listjt != null) {
			for (HisGroupEmployee entiyjt : listjt) {
				// Date modifyTimeJts = entiyjt.getModifyTime();
				// Long id = entiyjt.getId();
				// // 用集团的id查分院
				// HisGroupEmployee listfy = hisGroupEmployeeService.findById(id);
				// if (listfy != null) {
				// Date modifyTimeFys = listfy.getModifyTime();
				// entiyjt.setId(id);
				// // 此处时间判断不兼容性高，带完善
				// if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) ||
				// (modifyTimeFys.compareTo(modifyTimeJts) < 0) ||
				// (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
				// entiyjt.setModifyTime(new Date());
				// entiyjt.setCreateTime(entiyjt.getCreateTime());
				// num = hisGroupEmployeeService.updateGroupInfo(entiyjt);
				// }
				// } else {// 当ID不存在的时候插入当前数据
				// entiyjt.setModifyTime(new Date());
				num = hisGroupEmployeeService.addGroupInfo(entiyjt);
				num = num / num;
				// entiyjt.setCreateTime(new Date());
				// }
			}
		}
		return num;
	}

	@RequestMapping(value = "/findEntityInfos.json")
	public void findEntitys(HisGroupEmployee hisGroupAsso, HttpServletRequest request, HttpServletResponse respons) {
		List<HisGroupEmployee> list = hisGroupEmployeeService.findGroupGroupAssoInfoAll(hisGroupAsso);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}
}
