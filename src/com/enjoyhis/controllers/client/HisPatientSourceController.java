package com.enjoyhis.controllers.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisDict;
import com.enjoyhis.persistence.his.po.HisPatientCase;
import com.enjoyhis.service.HisDictService;
import com.enjoyhis.service.HisPatientSourceService;
import com.enjoyhis.util.Constants;

/**
 * 
 * @author
 * @Description: 患者来源
 */
@Controller
@RequestMapping(value = "/client/patientSource")
public class HisPatientSourceController {

	@Autowired
	private HisPatientSourceService hisPatientSourceService;
	@Autowired
	private HisDictService hisDictService;

	final Logger logger = LoggerFactory.getLogger(HisPatientSourceController.class);

	@RequestMapping("/findAll.htm")
	public String findAllPatien(HisPatientCase hisPatientCase, HttpServletRequest request, HttpServletResponse respons) {
		hisPatientSourceService.findAll(hisPatientCase);
		return "/client/patientSource/patientsource";
	}

	@RequestMapping(value = "/findAllRow.json", method = RequestMethod.POST)
	public void findDocroom(HisDict hisDict, Integer pageNumber, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		List<HisDict> list = hisDictService.page4List(hisDict, pageNumber, pageSize);
		Integer count = hisDictService.findCount(hisDict);
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setTotal(count);
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);

	}
}
