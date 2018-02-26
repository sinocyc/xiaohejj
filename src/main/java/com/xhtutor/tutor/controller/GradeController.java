package com.xhtutor.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Grade;
import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.service.GradeService;

@Controller
public class GradeController {

	@Autowired
	GradeService gradeService;
	
	@ResponseBody
	@RequestMapping(value="/grade/getAll")
	public Msg getAllGrades() {
		List<Grade> gradeList = gradeService.getAllGrades();
		return Msg.success().add("gradeList", gradeList);
	}
	
}
