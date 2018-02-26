package com.xhtutor.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.bean.Province;
import com.xhtutor.tutor.service.ProvinceService;

@Controller
public class ProvinceController {

	@Autowired
	ProvinceService provinceService;
	
	@ResponseBody
	@RequestMapping(value="/province/getAll")
	public Msg getgetAllProv() {
		List<Province> provList = provinceService.getAllProvice();
		return Msg.success().add("provList", provList);
	}
	
}
