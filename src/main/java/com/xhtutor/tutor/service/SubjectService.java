package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Subject;
import com.xhtutor.tutor.dao.SubjectMapper;

@Service
public class SubjectService {

	@Autowired
	SubjectMapper subjectMapper;
	
	public List<Subject> getAllSubjects() {
		List<Subject> subjectList = subjectMapper.selectByExample(null);
		return subjectList;
	}
	
}
