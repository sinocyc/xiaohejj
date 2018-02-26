package com.xhtutor.tutor.utils;

import com.xhtutor.tutor.bean.Tutor;

public class TutorUtil {
	public static void removeImportantInfo(Tutor tutor) {
		if(null != tutor) {
			tutor.setPassword("");
			tutor.setPhone("");
			tutor.setEmail("");
			tutor.setQq("");
			tutor.setIdCardNum("");
			tutor.setBirthCityId(0);
		}
	}
	
	public static void removePassword(Tutor tutor) {
		tutor.setPassword("");
	}
	
	public static Integer convertTutorIdStr(String tutorIdStr) {
		String numReg = "^[0-9]+$";
		if(null != tutorIdStr && tutorIdStr.matches(numReg)) {
			return Integer.valueOf(tutorIdStr);
		}
		return null;
	}
}
