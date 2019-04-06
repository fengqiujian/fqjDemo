package com.enjoyhis.controllers.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.po.HisPayment;
import com.enjoyhis.rmiclient.IHisPayment;
import com.enjoyhis.service.HisPaymentService;
import com.enjoyhis.util.HessianFactoryUtil;

@Controller()
@RequestMapping(value = "/client/hispayment")
public class HisPaymentController {
	final Logger logger = LoggerFactory.getLogger(HisOrganizController.class);
	IHisPayment URL = (IHisPayment) HessianFactoryUtil.getHessianObj(IHisPayment.class);

	@Autowired
	private HisPaymentService hisPaymentService;

	@Transactional
	public int JtToFy() {

		List<HisPayment> listjt = null;
		try {
			listjt = URL.findPaymentAll(null);
		} catch (HessianRuntimeException e) {
			e.printStackTrace();
			return 0;
		}
		int num = 0;
		hisPaymentService.deleteHisPayment(new HisPayment());
		if (listjt != null) {
			for (HisPayment entiyjt : listjt) {
//				Date modifyTimeJts = entiyjt.getModifyTime();
//				Integer id = entiyjt.getId();
//				// 用集团的id查分院
//				HisPayment listfy = hisPaymentService.findById(id);
//				if (listfy != null) {
//					Date modifyTimeFys = listfy.getModifyTime();
//					entiyjt.setId(id);
//					// 此处时间判断不兼容性高，带完善
//					if ((modifyTimeFys.compareTo(modifyTimeJts) == 0) || (modifyTimeFys.compareTo(modifyTimeJts) < 0) || (modifyTimeFys.compareTo(modifyTimeJts) > 0)) {
//						entiyjt.setModifyTime(new Date());
//						entiyjt.setCreateTime(entiyjt.getCreateTime());
//						num = hisPaymentService.updatePaymentInfo(entiyjt);
//					}
//				} else {// 当ID不存在的时候插入当前数据
//					entiyjt.setModifyTime(new Date());
//					entiyjt.setCreateTime(new Date());
					num = hisPaymentService.addPaymentInfo(entiyjt);
					num = num/num;
//				}

			}
		}
		return num;
	}
}
