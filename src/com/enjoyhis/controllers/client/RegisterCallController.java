package com.enjoyhis.controllers.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.enjoyhis.pojo.RegisterCall;
import com.enjoyhis.service.HisRegisterCallService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HttpClientLocalUtils;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.PropertiesUtil;

/**
 * 叫号controller
 */
@Controller
public class RegisterCallController {

	@Autowired
	private HisRegisterCallService hisRegisterCallService;// 叫号service

	private String callNoIp = PropertiesUtil.getProp("config/config.properties", "call_no_ip");

	@RequestMapping(value = "/client/registerCall/call.json", method = RequestMethod.POST)
	public void call(String calltype, Integer type, Long registerId, HttpServletRequest request, HttpServletResponse response) {
		try {
			String returnData = "";
			// 获取签名
			String url = "http://" + callNoIp + "/api/his/v1/helper/sign/verify";
			RegisterCall registerCall = this.getCallParams(calltype, type, registerId);

			JSONObject jsonObject = getVerify(registerCall, url, type);

			if (type == 0) { // 排号
				returnData = insertNo(registerCall, jsonObject);
			} else if (type == 1) { // 叫号
				returnData = callNo(registerCall, jsonObject);
			} else if (type == 2) { // 重呼
				returnData = recallNo(registerCall, jsonObject);
			} else if (type == 3) { // 过号
				returnData = callNo(registerCall, jsonObject);
			} else if (type == 4) { // 重排
				// 取消
				calcelNo(registerCall, jsonObject);

				// 叫号
				RegisterCall registerCall2 = this.getCallParams("1", 1, registerId);
				JSONObject jsonObject2 = getVerify(registerCall2, url, 1);
				returnData = callNo(registerCall2, jsonObject2);

				// 重新排号（本地与接口都要添加）
				hisRegisterCallService.save(Long.valueOf(registerCall.getUniqid()), DateUtils.getNow());
				RegisterCall registerCall3 = this.getCallParams("1", 0, registerId);
				JSONObject jsonObject3 = getVerify(registerCall3, url, 0);
				returnData = insertNo(registerCall3, jsonObject3);
			} else if (type == 5) { // 取消排号
				calcelNo(registerCall, jsonObject);
			}
			request.setAttribute(Constants.jsonResultData, JSONUtils.toJSONObject(returnData));
		} catch (Exception e) {

		}
	}

	// 获取签名
	public JSONObject getVerify(RegisterCall registerCall, String url, Integer type) {
		try {
			Map<String, Object> param = new HashMap<>();
			if (type == 0) {
				param.put("str", registerCall.getUniqid() + registerCall.getDepartments() + registerCall.getRoom() + registerCall.getDoctor() + registerCall.getTitle() + registerCall.getAvatar() + registerCall.getPatient() + registerCall.getPat_sex() + registerCall.getPat_age() + registerCall.getNumber());
			} else {
				param.put("str", registerCall.getUniqid());
			}
			return JSONUtils.toJSONObject(HttpClientLocalUtils.getTelnetData(new HttpPost(), url, param, null));
		} catch (Exception e) {
		}
		return null;
	}

	// 获取叫号参数
	public RegisterCall getCallParams(String calltype, Integer type, Long registerId) {
		try {
			RegisterCall registerCall = hisRegisterCallService.hisgetCallParams(Integer.parseInt(calltype), type, registerId);
			return registerCall;
		} catch (Exception e) {
		}
		return null;
	}

	// 排号
	private String insertNo(RegisterCall registerCall, JSONObject jsonObject) {
		try {
			String url = "http://" + callNoIp + "/api/his/v1/increase";
			Map<String, Object> param2 = new HashMap<>();
			param2.put("uniqid", registerCall.getUniqid());
			param2.put("departments", registerCall.getDepartments());
			param2.put("room", registerCall.getRoom());
			param2.put("doctor", registerCall.getDoctor());
			param2.put("title", registerCall.getTitle());
			param2.put("avatar", registerCall.getAvatar());
			param2.put("patient", registerCall.getPatient());
			param2.put("pat_sex", registerCall.getPat_sex());
			param2.put("pat_age", registerCall.getPat_age());
			param2.put("number", registerCall.getNumber());
			param2.put("sign", jsonObject.get("md5"));
			String result2 = HttpClientLocalUtils.getTelnetData(new HttpPost(), url, param2, null);
			return result2;
		} catch (Exception e) {
		}
		return null;
	}

	// 叫号
	private String callNo(RegisterCall registerCall, JSONObject jsonObject) {
		try {
			String url = "http://" + callNoIp + "/api/his/v1/call";
			Map<String, Object> param2 = new HashMap<>();
			param2.put("uniqid", registerCall.getUniqid());
			param2.put("sign", jsonObject.get("md5"));
			String result2 = HttpClientLocalUtils.getTelnetData(new HttpPost(), url, param2, null);
			return result2;
		} catch (Exception e) {
		}
		return null;
	}

	// 重呼
	private String recallNo(RegisterCall registerCall, JSONObject jsonObject) {
		try {
			String url = "http://" + callNoIp + "/api/his/v1/recall";
			Map<String, Object> param2 = new HashMap<>();
			param2.put("uniqid", registerCall.getUniqid());
			param2.put("sign", jsonObject.get("md5"));
			String result2 = HttpClientLocalUtils.getTelnetData(new HttpPost(), url, param2, null);
			return result2;
		} catch (Exception e) {
		}
		return null;
	}

	// 过号
	private String calcelNo(RegisterCall registerCall, JSONObject jsonObject) {
		try {
			String url;
			url = "http://" + callNoIp + "/api/his/v1/cancel";
			Map<String, Object> param2 = new HashMap<>();
			param2.put("uniqid", registerCall.getUniqid());
			param2.put("sign", jsonObject.get("md5"));
			String result2 = HttpClientLocalUtils.getTelnetData(new HttpPost(), url, param2, null);
			return result2;
		} catch (Exception e) {
		}
		return null;
	}
}
