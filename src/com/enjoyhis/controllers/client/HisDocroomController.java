package com.enjoyhis.controllers.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisDocroom;
import com.enjoyhis.service.HisDocroomService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.JSONUtils;

/**
 * 
 * @Title: HisDocroomController
 * @Description: 诊室配置
 * @Version: （版本号）
 * @Create Date: （创建日期）
 */
@Controller
@RequestMapping(value = "/admin/hisdocroom")
public class HisDocroomController {

	final Logger logger = LoggerFactory.getLogger(HisDocroomController.class);

	@Autowired
	private HisDocroomService hisDocroomService;

	/**
	 * @Description:查询诊室 (功能)
	 * @param hisDocroom
	 * @param request
	 * @param respons
	 * @return "/admin/hisdocroom/roommainte"
	 */
	@RequestMapping(value = "/home.htm")
	public String roomtList(HisDocroom hisDocroom, String rName, String rIp, HttpServletRequest request, HttpServletResponse respons) {
		return "/admin/hisdocroom/roommainte";
	}

	// 处理分页
	@RequestMapping(value = "/find_docroom.json", method = RequestMethod.POST)
	public void findDocroom(HisDocroom hisDocroom, String rName, String rIp, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<HisDocroom> list = hisDocroomService.page4List(hisDocroom, rName, rIp, pageNumber, pageSize);
		// 返回数据以供多少
		Integer count = hisDocroomService.findCount(hisDocroom);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	/**
	 * @Description:增加诊室或修改
	 * @param hisDocroom
	 * @param request
	 * @param respons
	 * @return 无
	 */
	@RequestMapping(value = "/add.htm")
	public void add(HisDocroom hisDocroom, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = hisDocroom.getId();
		HisDocroom isno = hisDocroomService.findById(id);
		int num = 0;
		if (isno != null) {// 数据存在就更新，且修改createTime
			hisDocroom.setModifyTime(new Date());
			hisDocroom.setCreateTime(isno.getCreateTime());
			num = hisDocroomService.modifyRoomInformation(hisDocroom);
		} else {// 数据不存在就插入
			hisDocroom.setModifyTime(new Date());
			hisDocroom.setCreateTime(new Date());
			num = hisDocroomService.addRoom(hisDocroom);
		}

		response.getWriter().println(num);
	}

	/*
	 * 根据ID查询
	 */
	@RequestMapping(value = "/findEntity.json")
	public void findEntity(HisDocroom hisDocroom, HttpServletRequest request, HttpServletResponse response) {
		HisDocroom entity = hisDocroomService.findById(hisDocroom.getId());
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().println(JSONUtils.toJSONString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:删除诊室
	 * @param hisDocroom
	 * @param request
	 * @param respons
	 * @return 无
	 */
	@RequestMapping(value = "/delete_room.json")
	public void DeleteRoom(HisDocroom hisDocroom, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = hisDocroom.getId();
		int num = hisDocroomService.deleteSelective(id);
		response.getWriter().println(num);
	}

	/**
	 * @Description:诊室信息回显
	 * @param hisDocroom
	 * @param request
	 * @param respons
	 * @return 无，(如果使用@ResponseBody返回json，@return JSONUtils.toJSONString(findInfo))
	 */
	@RequestMapping(value = "/modify_room.json")
	public void modifyRoom(HisDocroom hisDocroom, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = hisDocroom.getId();
		HisDocroom findInfo = null;
		if (id != null) {
			findInfo = hisDocroomService.findById(id);
		}
		request.setAttribute(Constants.jsonResultData, findInfo);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	/**
	 * 校验IP、诊室
	 */
	@RequestMapping(value = "/check_room_ip.json")
	public void checkRoomIp(HisDocroom hisDocroom, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String roomIp = hisDocroom.getRoomIp();
		String roomNa = hisDocroom.getRoomName();
		int num = 2;
		if (roomNa != "" && roomNa != null) {
			// hisDocroom.setRoomName(roomNa);
			hisDocroom = hisDocroomService.findIpByInfo(hisDocroom);
			if (hisDocroom != null) {
				num = 1;
			} else {
				num = 3;
			}
		}
		if (roomIp != "" && roomIp != null) {
			// hisDocroom.setRoomIp(roomIp);
			hisDocroom = hisDocroomService.findIpByInfo(hisDocroom);
			if (hisDocroom != null) {
				num = 1;
			} else {
				num = 3;
			}
		}
		response.getWriter().println(num);
	}
}
