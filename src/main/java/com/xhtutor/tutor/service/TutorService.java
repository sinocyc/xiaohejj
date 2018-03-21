package com.xhtutor.tutor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.bean.TutorExample;
import com.xhtutor.tutor.bean.TutorExample.Criteria;
import com.xhtutor.tutor.dao.TutorMapper;

@Service
public class TutorService {

	@Autowired
	TutorMapper tutorMapper;
	
	/**
	 * 
	 * @param userName
	 * @return true:用户名可用; false:用户名不可用
	 */
	public boolean checkUserName(String userName) {
		TutorExample example = new TutorExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		long count = tutorMapper.countByExample(example);
		return count == 0;
	}
	
	public void saveTutorAndGetId(Tutor tutor) {
		tutorMapper.insertSelectiveAndGetId(tutor);
	}
	
	public List<Tutor> getTutorsByCondition(Map<String, Object> paramMap) {
		List<Tutor> tutorList = tutorMapper.selectByCondition(paramMap);
		return tutorList;
	}
	
	public List<Tutor> getTutorsDetailByCondition(Map<String, Object> paramMap) {
		List<Tutor> tutorList = tutorMapper.selectDetailByCondition(paramMap);
		return tutorList;
	}
	
	public Tutor getTutorByUserName(String userName) {
		TutorExample example = new TutorExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		List<Tutor> tutorList = tutorMapper.selectByExample(example);
		if(tutorList.size() > 0) {
			return tutorList.get(0);
		}
		return null;
	}
	
	public Tutor getTutorByPhone(String phone) {
		TutorExample example = new TutorExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		List<Tutor> tutorList = tutorMapper.selectByExample(example);
		if(tutorList.size() > 0) {
			return tutorList.get(0);
		}
		return null;
	}
	
	public Tutor getTutorDetailById(Integer tutorId) {
		Tutor tutor = tutorMapper.selectDetailById(tutorId);
		return tutor;
	}
	
	public Integer modifyByPrimaryKeySelective(Tutor tutor) {
		return tutorMapper.updateByPrimaryKeySelective(tutor);
	}
	
	public Tutor getTutorById(Integer tutorId) {
		return tutorMapper.selectByPrimaryKey(tutorId);
	}
	
}
