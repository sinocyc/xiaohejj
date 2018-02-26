package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.TutorDistrExample;
import com.xhtutor.tutor.bean.TutorDistrExample.Criteria;
import com.xhtutor.tutor.bean.TutorDistrKey;
import com.xhtutor.tutor.dao.TutorDistrMapper;

@Service
public class TutorDistrService {

	@Autowired
	TutorDistrMapper tutorDistrMapper;
	
	public void saveTutorDistrKey(TutorDistrKey tutorDistrKey) {
		tutorDistrMapper.insert(tutorDistrKey);
	}
	
	
	public List<TutorDistrKey> getByTutorId(Integer tutorId) {
		TutorDistrExample example = new TutorDistrExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		List<TutorDistrKey> list = tutorDistrMapper.selectByExample(example);
		return list;
	}
	
	public void removeKeysByTutorId(Integer tutorId, List<Integer> rmDidList) {
		TutorDistrExample example = new TutorDistrExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		criteria.andDistrIdIn(rmDidList);
		tutorDistrMapper.deleteByExample(example);
	}
	
}
