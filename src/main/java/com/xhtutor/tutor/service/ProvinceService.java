package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Province;
import com.xhtutor.tutor.dao.ProvinceMapper;

@Service
public class ProvinceService {

	@Autowired
	ProvinceMapper provinceMapper;
	
	public List<Province> getAllProvice() {
		List<Province> provList = provinceMapper.selectByExample(null);
		return provList;
	}
	
}
