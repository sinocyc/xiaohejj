package com.xhtutor.tutor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Admin;
import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value="/admin/adminLoginByUserName")
	public Msg tutorLoginByUserName(@RequestParam("userName") String userName, @RequestParam("password") String password, 
			HttpServletResponse response) {
		if(null != userName && !"".equals(userName) && null != password && !"".equals(password)) {
			Admin admin = adminService.getAdminByUserName(userName);
			if(null != admin) {
				if(password.equals(admin.getPassword())) {
					// 更新最后登录时间
					Integer tutorId = admin.getId();
					
					Cookie loginCookie = new Cookie("loginAdminId", String.valueOf(admin.getId()));
					loginCookie.setPath("/");
					loginCookie.setMaxAge(-1);
					response.addCookie(loginCookie);
					return Msg.success().add("message", "登录成功!");
				}
			}
		}
		return Msg.fail().add("message", "登录失败!");
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/isAdminLogin")
	public Msg isTutorLogin(@CookieValue(value = "loginAdminId", required = false) Integer loginAdminId) {
		if(null != loginAdminId) {
			return Msg.success().add("message", "已登录！");
		}
		return Msg.fail().add("message", "未登录！");
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/adminLogout")
	public Msg tutorLogout(HttpServletResponse response) {
		Cookie loginCookie = new Cookie("loginAdminId", "");
		loginCookie.setPath("/");
		loginCookie.setMaxAge(0);
		response.addCookie(loginCookie);
		return Msg.success().add("message", "退出登录成功！");
	}

}
