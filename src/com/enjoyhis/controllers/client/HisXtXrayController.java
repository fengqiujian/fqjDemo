package com.enjoyhis.controllers.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.po.HisXtXray;
import com.enjoyhis.rmiclient.IHisXtXray;
import com.enjoyhis.service.HisXtXrayService;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping(value = "/client/hisxtxray")
public class HisXtXrayController {
	final Logger logger = LoggerFactory.getLogger(HisXtXrayController.class);
	IHisXtXray URL = (IHisXtXray) HessianFactoryUtil.getHessianObj(IHisXtXray.class);

	@Autowired
	private HisXtXrayService hisXtXrayService;

	@RequestMapping("/synch.htm")
	public int JtToFy() {

		List<HisXtXray> listjt = null;
		try {
			listjt = URL.findXtXrayAll(null);
		} catch (HessianRuntimeException e) {
			LogUtils.CLIENT_TRACE.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		if (listjt != null) {
			for (HisXtXray entiyjt : listjt) {
				Integer idjt = entiyjt.getId();
				// 用集团的id查分院
				HisXtXray listfy = hisXtXrayService.findById(idjt);
				if (listfy != null) {
					entiyjt.setId(idjt);
					// 此处时间判断不兼容性高，带完善
					// if ((modifyTimeFys.compareTo(modifyTimeJts) ==
					// 0)||(modifyTimeFys.compareTo(modifyTimeJts) <
					// 0)||(modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
					num = hisXtXrayService.updateDictInfo(entiyjt);
					// }
				} else {// 当ID不存在的时候插入当前数据
					num = hisXtXrayService.addDictInfo(entiyjt);
				}

			}
		}
		return num;
	}
}
