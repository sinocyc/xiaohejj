package com.xhtutor.tutor.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.City;
import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.service.CityService;
import com.xhtutor.tutor.utils.IpAddrUtil;

@Controller
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@ResponseBody
	@RequestMapping(value="/city/currentCity")
	public Msg getCurrCity(@CookieValue(value = "currCityId", required = false) Integer currCityId, 
			HttpServletRequest request, HttpServletResponse response) {
		// 判断是否有cookie，如果有返回cookie城市信息
		if(null != currCityId) {
			City currCity = cityService.getCityById(currCityId);
			if(currCity != null) {
				return Msg.success().add("city", currCity);
			}
		}
		
		// 没有城市cookie，通过ip定位城市，并添加城市cookie
		String currCityCode = IpAddrUtil.getCurrCityCode(request);
		if(null != currCityCode) {
			City currCity = cityService.getCityByCode(currCityCode);
			if(null != currCity) {
				String currCityIdStr = String.valueOf(currCity.getId());
				Cookie currCityCookie = new Cookie("currCityId", currCityIdStr);
				currCityCookie.setPath("/");
				currCityCookie.setMaxAge(6*30*24*60*60);
				response.addCookie(currCityCookie);
				return Msg.success().add("city", currCity);
			}
		}
		return Msg.fail();
	}
	
	@ResponseBody
	@RequestMapping(value="/city/setCurrCity")
	public Msg setCurrCity(@RequestParam("currCityId") String currCityId, HttpServletResponse response) {
		Cookie currCityCookie = new Cookie("currCityId", currCityId);
		currCityCookie.setPath("/");
		currCityCookie.setMaxAge(6*30*24*60*60);
		response.addCookie(currCityCookie);
		return Msg.success().add("message", "成功添加currCityId的cookie！");
	}
	
	/*@ResponseBody
	@RequestMapping(value="/city/currentCity")
	public Msg getCurrCity(HttpServletRequest request, HttpServletResponse response) {
		String currCityCode = IpAddrUtil.getCurrCityCode(request);
		if(null != currCityCode) {
			City currCity = cityService.getCityByCode(currCityCode);
			return Msg.success().add("city", currCity);
		}
		return Msg.fail();
	}*/
	
	@ResponseBody
	@RequestMapping(value="/city/getCitysByProv")
	public Msg getCitysByProv(@RequestParam("provId") Integer provId) {
		List<City> cityList = cityService.getCitysByProvId(provId);
		return Msg.success().add("cityList", cityList);
	}
	
	@ResponseBody
	@RequestMapping(value="/city/getCityDetailById")
	public Msg getCityDetailById(@RequestParam("cityId") Integer cityId) {
		City city = null;
		if(null != cityId) {
			city = cityService.getCityDetailByCityId(cityId);
		}
		if(null != city) {
			return Msg.success().add("city", city);
		}
		return Msg.fail();
	}

}
