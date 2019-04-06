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
import com.enjoyhis.persistence.his.po.HisOrganiz;
import com.enjoyhis.rmiclient.IHisOrganiz;
import com.enjoyhis.service.HisOrganizService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping(value = "/client/hisorganiz")
public class HisOrganizController {
	final Logger logger = LoggerFactory.getLogger(HisOrganizController.class);
	IHisOrganiz URL = (IHisOrganiz) HessianFactoryUtil.getHessianObj(IHisOrganiz.class);

	@Autowired
	private HisOrganizService hisOrganizService;

	@RequestMapping(value = "/find_UnitName.json")
	public void findByUnitName(HisOrganiz hisOrganiz, HttpServletRequest request, HttpServletResponse respons) {
		List<HisOrganiz> list = hisOrganizService.findOrganiz(hisOrganiz);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "/find_city.json")
	public void findByCity(HisOrganiz hisOrganiz, HttpServletRequest request, HttpServletResponse respons) {
		hisOrganiz = hisOrganizService.findById(hisOrganiz.getId());
		respons.setCharacterEncoding("UTF-8");
		try {
			respons.getWriter().println(JSONUtils.toJSONString(hisOrganiz));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/find_arr_city.json")
	public void findArrCity(HisOrganiz hisOrganiz, String da, HttpServletRequest request, HttpServletResponse respons) {

		if (da == null) {
			return;
		}
		String[] Arr = da.split(",");
		String uname = null;
		for (int i = 0; i < Arr.length; i++) {
			String s = Arr[i];
			if (i >= 1) {
				hisOrganiz = hisOrganizService.findById(Integer.parseInt(s));
				String uname2 = hisOrganiz.getCity();
				if (!uname.equals(uname2)) {
					break;
				}
			}
			hisOrganiz = hisOrganizService.findById(Integer.parseInt(s));
			uname = hisOrganiz.getCity();
		}
		hisOrganiz.setCity(uname);
		hisOrganiz = hisOrganizService.findByIdArr(hisOrganiz);
		request.setAttribute(Constants.jsonResultData, hisOrganiz);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "/findEntityInfo.json")
	public void findEntity(HisOrganiz hisOrganiz, HttpServletRequest request, HttpServletResponse respons) {
		HisOrganiz entity = hisOrganizService.findById(hisOrganiz.getId());
		if (entity != null) {
			request.setAttribute(Constants.jsonResultData, entity);
			request.setAttribute(Constants.jsonResultCode, 0);
		}
	}
	@RequestMapping("/organizJtToFy.json")
	public void organizJtToFy(){
		JtToFy();
	}
	
	@Transactional
	public int JtToFy() {
		List<HisOrganiz> listjt = null;
		try {
			listjt = URL.findOrganizAll(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		hisOrganizService.deleteHisOrganiz(new HisOrganiz());
		if (listjt != null) {
			for (HisOrganiz entiyjt : listjt) {
				// Date modifyTimeJts = entiyjt.getModifyTime();
				// Integer id = entiyjt.getId();
				// // 用集团的id查分院
				// HisOrganiz listfy = hisOrganizService.findById(id);
				// if (listfy != null) {
				// Date modifyTimeFys = listfy.getModifyTime();
				// entiyjt.setId(id);
				// // 此处时间判断不兼容性高，带完善
				// if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) ||
				// (modifyTimeFys.compareTo(modifyTimeJts) < 0) ||
				// (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
				// entiyjt.setCreateTime(entiyjt.getCreateTime());
				// entiyjt.setModifyTime(new Date());
				// num = hisOrganizService.updateDictInfo(entiyjt);
				// }
				// } else {// 当ID不存在的时候插入当前数据
				// entiyjt.setModifyTime(new Date());
				// entiyjt.setCreateTime(new Date());
				num = hisOrganizService.addDictInfo(entiyjt);
				num = num / num;
			}
		}
		return num;
	}

	// 联动二次请求数据处理
	@RequestMapping(value = "menu_select.json")
	public void menuSelect(HisOrganiz hisOrganiz, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HisOrganiz> list = hisOrganizService.findByIds(hisOrganiz);
		request.setAttribute(Constants.jsonResultData, list);
		request.setAttribute(Constants.jsonResultCode, 0);
	}
}
