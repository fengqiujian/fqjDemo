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
import com.enjoyhis.persistence.his.po.HisDepart;
import com.enjoyhis.persistence.his.po.HisGroup;
import com.enjoyhis.rmiclient.IHisGroup;
import com.enjoyhis.service.HisGroupService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

/**
 * 
 * @Name: HisGroupController
 * @Description: 分组
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 * @Parameters: 参数
 */
@Controller
@RequestMapping(value = "/client/groupmanager")
public class HisGroupController {
	
	
	final Logger logger = LoggerFactory.getLogger(HisGroupController.class);
	IHisGroup URL = (IHisGroup) HessianFactoryUtil.getHessianObj(IHisGroup.class);
	@Autowired
	private HisGroupService hisGroupService;
	@Autowired 
	private SysConfigService sysConfigService;
	@Autowired
	private HisGroupEmployeeController hisGroupEmployeeController;

	// 显示所有数据
	@RequestMapping(value = "/groupview_main.htm")
	public String findAll(HisDepart hisDepart, HttpServletRequest request, HttpServletResponse response) {
		return "/client/groupmanager/groupview";
	}

	// 查询所有数据
	@RequestMapping(value = "/find_List.json")
	public void findSkipList(HisGroup hisGroup, String gName, Integer uId, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		int unit = Integer.parseInt(sysConfigService.getSysConfig().get("local_unit"));
		List<HisGroup> list = hisGroupService.page4List(hisGroup, gName, uId, pageNumber, pageSize,unit);
		Integer count = hisGroupService.findCount(hisGroup, gName, uId, unit);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	@Transactional
	public int JtToFy() {
		List<HisGroup> listjt = null;
		try {
			listjt = URL.findGroupAll(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisGroupService.deleteGroupInfo(null);
		if (listjt != null) {
			num =hisGroupEmployeeController.JtToFy();
			num = num/num;
			for (HisGroup entiyjt : listjt) {
				
				if(entiyjt.getStatus()==0){
					continue;
				}
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Long id = entiyjt.getId();
//				// 用集团的id查分院
//				HisGroup listfy = hisGroupService.findById(id);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(id);
//					// 此处时间判断不兼容性高，带完善
//					if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//						entiyjt.setModifyTime(new Date());
//						entiyjt.setCreateTime(entiyjt.getCreateTime());
//						num = hisGroupService.updateGroupInfo(entiyjt);
//					}
//				} else {// 当ID不存在的时候插入当前数据
//					entiyjt.setModifyTime(new Date());
//					entiyjt.setCreateTime(new Date());
					num = hisGroupService.addGroupInfo(entiyjt);
					num = num/num;
//				}
			}
		}
		return num;
	}
	/**
	 * 显示组员
	 * @param request
	 * @param respons
	 * @param groupId
	 */
	@RequestMapping(value = "/showGroupPersonnel.json")
	public void showGroupPersonnel(HttpServletRequest request, HttpServletResponse respons,Long groupId) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisGroupService.showGroupPersonnel(groupId));
		request.setAttribute(Constants.pageResultData, page);
	}
	
	@RequestMapping(value = "/save.json")
	public void save(HttpServletRequest request, HttpServletResponse respons,Long groupId,String names,Integer isdel) {

		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setSuccess(hisGroupService.saveGroupPersonnel(groupId, names, isdel));
		request.setAttribute(Constants.pageResultData, page);
	}
	
	
	@RequestMapping(value = "/findEntityInfo.json")
	public void findEntity(HisGroup hisGroup, HttpServletRequest request, HttpServletResponse respons) {
		hisGroup = hisGroupService.findById(hisGroup.getId());
		request.setAttribute(Constants.jsonResultData, hisGroup);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "/findEntityInfos.json")
	public void findEntitys(HisGroup hisGroup, HttpServletRequest request, HttpServletResponse respons) {
		List<HisGroup> list = hisGroupService.findGroupInfoAll(hisGroup);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}
}
