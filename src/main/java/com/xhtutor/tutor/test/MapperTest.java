package com.xhtutor.tutor.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xhtutor.tutor.bean.City;
import com.xhtutor.tutor.bean.District;
import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.bean.University;
import com.xhtutor.tutor.dao.OrderMapper;
import com.xhtutor.tutor.dao.TutorMapper;
import com.xhtutor.tutor.dao.UniversityMapper;
import com.xhtutor.tutor.service.CityService;
import com.xhtutor.tutor.service.DistrictService;
import com.xhtutor.tutor.service.TutorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	TutorMapper tutorMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	UniversityMapper universityMapper;
	
	@Autowired
	TutorService tutorService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	DistrictService districtService;
	
	@Test
	public void testDistrictService() {
		District district = districtService.getDistrDetailById(735);
		System.out.println(district);
	}
	
	@Test
	public void testCityService() {
		City city = cityService.getCityDetailByCityId(79);
		System.out.println(city);
	}
	
	@Test
	public void testA() {
		System.out.println(tutorMapper);
		Tutor tutor = tutorMapper.selectByPrimaryKey(1);
		System.out.println(tutor.getUserName());
		System.out.println("success");
	}
	
	private Integer insertTutorGetId(Tutor tutor) {
		tutorMapper.insertSelectiveAndGetId(tutor);
		return tutor.getId();
	}
	
	@Test
	public void testInsertTutor() {
		Tutor tutor = new Tutor();
		tutor.setUserName("uname" + "11");
		tutor.setPassword("123456");
		tutor.setRealName("rname" + "11");
		tutor.setGender("m");
		tutor.setPhone("12345678901");
		tutor.setEmail("mail@mail.com");
		tutor.setMajor("major");
		tutor.setTutorType(1);
		tutor.setUniversity("u");
		tutor.setTeachCityId(1);
		Integer id = insertTutorGetId(tutor);
		System.out.println(tutor.getId());
		System.out.println(id);
	}
	
	@Test
	public void testInsertTutors() {
		for(int i = 0; i < 20; i++) {
			Tutor tutor = new Tutor();
			tutor.setUserName("uname" + i);
			tutor.setPassword("123456");
			tutor.setRealName("rname" + i);
			tutor.setGender("m");
			tutor.setPhone("12345678901");
			tutor.setEmail("mail@mail.com");
			tutor.setMajor("major");
			tutor.setTutorType(1);
			tutor.setUniversity("u");
			tutor.setTeachCityId(1);
			tutorMapper.insertSelective(tutor);
		}
		System.out.println("success");
	}
	
	@Test
	public void testTutorService() {
		Tutor tutor = new Tutor();
		tutor.setUserName("uname" + "14");
		tutor.setPassword("123456");
		tutor.setRealName("rname" + "14");
		tutor.setGender("m");
		tutor.setPhone("12345678901");
		tutor.setEmail("mail@mail.com");
		tutor.setMajor("major");
		tutor.setTutorType(1);
		tutor.setUniversity("u");
		tutor.setTeachCityId(1);
//		Integer id = tutorService.saveTutorAndGetId(tutor);
//		System.out.println(id);
	}
	
	@Test
	public void testInsertUniv() {
		University university = new University();
		university.setCode("124");
		university.setName("大学名字2");
		university.setCityId(1);
		System.out.println("插入前id:" + university.getId());
		universityMapper.insertAndGetId(university);
		System.out.println("插入后id:" + university.getId());
		System.out.println("success");
	}
	
	@Test
	public void selectByCondition() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Tutor> list = tutorMapper.selectByCondition(paramMap);
		System.out.println(list);
	}
	
	@Test
	public void getTutorTest() {
		Tutor tutor = tutorService.getTutorDetailById(1);
		System.out.println(tutor);
	}
}
