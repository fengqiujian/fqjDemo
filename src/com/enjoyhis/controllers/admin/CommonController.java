package com.enjoyhis.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping("/common/500")
	public String exception500() {
		return "common/500";
	}

	@RequestMapping("/common/404")
	public String exception404() {
		return "common/404";
	}

	@RequestMapping("/common/403")
	public String exception403() {
		return "common/403";
	}
	
	@RequestMapping("/common/400")
	public String exception400() {
		return "common/400";
	}
	
	@RequestMapping("/common/exception")
	public String exceptionTest() {
		throw new RuntimeException();
	}

}
