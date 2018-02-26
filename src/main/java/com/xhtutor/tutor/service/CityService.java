package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.City;
import com.xhtutor.tutor.bean.CityExample;
import com.xhtutor.tutor.bean.CityExample.Criteria;
import com.xhtutor.tutor.dao.CityMapper;

@Service
public class CityService {

	@Autowired
	CityMapper cityMapper;
	
	public City getCityByCode(String code) {
		CityExample cityExample = new CityExample();
		Criteria cityCriteria = cityExample.createCriteria();
		cityExample.setOrderByClause(null);
		cityCriteria.andCodeEqualTo(code);
		List<City> result = cityMapper.selectByExample(cityExample);
		if(result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	public List<City> getCitysByProvId(Integer provId) {
		CityExample cityExample = new CityExample();
		Criteria cityCriteria = cityExample.createCriteria();
		cityCriteria.andProvIdEqualTo(provId);
		List<City> result = cityMapper.selectByExample(cityExample);
		return result;
	}
	
	public City getCityDetailByCityId(Integer cityId) {
		City city = cityMapper.selectDetailByPrimaryKey(cityId);
		return city;
	}
	
	public City getCityById(Integer cityId) {
		return cityMapper.selectByPrimaryKey(cityId);
	}
	
}
