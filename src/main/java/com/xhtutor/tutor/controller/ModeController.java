package com.xhtutor.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Mode;
import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.service.ModeService;

@Controller
public class ModeController {

	@Autowired
	ModeService modeService;
	
	@ResponseBody
	@RequestMapping(value="/mode/getAllModes")
	public Msg getAllModes() {
		List<Mode> modeList = modeService.getAllModes();
		return Msg.success().add("modeList", modeList);
	}
	
}
