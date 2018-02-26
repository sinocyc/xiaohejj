package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.TutorSubjectExample;
import com.xhtutor.tutor.bean.TutorSubjectExample.Criteria;
import com.xhtutor.tutor.bean.TutorSubjectKey;
import com.xhtutor.tutor.dao.TutorSubjectMapper;

@Service
public class TutorSubjectService {

	@Autowired
	TutorSubjectMapper tutorSubjectMapper;
	
	public void saveTutorSubjectKey(TutorSubjectKey tutorSubject) {
		tutorSubjectMapper.insert(tutorSubject);
	}
	
	public List<TutorSubjectKey> getByTutorId(Integer tutorId) {
		TutorSubjectExample example = new TutorSubjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		List<TutorSubjectKey> list = tutorSubjectMapper.selectByExample(example);
		return list;
	}
	
	public void removeKeysByTutorId(Integer tutorId, List<Integer> rmSidList) {
		TutorSubjectExample example = new TutorSubjectExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		criteria.andSubjectIdIn(rmSidList);
		tutorSubjectMapper.deleteByExample(example);
	}
	
}
