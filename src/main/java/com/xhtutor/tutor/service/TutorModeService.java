package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.TutorModeExample;
import com.xhtutor.tutor.bean.TutorModeExample.Criteria;
import com.xhtutor.tutor.bean.TutorModeKey;
import com.xhtutor.tutor.dao.TutorModeMapper;

@Service
public class TutorModeService {

	@Autowired
	TutorModeMapper tutorModeMapper;
	
	public void saveTutorModeKey(TutorModeKey tutorModeKey) {
		tutorModeMapper.insert(tutorModeKey);
	}
	
	public List<TutorModeKey> getByTutorId(Integer tutorId) {
		TutorModeExample example = new TutorModeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		List<TutorModeKey> list = tutorModeMapper.selectByExample(example);
		return list;
	}
	
	public void removeKeysByTutorId(Integer tutorId, List<Integer> rmMidList) {
		TutorModeExample example = new TutorModeExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		criteria.andModeIdIn(rmMidList);
		tutorModeMapper.deleteByExample(example);
	}
	
}
