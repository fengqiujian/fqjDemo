package com.enjoyhis.controllers.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.po.HisStatement;
import com.enjoyhis.persistence.his.po.HisStatementItem;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.JSONUtils;

@Controller
@RequestMapping(value = "client/statement")
public class HisStatementController {
	@Autowired
	private HisStatementChargeDao hisStatementChargeDao;

	/**
	 * 还欠款单据重印的接口
	 * 
	 * @param hisStatement
	 * @param type 1挂号， 2处置收费， 3收欠费， 4结算单调整， 9预存， 10退预存， 11预存转账， 12处置单调整， 13会计退费
	 * @param request
	 * @param response
	 * 测试地址http://localhost:8080//enjoyhisfy/client/statement/getHisStatement.json?id=id&types=
	 * types
	 */
	@RequestMapping(value = "/getHisStatement.json")
	public void getHisStament(HisStatement hisStatement, HttpServletRequest request, HttpServletResponse response, String data) {
		long id = hisStatement.getId();
		Integer types = getJson(data).getInteger("types");
		HisStatementItem hisStatementItem = new HisStatementItem();
		if (types == 3) {// 收欠费
			hisStatementChargeDao.selectByPrimaryKey(id);
		} else if (types == 9) {// 预充值
			hisStatementChargeDao.selectByPrimaryKey(id);
		} else if (types == 10) {// 退预存
			hisStatementChargeDao.selectByPrimaryKey(id);
		} else if (types == 11) {// 预充值转存？
			hisStatementChargeDao.selectByPrimaryKey(id);
		}
		request.setAttribute(Constants.jsonResultData, hisStatementItem);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	private JSONObject getJson(String data) {
		data = data.replaceAll("#", "&");
		return JSONUtils.toJSONObject(data);
	}
}