package com.enjoyhis.controllers.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.persistence.his.po.HisOrganiz;
import com.enjoyhis.service.HisOrganizService;
import com.enjoyhis.service.SysConfigService;

@Controller
@RequestMapping(value = "/client/homepange")
public class HisHome {

	@Autowired
	private HisOrganizService hisOrganizService;
	@Autowired
	private SysConfigService sysConfigService;

	@RequestMapping(value = "/homes.htm")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
		HisOrganiz hisOrganiz = hisOrganizService.findById(unitCode);
		request.getSession().setAttribute("title_name", hisOrganiz);
		return "/client/homepange/home";
	}
}
