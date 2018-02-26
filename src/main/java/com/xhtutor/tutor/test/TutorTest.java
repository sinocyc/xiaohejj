package com.xhtutor.tutor.test;

import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.utils.TutorUtil;

public class TutorTest {

	public static void main(String[] args) {
		Tutor tutor = new Tutor();
		tutor.setPassword("aaa");
		System.out.println(tutor);
		TutorUtil.removePassword(tutor);
		System.out.println(tutor);
	}
	
}
