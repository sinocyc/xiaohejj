package com.xhtutor.tutor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhtutor.tutor.bean.Mode;
import com.xhtutor.tutor.dao.ModeMapper;

@Service
public class ModeService {

	@Autowired
	ModeMapper modeMapper;
	
	public List<Mode> getAllModes() {
		List<Mode> modeList = modeMapper.selectByExample(null);
		return modeList;
	}
	
}
