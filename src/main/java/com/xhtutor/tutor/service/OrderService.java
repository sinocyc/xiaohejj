package com.xhtutor.tutor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Order;
import com.xhtutor.tutor.dao.OrderMapper;

@Service
public class OrderService {

	@Autowired
	OrderMapper orderMapper;
	
	public void saveOrder(Order order) {
		orderMapper.insertSelective(order);
	}
	
	public List<Order> getOrdersByCondition(Map<String, Object> paramMap) {
		List<Order> orderList = orderMapper.selectByCondition(paramMap);
		return orderList;
	}
	
	public List<Order> getOrdersDetailByCondition(Map<String, Object> paramMap) {
		List<Order> orderList = orderMapper.selectDetailByCondition(paramMap);
		return orderList;
	}
	
	public List<Order> getOrdersByIds(List<Integer> orderIdList) {
		List<Order> orderList = orderMapper.selectOrderDetailByIds(orderIdList);
		return orderList;
	}
	
}
