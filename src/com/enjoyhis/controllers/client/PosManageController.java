package com.enjoyhis.controllers.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.HisPosDetails;
import com.enjoyhis.service.PosDetailsService;
import com.enjoyhis.util.Constants;

@Controller
@RequestMapping(value = "pos")
public class PosManageController {

	@Autowired
	private PosDetailsService posDetailsService;
	
	
	@RequestMapping(value = "gotoPage.htm")
	public String gotoPage(Model model, HttpServletRequest request, HttpServletResponse response, Long id) {
		return "client/pos/PosManage";
	}

	@RequestMapping(value = "getList.json")
	public void getList(HttpServletRequest request, HttpServletResponse response,String posName ,String bankName, Integer isLater,Integer pageSize, Integer pageNum){
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(posDetailsService.getList(posName, bankName, isLater, pageSize, pageNum));
		page.setTotal(posDetailsService.getListCount(posName, bankName, isLater));
		request.setAttribute(Constants.pageResultData, page);
	}
	
	@RequestMapping(value = "savePos.json")
	public void savePos(HttpServletRequest request, HttpServletResponse response,HisPosDetails hpd){
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setSuccess(posDetailsService.savePos(hpd));
		request.setAttribute(Constants.pageResultData, page);
	}

	@RequestMapping(value = "updatePos.json")
	public void updatePos(HttpServletRequest request, HttpServletResponse response,Long id,Integer status){
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setSuccess(posDetailsService.updatePos(id, status));
		request.setAttribute(Constants.pageResultData, page);
	}
}