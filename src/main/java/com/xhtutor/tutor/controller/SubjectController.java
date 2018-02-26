package com.xhtutor.tutor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.bean.Subject;
import com.xhtutor.tutor.service.SubjectService;

@Controller
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@ResponseBody
	@RequestMapping(value="/subject/getAll")
	public Msg getAllSubjects() {
		List<Subject> subjectList = subjectService.getAllSubjects();
		return Msg.success().add("subjectList", subjectList);
	}
	
}
