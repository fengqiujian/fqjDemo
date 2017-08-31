package com.enjoyhis.controllers.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.service.PorcessService;

@Controller
@RequestMapping(value = "client/process")
public class ProcessController {

	@Autowired
	private PorcessService porcessService;
	
	
	@RequestMapping(value = "gotoPage.htm")
	public String gotoPage(Model model, HttpServletRequest request, HttpServletResponse response, Long id) {
		return "/client/datasynchronization/unClocklist";
	}

	public int Upadte(){
		return porcessService.update();
	}
}