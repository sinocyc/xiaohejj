package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.District;
import com.xhtutor.tutor.bean.DistrictExample;
import com.xhtutor.tutor.bean.DistrictExample.Criteria;
import com.xhtutor.tutor.dao.DistrictMapper;

@Service
public class DistrictService {

	@Autowired
	DistrictMapper districtMapper;
	
	public List<District> getDistrsByCityId(Integer cityId) {
		DistrictExample distrExample = new DistrictExample();
		Criteria distrCriteria = distrExample.createCriteria();
		distrCriteria.andCityIdEqualTo(cityId);
		List<District> distrList = districtMapper.selectByExample(distrExample);
		return distrList;
	}
	
	public District getDistrDetailById(Integer distrId) {
		District district = districtMapper.selectDetailByPrimaryKey(distrId);
		return district;
	}
	
}
