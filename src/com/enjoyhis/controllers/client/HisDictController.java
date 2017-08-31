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
import com.enjoyhis.persistence.his.po.HisDict;
import com.enjoyhis.rmiclient.IHisDict;
import com.enjoyhis.service.HisDictService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping("/client/dictionary")
public class HisDictController {
	final Logger logger = LoggerFactory.getLogger(HisDictController.class);
	IHisDict URL = (IHisDict) HessianFactoryUtil.getHessianObj(IHisDict.class);
	@Autowired
	private HisDictService hisDictService;

	@RequestMapping("/findAll.htm")
	public String findAll(HisDict hisDict, HttpServletRequest request, HttpServletResponse response) {
		return "/client/dictionary/dicconfig";
	}

	@RequestMapping(value = "/find_dict_List.json")
	public void findDicList(HisDict hisDict, Integer pageNumber, String iNames, String charges, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		// JtDataTOFy(hisDict, request, response);
		if (iNames != null && iNames != "") {
			hisDict.setDictName(iNames);
		}
		if (charges != null && charges != "") {
			hisDict.setDictType(charges);
		}
		List<HisDict> list = hisDictService.page4List(hisDict, pageNumber, pageSize);
		// List<HisDict> list = URL.page4ListTo(hisDict, pageNumber, pageSize);

		Integer count = hisDictService.findCount(hisDict);
		// Integer count = URL.findCountTo(hisDict);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * 添加数据
	 * 
	 * @param hisDict
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("add_dic.json")
	public void addDict(HisDict hisDict, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = hisDict.getId();
		hisDictService.deleteDictAll(hisDict);
		// 如果是增加或是修改，根据ID进行判断，如果ID存在就更新，如果ID不存在就插入
		if (id != null) {
			HisDict hisDicts = null;
			try {
				hisDicts = URL.findDictInfo(id);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
			// 根据ID查询，如果查询到就更新，未查询到就添加
			if (hisDicts != null) {
				hisDict.setId(id);
				// 更新
				hisDict.setModifyTime(new Date());
				hisDict.setCreateTime(hisDicts.getCreateTime());
				int idstatus = URL.updatDictInfo(hisDict);
				response.getWriter().println(idstatus);
			} else {
				// 直接插入数据
				hisDict.setCreateTime(new Date());
				hisDict.setModifyTime(new Date());
				int idstatus = 0;
				try {
					idstatus = URL.addDictInfo(hisDict);
				} catch (HessianRuntimeException e) {
					LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
				}
				response.getWriter().println(idstatus);
			}
		}
	}

	/**
	 * 修改信息前回显
	 * 
	 * @param hisStation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/echos_Dict.json")
	public void modifyDict(HisDict hisDict, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = hisDict.getId();
		if (id != null) {
			hisDict.setModifyTime(new Date());
			try {
				hisDict = URL.findDictInfo(id);
			} catch (HessianRuntimeException e) {
				LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			}
		}
		request.setAttribute(Constants.jsonResultData, hisDict);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 校验ID
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	// @RequestMapping(value="check_dict_id.htm")
	// public void checkId(HttpServletRequest request, HttpServletResponse response)throws
	// Exception{
	// String ids = request.getParameter("id");
	// Integer id = Integer.parseInt(ids);
	// if (id!=null) {
	// String flag = hisDictService.checkById(id);
	// response.getWriter().println(flag);
	// } else {
	// response.getWriter().println("");
	// }
	// }
	/**
	 * 删除
	 * 
	 * @param hisServiceItemFl
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete_dict.htm")
	public void deleteDict(HisDict hisDict, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int statusValue = 0;
		try {
			statusValue = URL.deleteDict(hisDict.getId());
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
		}
		response.getWriter().println(statusValue);
	}

	// 数据同步
	@Transactional
	public int JtDataTOFy() {

		List<HisDict> listjt = null;
		try {
			listjt = URL.findDictAll(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisDictService.deleteDictAll(new HisDict());
		if (listjt != null) {
			for (HisDict entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Integer idjt = entiyjt.getId();
//				// 用集团的id查分院
//				HisDict listfy = hisDictService.findById(idjt);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(idjt);
//					// 此处时间判断不兼容性高，带完善
//					if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//						num = hisDictService.updateDictInfo(entiyjt);
//						hisDictService.delectNoid(null, idjt);
//					}
//				} else {// 当ID不存在的时候插入当前数据
					num = hisDictService.addDictInfo(entiyjt);
					num = num/num;
//				}
			}
		}
		return num;
	}
}
