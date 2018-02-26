package com.xhtutor.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.District;
import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.service.DistrictService;

@Controller
public class DistrictController {

	@Autowired
	DistrictService districtService;
	
	@ResponseBody
	@RequestMapping(value="/district/getDistrsByCity")
	public Msg getDistrsByCity(@RequestParam("cityId") Integer cityId) {
		List<District> distrList = districtService.getDistrsByCityId(cityId);
		return Msg.success().add("distrList", distrList);
	}
	
	@ResponseBody
	@RequestMapping(value="/district/getDistrDetailById")
	public Msg getDistrDetailById(@RequestParam("distrId") Integer distrId) {
		District district = null;
		if(null != distrId) {
			district = districtService.getDistrDetailById(distrId);
		}
		if(null != district) {
			return Msg.success().add("district", district);
		}
		return Msg.fail();
	}
}
