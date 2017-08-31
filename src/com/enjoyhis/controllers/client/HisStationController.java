package com.enjoyhis.controllers.client;

import java.io.IOException;
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
import com.enjoyhis.persistence.his.po.HisStation;
import com.enjoyhis.rmiclient.IHisStation;
import com.enjoyhis.service.HisStationService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;

/**
 * @Name: HisStationController
 * @Description: 岗位的操作
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 */
@Controller
@RequestMapping(value = "/client/stationpage")
public class HisStationController {
	final Logger logger = LoggerFactory.getLogger(HisStationController.class);
	IHisStation URL = (IHisStation) HessianFactoryUtil.getHessianObj(IHisStation.class);

	@Autowired
	private HisStationService hisStationService;

	// 显示所有数据
	@RequestMapping("/station_main.htm")
	public String findAll(HisStation hisStation, HttpServletRequest request, HttpServletResponse response) {
		return "/client/stationpage/station";
	}

	// 查询所有数据
	@RequestMapping(value = "/find_List.json")
	public void findSkipList(HisStation hisStation, String pwName, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {

		if (pwName != null && pwName != "") {
			hisStation.setGwName(pwName);
		}

		// List<HisStation> list = URL.pageToListJt(hisStation,pageNumber,pageSize);
		List<HisStation> list = hisStationService.page4List(hisStation, pageNumber, pageSize);
		// 返回数据以供多少
		// Integer count = URL.findCountJt(hisStation);
		Integer count = hisStationService.findCount(hisStation);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	// 删除活动项(更改status状态)
	/*
	 * @RequestMapping(value="/delete_station.htm") public void deleteStation(HisStation
	 * hisStation,HttpServletRequest request, HttpServletResponse response)throws Exception{
	 * Integer status = hisStation.getStatus(); if (status==1) { status=0; }
	 * hisStation.setStatus(status); int idVla = URL.modifyStatus(hisStation);
	 * response.getWriter().println(idVla); }
	 */
	/**
	 * 修改信息回显
	 * 
	 * @param hisStation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	// @RequestMapping(value="/modify_station.json")
	/*
	 * public void modifyStation(HisStation hisStation, HttpServletRequest request,
	 * HttpServletResponse response)throws Exception{ Integer id = hisStation.getId(); if
	 * (id!=null) { //根据选中的ID查询信息 hisStation = URL.findById(id); }
	 * request.setAttribute(Constants.jsonResultData,hisStation);
	 * request.setAttribute(Constants.jsonResultCode,0); }
	 */

	@RequestMapping(value = "/stationFyName.json")
	public void findStationName(HisStation hisStation, HttpServletRequest request, HttpServletResponse response) {
		HisStation entity = hisStationService.findById(hisStation.getId());
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().println(JSONUtils.toJSONString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 数据同步
	@Transactional
	public int JtDataTOFy() {
		// 查询集团所有
		List<HisStation> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisStationService.deleteStationInfo(null);

		if (listjt != null) {
			for (HisStation entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Integer idjt = entiyjt.getId();
//				// 用集团的id查分院
//				HisStation listfy = hisStationService.findById(idjt);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(idjt);
//					// 此处时间判断不兼容性高，带完善
//					if (modifyTimeFys != null && modifyTimeJts != null) {
//						if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//							num = hisStationService.updatAddStationFy(entiyjt);
//						}
//					}
//				} else {// 当ID不存在的时候插入当前数据
					num = hisStationService.addDepartFy(entiyjt);
					num = num/num;
//				}

			}
		}
		return num;
	}
}
