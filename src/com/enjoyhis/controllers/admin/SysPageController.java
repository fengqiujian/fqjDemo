package com.enjoyhis.controllers.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class SysPageController {
	final Logger logger = LoggerFactory.getLogger(SysPageController.class);

	public static final String Constant = "V2.1.15";

	@RequestMapping(value = "login.htm")
	public String goToIndexLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "admin/login";
	}

	@RequestMapping(value = "getConstant")
	public void getConstant(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println(Constant);
	}
}
