package com.xhtutor.tutor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.bean.Order;
import com.xhtutor.tutor.bean.TutorOrderKey;
import com.xhtutor.tutor.service.OrderService;
import com.xhtutor.tutor.service.TutorOrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	TutorOrderService tutorOrderService;
	
	@ResponseBody
	@RequestMapping(value="/order/publish")
	public Msg publishOrder(Order order) {
		try {
			order.setCode(String.valueOf(new Date().getTime()));
			order.setStatus(1);
			order.setUpdateTime(new Date());
			orderService.saveOrder(order);
			return Msg.success();
		} catch(Exception e) {
			e.printStackTrace();
			return Msg.fail();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/order/getOrdersByCondition")
	public Msg getOrdersByCondition(Integer subjectId, Integer gradeId, 
			Integer distrId, Integer cityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("cityId", cityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Order> orderList = orderService.getOrdersByCondition(paramMap);
		return Msg.success().add("orderList", orderList);
	}
	
	@ResponseBody
	@RequestMapping(value="/order/getOrdersDetailByCondition")
	public Msg getOrdersDetailByCondition(Integer subjectId, Integer gradeId, 
			Integer distrId, Integer cityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("cityId", cityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Order> orderList = orderService.getOrdersDetailByCondition(paramMap);
		return Msg.success().add("orderList", orderList);
	}
	
	@ResponseBody
	@RequestMapping(value="/order/orderPageHasContent")
	public Msg isOrderLastPage(Integer subjectId, Integer gradeId, 
			Integer distrId, Integer cityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("cityId", cityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Order> orderList = orderService.getOrdersByCondition(paramMap);
		if(orderList.size() > 0) {
			return Msg.success().add("message", "该页码有内容！");
		}
		return Msg.fail().add("message", "该页码没有内容！");
	}
	
	@ResponseBody
	@RequestMapping(value="/order/getOrdersByTutorId")
	public Msg getOrdersByTutorId(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId) {
		if(null != loginTutorId) {
			List<TutorOrderKey> toList = tutorOrderService.getTutorOrderByTutorId(loginTutorId);
			List<Integer> orderIdList = new ArrayList<Integer>();
			for(TutorOrderKey to : toList) {
				orderIdList.add(to.getOrderId());
			}
			if(orderIdList.size() > 0) {
				List<Order> orderList = orderService.getOrdersByIds(orderIdList);
				return Msg.success().add("orderList", orderList);
			}
			return Msg.success().add("message", "没有收藏的订单。");
		}
		return Msg.fail().add("message", "教员尚未登录。");
	}
	
}
