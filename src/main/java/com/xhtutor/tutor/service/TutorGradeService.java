package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.TutorGradeExample;
import com.xhtutor.tutor.bean.TutorGradeExample.Criteria;
import com.xhtutor.tutor.bean.TutorGradeKey;
import com.xhtutor.tutor.dao.TutorGradeMapper;

@Service
public class TutorGradeService {

	@Autowired
	TutorGradeMapper tutorGradeMapper;
	
	public void saveTutorGradeKey(TutorGradeKey tutorGradeKey) {
		tutorGradeMapper.insert(tutorGradeKey);
	}
	
	public List<TutorGradeKey> getByTutorId(Integer tutorId) {
		TutorGradeExample example = new TutorGradeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		List<TutorGradeKey> list = tutorGradeMapper.selectByExample(example);
		return list;
	}
	
	public void removeKeysByTutorId(Integer tutorId, List<Integer> rmGidList) {
		TutorGradeExample example = new TutorGradeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		criteria.andGradeIdIn(rmGidList);
		tutorGradeMapper.deleteByExample(example);
	}
	
}
