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
import org.springframework.web.bind.annotation.RequestMethod;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisPrepayact;
import com.enjoyhis.rmiclient.IHisPrepayact;
import com.enjoyhis.service.HisPrepayactService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

/**
 * 
 * @Name: 充值活动
 * @Description: 功能
 * @Author: 作者
 * @Version:（版本号）
 * @Create Date: 创建日期
 */
@Controller
@RequestMapping("/client/prepayact")
public class HisPrepayactController {
	final Logger logger = LoggerFactory.getLogger(HisPrepayactController.class);
	IHisPrepayact URL = (IHisPrepayact) HessianFactoryUtil.getHessianObj(IHisPrepayact.class);
	@Autowired
	private HisPrepayactService hisPrepayactService;

	@RequestMapping(value = "/findactivityall.htm")
	public String findActivityAll(HisPrepayact hisPrepayact, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		return "/client/prepayact/rechargeactivity";
	}

	@RequestMapping(value = "/find_recharges.json", method = RequestMethod.POST)
	public void findActivity(HisPrepayact hisPrepayact, String aname, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		if(pageNumber==null){
			pageNumber = 1;
		}
		if(pageSize ==null){
			pageSize = 5;
		}
		// 从页面获取pageNumber,pageSize这两个参数
		List<HisPrepayact> list = hisPrepayactService.page4List(hisPrepayact, aname, pageNumber, pageSize);
		// 返回数据以供多少
		Integer count = hisPrepayactService.findCount(hisPrepayact);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);
	}

	// 数据同步
	@Transactional
	public int JtTOFy() {
		List<HisPrepayact> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisPrepayactService.deletePrepayactAll(null);
		if (listjt != null) {
			for (HisPrepayact entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Integer idjt = entiyjt.getId();
//				// 用集团的id查分院
//				HisPrepayact listfy = hisPrepayactService.findById(idjt);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(idjt);
//					// 此处时间判断不兼容性高，带完善
//					if (modifyTimeFys != null && modifyTimeJts != null) {
//						if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//							num = hisPrepayactService.UpdateaddPrepayactFy(entiyjt);
//						}
//					}
//				} else {// 当ID不存在的时候插入当前数据
					num = hisPrepayactService.addPrepayactFy(entiyjt);
					num = num/num;
//				}
			}
		}
		return num;
	}
}
