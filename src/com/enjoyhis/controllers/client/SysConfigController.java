package com.enjoyhis.controllers.client;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.po.SysConfig;
import com.enjoyhis.rmiclient.ISysConfigController;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.LogUtils;

@Controller
@RequestMapping(value = "/client/configmanager")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	ISysConfigController URL = (ISysConfigController) HessianFactoryUtil.getHessianObj(ISysConfigController.class);
	Logger log = LogUtils.CLIENT_TRACE;

	// 数据同步
	@Transactional
	public int JtSysConfigTOFy() {
		List<SysConfig> listjt = null;
		try {
			listjt = URL.findAllJt(null);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
			return 0;
		}
		int num = 0;
		if (listjt != null) {
			for (SysConfig entiyjt : listjt) {
				Integer idjt = entiyjt.getId();
				if (idjt < 7) {
					continue;
				}
				// 用集团的id查分院
				SysConfig listfy = sysConfigService.findByIdInfo(idjt);
				if (listfy != null) {
					// 此处时间判断不兼容性高，带完善
					num = sysConfigService.updateSysConfigToFy(entiyjt);
				} else {// 当ID不存在的时候插入当前数据
					num = sysConfigService.addSysConfigFy(entiyjt);
				}
			}
		}
		return num;
	}
}
