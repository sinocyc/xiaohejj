package com.xhtutor.tutor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhtutor.tutor.bean.Msg;
import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.bean.TutorDistrKey;
import com.xhtutor.tutor.bean.TutorGradeKey;
import com.xhtutor.tutor.bean.TutorModeKey;
import com.xhtutor.tutor.bean.TutorOrderKey;
import com.xhtutor.tutor.bean.TutorSubjectKey;
import com.xhtutor.tutor.service.TutorDistrService;
import com.xhtutor.tutor.service.TutorGradeService;
import com.xhtutor.tutor.service.TutorModeService;
import com.xhtutor.tutor.service.TutorOrderService;
import com.xhtutor.tutor.service.TutorService;
import com.xhtutor.tutor.service.TutorSubjectService;
import com.xhtutor.tutor.utils.TutorUtil;

@Controller
public class TutorController {
	
	@Autowired
	TutorService tutorService;
	
	@Autowired
	TutorSubjectService tutorSubjectService;
	
	@Autowired
	TutorGradeService tutorGradeService;
	
	@Autowired
	TutorDistrService tutorDistrService;
	
	@Autowired
	TutorModeService tutorModeService;
	
	@Autowired
	TutorOrderService tutorOrderService;

	@ResponseBody
	@RequestMapping(value="/tutor/register")
	public Msg tutorRegister(Tutor tutor, HttpServletRequest request, HttpServletResponse response) {
		tutor.setLastLogin(new Date());
		tutor.setStatus(1);
		tutorService.saveTutorAndGetId(tutor);
		Integer tutorId = tutor.getId();
		if(null != tutorId) {
			for(String subjectId : request.getParameterValues("subjectId")) {
				TutorSubjectKey tutorSubjectKey = new TutorSubjectKey();
				tutorSubjectKey.setTutorId(tutorId);
				tutorSubjectKey.setSubjectId(Integer.valueOf(subjectId));
				tutorSubjectService.saveTutorSubjectKey(tutorSubjectKey);
			}
			for(String gradeId : request.getParameterValues("gradeId")) {
				TutorGradeKey tutorGradeKey = new TutorGradeKey();
				tutorGradeKey.setTutorId(tutorId);
				tutorGradeKey.setGradeId(Integer.valueOf(gradeId));
				tutorGradeService.saveTutorGradeKey(tutorGradeKey);
			}
			for(String distrId : request.getParameterValues("distrId")) {
				TutorDistrKey tutorDistrKey = new TutorDistrKey();
				tutorDistrKey.setTutorId(tutorId);
				tutorDistrKey.setDistrId(Integer.valueOf(distrId));
				tutorDistrService.saveTutorDistrKey(tutorDistrKey);
			}
			for(String modeId : request.getParameterValues("modeId")) {
				TutorModeKey tutorModeKey = new TutorModeKey();
				tutorModeKey.setTutorId(tutorId);
				tutorModeKey.setModeId(Integer.valueOf(modeId));
				tutorModeService.saveTutorModeKey(tutorModeKey);
			}
			Cookie loginCookie = new Cookie("loginTutorId", String.valueOf(tutorId));
			loginCookie.setPath("/");
			loginCookie.setMaxAge(-1);
			response.addCookie(loginCookie);
			return Msg.success().add("message", "注册成功！");
		}
		return Msg.fail().add("message", "注册失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/checkUserName")
	public Msg checkUserName(@RequestParam("userName")String userName) {
		if(tutorService.checkUserName(userName)) {
			return Msg.success().add("message", "用户名可用");
		}
		return Msg.fail().add("message", "用户名不可用");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/checkTutorPhoneUnique")
	public Msg checkTutorPhoneUnique(@RequestParam("phone")String phone, 
			@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId) {
		if(null != phone) {
			Tutor tutor = tutorService.getTutorByPhone(phone);
			if(null == tutor) {
				return Msg.success().add("message", "手机号未被注册，可用。");
			}
			if(null != loginTutorId && loginTutorId.equals(tutor.getId())) {
				return Msg.success().add("message", "手机号可用。");
			}
			return Msg.fail().add("message", "手机号已被注册，不可用。");
		}
		return Msg.fail().add("message", "手机号错误。");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/getTutorsByCondition")
	public Msg getTutorsByCondition(Integer subjectId, Integer gradeId, Integer distrId, Integer modeId, 
			String gender, Integer tutorType, Integer teachCityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("modeId", modeId);
		paramMap.put("gender", gender);
		paramMap.put("tutorType", tutorType);
		paramMap.put("teachCityId", teachCityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Tutor> tutorList = tutorService.getTutorsByCondition(paramMap);
		return Msg.success().add("tutorList", tutorList);
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/getTutorsDetailByCondition")
	public Msg getTutorsInfoByCondition(Integer subjectId, Integer gradeId, Integer distrId, Integer modeId, 
			String gender, Integer tutorType, Integer teachCityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("modeId", modeId);
		paramMap.put("gender", gender);
		paramMap.put("tutorType", tutorType);
		paramMap.put("teachCityId", teachCityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Tutor> tutorList = tutorService.getTutorsDetailByCondition(paramMap);
		for (Tutor tutor : tutorList) {
			TutorUtil.removePassword(tutor);
		}
		return Msg.success().add("tutorList", tutorList);
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorPageHasContent")
	public Msg tutorPageHasContent(Integer subjectId, Integer gradeId, Integer distrId, Integer modeId, 
			String gender, Integer tutorType, Integer teachCityId, Integer start, Integer num) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("subjectId", subjectId);
		paramMap.put("gradeId", gradeId);
		paramMap.put("distrId", distrId);
		paramMap.put("modeId", modeId);
		paramMap.put("gender", gender);
		paramMap.put("tutorType", tutorType);
		paramMap.put("teachCityId", teachCityId);
		paramMap.put("start", start);
		paramMap.put("num", num);
		List<Tutor> tutorList = tutorService.getTutorsByCondition(paramMap);
		if(tutorList.size() > 0) {
			return Msg.success().add("message", "该页码有内容！");
		}
		return Msg.fail().add("message", "该页码没有内容！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorLoginByUserName")
	public Msg tutorLoginByUserName(@RequestParam("userName") String userName, @RequestParam("password") String password, 
			HttpServletResponse response) {
		if(null != userName && !"".equals(userName) && null != password && !"".equals(password)) {
			Tutor tutor = tutorService.getTutorByUserName(userName);
			if(null != tutor) {
				if(password.equals(tutor.getPassword())) {
					// 更新最后登录时间
					Integer tutorId = tutor.getId();
					Tutor newTutor = new Tutor();
					newTutor.setId(tutorId);
					newTutor.setLastLogin(new Date());
					tutorService.modifyByPrimaryKeySelective(newTutor);
					
					Cookie loginCookie = new Cookie("loginTutorId", String.valueOf(tutor.getId()));
					loginCookie.setPath("/");
					loginCookie.setMaxAge(-1);
					response.addCookie(loginCookie);
					return Msg.success().add("message", "登录成功!");
				}
			}
		}
		return Msg.fail().add("message", "登录失败!");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorLoginByPhone")
	public Msg tutorLoginByPhone(@RequestParam("phone") String phone, @RequestParam("password") String password, HttpServletResponse response) {
		if(null != phone && !"".equals(phone) && null != password && !"".equals(password)) {
			Tutor tutor = tutorService.getTutorByPhone(phone);
			if(null != tutor) {
				if(password.equals(tutor.getPassword())) {
					// 更新最后登录时间
					Integer tutorId = tutor.getId();
					Tutor newTutor = new Tutor();
					newTutor.setId(tutorId);
					newTutor.setLastLogin(new Date());
					tutorService.modifyByPrimaryKeySelective(newTutor);
					
					Cookie loginCookie = new Cookie("loginTutorId", String.valueOf(tutor.getId()));
					loginCookie.setPath("/");
					loginCookie.setMaxAge(-1);
					response.addCookie(loginCookie);
					return Msg.success().add("message", "登录成功!");
				}
			}
		}
		return Msg.fail().add("message", "登录失败!");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/isTutorLogin")
	public Msg isTutorLogin(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId) {
		if(null != loginTutorId) {
			return Msg.success().add("message", "已登录！");
		}
		return Msg.fail().add("message", "未登录！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorLogout")
	public Msg tutorLogout(HttpServletResponse response) {
		Cookie loginCookie = new Cookie("loginTutorId", "");
		loginCookie.setPath("/");
		loginCookie.setMaxAge(0);
		response.addCookie(loginCookie);
		return Msg.success().add("message", "退出登录成功！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/getLoginTutorInfo")
	public Msg getLoginTutorInfo(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId) {
		Tutor tutor = null;
		if(null != loginTutorId) {
			tutor = tutorService.getTutorDetailById(loginTutorId);
		}
		if(null != tutor) {
			TutorUtil.removePassword(tutor);
			return Msg.success().add("tutor", tutor);
		}
		return Msg.fail().add("message", "获取登录的tutor的信息失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorModifySelective")
	public Msg tutorModifySelective(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId, 
			Tutor tutor) {
		if(null != loginTutorId) {
			tutor.setId(loginTutorId);
			tutorService.modifyByPrimaryKeySelective(tutor);
			return Msg.success().add("message", "修改成功！");
		}
		return Msg.fail().add("message", "修改失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorModifyTeachInfo")
	public Msg tutorModifyTeachInfo(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId, 
			Tutor tutor, HttpServletRequest request) {
		if(null != loginTutorId) {
			tutor.setId(loginTutorId);
			tutorService.modifyByPrimaryKeySelective(tutor);
			
			List<TutorSubjectKey> tsDBList = tutorSubjectService.getByTutorId(loginTutorId);
			String[] sidReqStrArr = request.getParameterValues("subjectId");
			List<Integer> rmSidList = new ArrayList<Integer>();
			List<TutorSubjectKey> addTsList = new ArrayList<TutorSubjectKey>();
			if(null != sidReqStrArr) {
				for(TutorSubjectKey tsDB : tsDBList) {
					Integer sidDB = tsDB.getSubjectId();
					boolean rmOrNot = true;
					for(String sidReqStr : sidReqStrArr) {
						Integer sidReq = Integer.valueOf(sidReqStr);
						if(sidDB.equals(sidReq)) {
							rmOrNot = false;
							break;
						}
					}
					if(rmOrNot) {
						rmSidList.add(sidDB);
					}
				}
				for(String sidReqStr : sidReqStrArr) {
					Integer sidReq = Integer.valueOf(sidReqStr);
					boolean addOrNot = true;
					for(TutorSubjectKey tsDB : tsDBList) {
						Integer sidDB = tsDB.getSubjectId();
						if(sidReq.equals(sidDB)) {
							addOrNot = false;
							break;
						}
					}
					if(addOrNot) {
						TutorSubjectKey addTs = new TutorSubjectKey();
						addTs.setTutorId(loginTutorId);
						addTs.setSubjectId(sidReq);
						addTsList.add(addTs);
					}
				}
				if(rmSidList.size() > 0) {
					tutorSubjectService.removeKeysByTutorId(loginTutorId, rmSidList);
				}
				for(TutorSubjectKey addTs : addTsList) {
					tutorSubjectService.saveTutorSubjectKey(addTs);
				}
			}
			
			List<TutorGradeKey> tgDBList = tutorGradeService.getByTutorId(loginTutorId);
			String[] gidReqStrArr = request.getParameterValues("gradeId");
			List<Integer> rmGidList = new ArrayList<Integer>();
			List<TutorGradeKey> addTgList = new ArrayList<TutorGradeKey>();
			if(null != gidReqStrArr) {
				for(TutorGradeKey tgDB : tgDBList) {
					Integer gidDB = tgDB.getGradeId();
					boolean rmOrNot = true;
					for(String gidReqStr : gidReqStrArr) {
						Integer gidReq = Integer.valueOf(gidReqStr);
						if(gidDB.equals(gidReq)) {
							rmOrNot = false;
							break;
						}
					}
					if(rmOrNot) {
						rmGidList.add(gidDB);
					}
				}
				for(String gidReqStr : gidReqStrArr) {
					Integer gidReq = Integer.valueOf(gidReqStr);
					boolean addOrNot = true;
					for(TutorGradeKey tgDB : tgDBList) {
						Integer gidDB = tgDB.getGradeId();
						if(gidReq.equals(gidDB)) {
							addOrNot = false;
							break;
						}
					}
					if(addOrNot) {
						TutorGradeKey addTg = new TutorGradeKey();
						addTg.setTutorId(loginTutorId);
						addTg.setGradeId(gidReq);
						addTgList.add(addTg);
					}
				}
				if(rmGidList.size() > 0) {
					tutorGradeService.removeKeysByTutorId(loginTutorId, rmGidList);
				}
				for(TutorGradeKey addTg : addTgList) {
					tutorGradeService.saveTutorGradeKey(addTg);
				}
			}
			
			List<TutorDistrKey> tdDBList = tutorDistrService.getByTutorId(loginTutorId);
			String[] didReqStrArr = request.getParameterValues("distrId");
			List<Integer> rmDidList = new ArrayList<Integer>();
			List<TutorDistrKey> addTdList = new ArrayList<TutorDistrKey>();
			if(null != didReqStrArr) {
				for(TutorDistrKey tdDB : tdDBList) {
					Integer didDB = tdDB.getDistrId();
					boolean rmOrNot = true;
					for(String didReqStr : didReqStrArr) {
						Integer didReq = Integer.valueOf(didReqStr);
						if(didDB.equals(didReq)) {
							rmOrNot = false;
							break;
						}
					}
					if(rmOrNot) {
						rmDidList.add(didDB);
					}
				}
				for(String didReqStr : didReqStrArr) {
					Integer didReq = Integer.valueOf(didReqStr);
					boolean addOrNot = true;
					for(TutorDistrKey tdDB : tdDBList) {
						Integer didDB = tdDB.getDistrId();
						if(didReq.equals(didDB)) {
							addOrNot = false;
							break;
						}
					}
					if(addOrNot) {
						TutorDistrKey addTd = new TutorDistrKey();
						addTd.setTutorId(loginTutorId);
						addTd.setDistrId(didReq);
						addTdList.add(addTd);
					}
				}
				if(rmDidList.size() > 0) {
					tutorDistrService.removeKeysByTutorId(loginTutorId, rmDidList);
				}
				for(TutorDistrKey addTd : addTdList) {
					tutorDistrService.saveTutorDistrKey(addTd);
				}
			}
			
			List<TutorModeKey> tmDBList = tutorModeService.getByTutorId(loginTutorId);
			String[] midReqStrArr = request.getParameterValues("modeId");
			List<Integer> rmMidList = new ArrayList<Integer>();
			List<TutorModeKey> addTmList = new ArrayList<TutorModeKey>();
			if(null != midReqStrArr) {
				for(TutorModeKey tmDB : tmDBList) {
					Integer midDB = tmDB.getModeId();
					boolean rmOrNot = true;
					for(String midReqStr : midReqStrArr) {
						Integer midReq = Integer.valueOf(midReqStr);
						if(midDB.equals(midReq)) {
							rmOrNot = false;
							break;
						}
					}
					if(rmOrNot) {
						rmMidList.add(midDB);
					}
				}
				for(String midReqStr : midReqStrArr) {
					Integer midReq = Integer.valueOf(midReqStr);
					boolean addOrNot = true;
					for(TutorModeKey tmDB : tmDBList) {
						Integer midDB = tmDB.getModeId();
						if(midReq.equals(midDB)) {
							addOrNot = false;
							break;
						}
					}
					if(addOrNot) {
						TutorModeKey addTm = new TutorModeKey();
						addTm.setTutorId(loginTutorId);
						addTm.setModeId(midReq);
						addTmList.add(addTm);
					}
				}
				if(rmMidList.size() > 0) {
					tutorModeService.removeKeysByTutorId(loginTutorId, rmMidList);
				}
				for(TutorModeKey addTm : addTmList) {
					tutorModeService.saveTutorModeKey(addTm);
				}
			}
			
			return Msg.success().add("message", "修改成功！");
		}
		return Msg.fail().add("message", "修改失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorModifyPassword")
	public Msg tutorModifyPassword(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId, 
			@RequestParam("originPassword") String originPassword, @RequestParam("newPassword") String newPassword) {
		if(null != loginTutorId && null != originPassword && !"".equals(originPassword) && null != newPassword && !"".equals(newPassword)) {
			Tutor tutor = tutorService.getTutorById(loginTutorId);
			if(null != tutor && originPassword.equals(tutor.getPassword())) {
				Tutor updateTutor = new Tutor();
				updateTutor.setId(loginTutorId);
				updateTutor.setPassword(newPassword);
				tutorService.modifyByPrimaryKeySelective(updateTutor);
				return Msg.success().add("message", "密码修改成功！");
			}
			return Msg.fail().add("message", "初始密码错误，修改失败！");
		}
		return Msg.fail().add("message", "密码修改失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/getTutorInfoById")
	public Msg getTutorInfoById(@RequestParam("tutorId") Integer tutorId) {
		if(null != tutorId) {
			Tutor tutor = tutorService.getTutorDetailById(tutorId);
			TutorUtil.removeImportantInfo(tutor);
			if(null != tutor) {
				return Msg.success().add("tutor", tutor);
			}
		}
		return Msg.fail().add("message", "获取tutor信息失败！");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/applyOrder")
	public Msg applyOrder(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId, 
			@RequestParam("orderId") Integer orderId) {
		if(null != loginTutorId) {
			if(null != orderId) {
				boolean exist = tutorOrderService.existTutorOrder(loginTutorId, orderId);
				if(exist) {
					return Msg.fail().add("message", "您已经申请过该订单，可在 个人中心->我的订单 中查看您申请的订单。");
				}
				TutorOrderKey to = new TutorOrderKey();
				to.setTutorId(loginTutorId);
				to.setOrderId(orderId);
				tutorOrderService.saveTutorOrder(to);
				return Msg.success().add("message", "申请订单成功，可在  个人中心->我的订单 中查看您申请的订单。");
			}
			return Msg.fail().add("message", "申请失败，该订单可能存在异常。");
		}
		return Msg.fail().add("message", "您尚未登录，请以教员身份登录后申请订单。");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/cancelOrder")
	public Msg cancelOrder(@CookieValue(value = "loginTutorId", required = false) Integer loginTutorId, 
			@RequestParam("orderId") Integer orderId) {
		if(null != loginTutorId) {
			if(null != orderId) {
				TutorOrderKey to = new TutorOrderKey();
				to.setTutorId(loginTutorId);
				to.setOrderId(orderId);
				tutorOrderService.removeTutorOrder(to);
				return Msg.success().add("message", "取消订单成功！");
			}
			return Msg.fail().add("message", "取消失败，该订单可能存在异常。");
		}
		return Msg.fail().add("message", "登录信息失效，请以教员身份重新登录后操作。");
	}
	
	@ResponseBody
	@RequestMapping(value="/tutor/tutorModifyStatus")
	public Msg tutorModifyStatus(Tutor tutor) {
		Integer res = tutorService.modifyByPrimaryKeySelective(tutor);
		if (res > 0) {
			return Msg.success().add("message", "修改成功！");
		}
		return Msg.fail().add("message", "修改失败！");
	}
	
}
