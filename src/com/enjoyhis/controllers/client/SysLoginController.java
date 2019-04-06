package com.enjoyhis.controllers.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.enjoyhis.controllers.json.PageResultForBootstrap;
import com.enjoyhis.persistence.his.po.AdminMenu;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisOrganiz;
import com.enjoyhis.pojo.MenuPojo;
import com.enjoyhis.service.AdminMenuService;
import com.enjoyhis.service.HisEmployeeService;
import com.enjoyhis.service.HisOrganizService;
import com.enjoyhis.service.SysConfigService;
import com.enjoyhis.util.Constants;
import com.enjoyhis.util.CookieUtil;
import com.enjoyhis.util.JSONUtils;
import com.enjoyhis.util.SysUtil;

@Controller
public class SysLoginController {

	@Autowired
	private HisEmployeeService hisEmployeeService;
	@Autowired
	private AdminMenuService adminMenuService;
	@Autowired
	private HisOrganizService hisOrganizService;
	@Autowired
	private SysConfigService sysConfigService;

	@RequestMapping(value = "login/login.json")
	public void login(HttpServletRequest request, HttpServletResponse response, String mobile, String password, String roomIp) {
		HisEmployee he = new HisEmployee();
		he.setMobile(mobile);
		he.setPassword(password);
		he.setIsShow(1);
		he.setStatus(1);
		he = hisEmployeeService.login(he, roomIp);

		if (he != null) {
			Cookie cookie = new Cookie("ID", he.getId().toString());
			cookie.setMaxAge(60 * 60 * 24);// 过期时间为24小时
			cookie.setPath("/");
			response.addCookie(cookie);
			Cookie cookies = new Cookie("COOKIEID", he.getCookie());
			cookies.setMaxAge(60 * 60 * 24);// 过期时间为24小时
			cookies.setPath("/");
			response.addCookie(cookies);
			request.setAttribute(Constants.jsonResultData, he);
		} else {
			request.setAttribute(Constants.jsonResultCode, -7);
		}
	}

	@RequestMapping(value = "login/loginOut.json")
	public void loginOut(HttpServletRequest request, HttpServletResponse response, Long id) {
		hisEmployeeService.loginOut(id);
		Cookie cookie = new Cookie("ID", null);
		cookie.setMaxAge(0);// 过期时间为24小时
		cookie.setPath("/");
		response.addCookie(cookie);
		Cookie cookies = new Cookie("COOKIEID", null);
		cookies.setMaxAge(0);// 过期时间为24小时
		cookies.setPath("/");
		response.addCookie(cookies);
		request.setAttribute(Constants.jsonResultCode, 0);
	}

	@RequestMapping(value = "login/main.htm")
	public String mainPage(HttpServletRequest request, HttpServletResponse response, String mobile, String password) throws Exception {
		HisEmployee he = new HisEmployee();
		he.setMobile(mobile);
		he.setPassword(password);
		he = hisEmployeeService.login(he);

		if (he != null) {
			Cookie cookie = new Cookie("ID", he.getId().toString());
			cookie.setMaxAge(60 * 60 * 24);// 过期时间为24小时
			cookie.setPath("/");
			response.addCookie(cookie);
			Cookie cookies = new Cookie("COOKIEID", he.getCookie());
			cookies.setMaxAge(60 * 60 * 24);// 过期时间为24小时
			cookies.setPath("/");
			response.addCookie(cookies);
			return "redirect:main";
		} else {
			return "admin/login";
		}
	}

	// 校验用户名和密码
	@RequestMapping(value = "login/check_userName_pass.json")
	public void checkUserNamePass(HttpServletRequest request, HttpServletResponse response, String mobile, String password) throws Exception {
		HisEmployee hisEmployee = new HisEmployee();

		HisEmployee hisEmployeeUser = hisEmployeeService.loginUser(hisEmployee, mobile);
		int num = 1;
		/**
		 * 1.用户和密码都正常 2.用名不正确 3.密码不正确
		 */

		if (hisEmployeeUser == null) {
			// 用户不正确.2
			num = 2;
			// response.getWriter().println(num);
		} else if (!hisEmployeeUser.getPassword().equals(password)) {
			// 密码不正确。3
			num = 3;
		} else if(hisEmployeeUser.getRoleId()==null){
			num = 4;
		} else if(hisEmployeeUser.getStatus()==0){
			num = 5;
		}else {
			Map<String, String> sysConfig = sysConfigService.getSysConfig();
			Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
			// System.out.println("----院区代码-----"+hisEmployeeUser.getUnitCode());
			request.getSession().setAttribute("login_unit_code", unitCode);
		}
		response.getWriter().println(num);
	}

	@RequestMapping(value = "login/main")
	public String mainIndex(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		if (cookie == null) {
			return "admin/login";
		}
		HisEmployee he = getHisEmployee(Long.parseLong(cookie.getValue()));
		if (he == null) {
			return "admin/login";
		} else {
			Map<String, String> sysConfig = sysConfigService.getSysConfig();
			Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));
			HisOrganiz hisOrganiz = hisOrganizService.findById(unitCode);
			request.getSession().setAttribute("login_name", he);
			request.getSession().setAttribute("title_name", hisOrganiz);
			return "admin/manager";
		}
	}

	@RequestMapping(value = "admin/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "admin/login";
	}

	/**
	 * 
	 * @Name: logout
	 * @Description: 退出(登出)
	 * @Author: sunxiaoya
	 * @Version:（版本号）
	 * @Create Date: 2016/09/02
	 * @Parameters: 参数
	 * @Return: "admin/login"
	 */
	@RequestMapping(value = "login/log_out.htm")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("login_name");// 针对某个session进行清空
		// request.getSession().invalidate();//清空所有的session
		return "admin/login";
	}

	@RequestMapping(value = "login/getMenu.json")
	public void getMenu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		Cookie cookie = (Cookie) cookieMap.get("ID");
		HisEmployee he = getHisEmployee(Long.parseLong(cookie.getValue()));
		if (he.getRoleId() == null) {
			return;
		}
		AdminMenu am = new AdminMenu();
		am.setParentId(0);
		am.setSqlStr(" and id in (SELECT menu_id FROM admin_role_menu_mapping WHERE role_id = " + he.getRoleId() + ")");
		List<AdminMenu> listMenu = adminMenuService.getAdminMenuList(am);
		List<MenuPojo> list = getMenuPojo(listMenu, "menu-icon fa fa-desktop", 0);
		for (int i = 0; i < list.size(); i++) {
			MenuPojo mp = list.get(i);
			am = new AdminMenu();
			am.setParentId(mp.getId());
			List<AdminMenu> listMenus = adminMenuService.getAdminMenuList(am);
			List<MenuPojo> lists = getMenuPojo(listMenus, "menu-icon fa fa-glass", i);
			mp.setMenus(lists);
		}
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(list);
		request.setAttribute(Constants.pageResultData, page);

	}

	@RequestMapping(value = "login/changePassword.json")
	public void changePassword(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jo = getJson(request);
		String oldPassword = (String) jo.get("oldPassword");
		String newPassword = (String) jo.get("newPassword");
		Cookie cookie = CookieUtil.getCookieByName(request, "ID");
		if (cookie == null) {
			request.setAttribute(Constants.jsonResultCode, -1);
			return;
		}
		HisEmployee he = getHisEmployee(Long.parseLong(cookie.getValue()));
		if (he == null) {
			request.setAttribute(Constants.jsonResultCode, -1);
			return;
		}
		if (!he.getPassword().equals(oldPassword)) {
			request.setAttribute(Constants.jsonResultCode, -3);
		} else {
			HisEmployee hes = new HisEmployee();
			hes.setId(he.getId());
			hes.setPassword(newPassword);
			hisEmployeeService.updateHisEmployeeOne2(hes);
			request.setAttribute(Constants.jsonResultCode, 0);
		}
	}

	@RequestMapping(value = "login/getOrganizPojo.json")
	public void getOrganizPojo(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(Constants.jsonResultData, hisEmployeeService.getOrganizPojo());
	}

	@RequestMapping(value = "login/getOrganizList.json")
	public void getOrganizList(HttpServletRequest request, HttpServletResponse response) {
		PageResultForBootstrap page = new PageResultForBootstrap();
		page.setRows(hisEmployeeService.getList(new HisEmployee()));
		request.setAttribute(Constants.pageResultData, page);
	}

	private HisEmployee getHisEmployee(Long id) {
		return hisEmployeeService.getHisEmployeeOne(id);
	}

	private List<MenuPojo> getMenuPojo(List<AdminMenu> ams, String icon, int isOpen) {
		List<MenuPojo> mps = new ArrayList<>();
		MenuPojo mp;
		int i = 0;
		for (AdminMenu am : ams) {
			mp = new MenuPojo();
			if (i == 0 && isOpen == 0) {
				mp.setIsOpen(true);
			}
			mp.setId(am.getId());
			mp.setText(am.getMenuName());
			mp.setUrl(am.getMenuUrl());
			mp.setIcon(icon);
			i++;
			mps.add(mp);
		}
		return mps;
	}

	private JSONObject getJson(HttpServletRequest request) {
		String reqStr = "";
		try {
			reqStr = SysUtil.getInputStr(request.getInputStream(), request.getContentLength());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSONUtils.toJSONObject(reqStr);
	}

}
