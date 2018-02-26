package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Admin;
import com.xhtutor.tutor.bean.AdminExample;
import com.xhtutor.tutor.dao.AdminMapper;

@Service
public class AdminService {

	@Autowired
	AdminMapper adminMapper;
	
	public Admin getAdminByUserName(String userName) {
		AdminExample example = new AdminExample();
		example.createCriteria().andUserNameEqualTo(userName);
		List<Admin> adminList = adminMapper.selectByExample(example);
		if (adminList.size() > 0) {
			return adminList.get(0);
		}
		return null;
	}
	
}
