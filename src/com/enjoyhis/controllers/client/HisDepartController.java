package com.enjoyhis.controllers.client;

import java.util.Date;
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
import com.enjoyhis.persistence.his.po.HisDepart;
import com.enjoyhis.rmiclient.IHisDepart;
import com.enjoyhis.service.HisDepartService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping(value = "/client/pages")
public class HisDepartController {
	final Logger logger = LoggerFactory.getLogger(HisDepartController.class);
	IHisDepart URL = (IHisDepart) HessianFactoryUtil.getHessianObj(IHisDepart.class);

	@Autowired
	private HisDepartService hisDepartService;

	// 显示所有数据
	@RequestMapping("/depart_fy_main.htm")
	public String findAll(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse response) {
		return "/client/pages/departmain";
	}

	// 查询所有数据
	@RequestMapping(value = "/find_fy_List.json")
	public void findSkipList(HisDepart hisDepart, String dName, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		// JtDataTOFy(hisDepart, request, response);
		if (dName != null && dName != "") {
			hisDepart.setDepName(dName);
		}
		// 从页面获取pageNumber,pageSize这两个参数
		List<HisDepart> list = hisDepartService.page4List(hisDepart, pageNumber, pageSize);
		// 返回数据以供多少
		Integer count = hisDepartService.findCount(hisDepart);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	// 添加科室
	@RequestMapping(value = "/addOrEdit_depart.json")
	public void addOrEditDepart(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 添加 ，返回判断是否添加 完成
		HisDepart depart = null;
		try {
			depart = URL.findDepart(hisDepart.getId());
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
		}
		if (depart != null) {
			hisDepart.setModifyTime(new Date());
			hisDepart.setCreateTime(depart.getCreateTime());
			int numb = URL.updateDepartInfo(hisDepart);
			response.getWriter().println(numb);
		} else {
			hisDepart.setModifyTime(new Date());
			hisDepart.setCreateTime(new Date());
			int idstatus = 0;
			try {
				idstatus = URL.addDepartInfo(hisDepart);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
			response.getWriter().println(idstatus);
		}
	}

	// 删除科室信息...更改状态值
	@RequestMapping(value = "/delete_depart.htm")
	public void deleteDepart(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer status = hisDepart.getStatus();
		// 设置状态值
		if (status == 1) {
			status = 0;
		}
		hisDepart.setStatus(status);
		int idVla = 0;
		try {
			idVla = URL.findIdToDepart(hisDepart);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
		}
		response.getWriter().println(idVla);
	}

	// 修改科室信息的时候数据回显
	@RequestMapping(value = "/modify_Depart.json")
	public void modifyDepart(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse respons) throws Exception {
		Integer id = hisDepart.getId();
		hisDepart.setId(id);
		if (id != null) {
			try {
				hisDepart = URL.modifyDepartInfo(id);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
		}
		request.setAttribute(Constants.jsonResultData, hisDepart);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	// 校验ID
	@RequestMapping(value = "check_id.htm")
	public void checkUser(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("id");
		if (ids != null) {
			String flag = "";
			try {
				flag = URL.checkById(Integer.parseInt(ids));
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
			response.getWriter().println(flag);
		} else {
			response.getWriter().println("");
		}
	}

	// 数据同步
	@Transactional
	public int JtDataTOFy() {
		List<HisDepart> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		// 遍历集团的数据
		int num = 0;
		hisDepartService.deleteDepartAll(null);
		if (listjt != null) {
			for (HisDepart entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Integer idjt = entiyjt.getId();
//				// 用集团的id查分院
//				HisDepart listfy = hisDepartService.findById(idjt);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(idjt);
//					// 此处时间判断不兼容性高，带完善
//					if (modifyTimeFys != null && modifyTimeJts != null) {
//						if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//							entiyjt.setModifyTime(new Date());
//							num = hisDepartService.updatAddDepartFy(entiyjt);
//						}
//					}
//				} else {// 当ID不存在的时候插入当前数据
					num = hisDepartService.addDepartFy(entiyjt);
					num = num/num;
//				}
			}
		}
		return num;
	}
}
