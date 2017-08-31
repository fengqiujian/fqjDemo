package com.enjoyhis.controllers.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.persistence.his.po.AdminRole;
import com.enjoyhis.service.AdminRoleService;
import com.enjoyhis.util.JSONUtils;

@Controller
@RequestMapping("/client/adminrole")
public class AdminRoleController {
	@Autowired
	private AdminRoleService adminRoleService;

	@RequestMapping(value = "/findAdminRoleInfo.json")
	public void findEntity(AdminRole adminRole, HttpServletRequest request, HttpServletResponse respons) {
		adminRole = adminRoleService.findById(adminRole.getId());
		respons.setCharacterEncoding("UTF-8");
		try {
			respons.getWriter().println(JSONUtils.toJSONString(adminRole));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
