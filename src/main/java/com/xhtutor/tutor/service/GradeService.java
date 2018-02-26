package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Grade;
import com.xhtutor.tutor.dao.GradeMapper;

@Service
public class GradeService {

	@Autowired
	GradeMapper gradeMapper;
	
	public List<Grade> getAllGrades() {
		List<Grade> gradeList = gradeMapper.selectByExample(null);
		return gradeList;
	}
	
}
