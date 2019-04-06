package com.enjoyhis.controllers.client.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.util.DateUtils;

@Controller
public class SystemController {

	@RequestMapping("client/system/getSystemDate.json")
	public void getSystemDate(HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			writer.print(DateUtils.getDataString(DateUtils.date_sdf));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
