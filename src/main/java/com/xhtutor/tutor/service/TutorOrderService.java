package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.TutorOrderExample;
import com.xhtutor.tutor.bean.TutorOrderExample.Criteria;
import com.xhtutor.tutor.bean.TutorOrderKey;
import com.xhtutor.tutor.dao.TutorOrderMapper;

@Service
public class TutorOrderService {

	@Autowired
	TutorOrderMapper tutorOrderMapper;
	
	public boolean existTutorOrder(Integer tutorId, Integer orderId) {
		TutorOrderExample example = new TutorOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		criteria.andOrderIdEqualTo(orderId);
		long result = tutorOrderMapper.countByExample(example);
		if(result > 0) {
			return true;
		}
		return false;
	}
	
	public void saveTutorOrder(TutorOrderKey to) {
		tutorOrderMapper.insert(to);
	}
	
	public List<TutorOrderKey> getTutorOrderByTutorId(Integer tutorId) {
		TutorOrderExample example = new TutorOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andTutorIdEqualTo(tutorId);
		List<TutorOrderKey> toList = tutorOrderMapper.selectByExample(example);
		return toList;
	}
	
	public void removeTutorOrder(TutorOrderKey to) {
		tutorOrderMapper.deleteByPrimaryKey(to);
	}
	
}
