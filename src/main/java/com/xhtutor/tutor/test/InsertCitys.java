package com.xhtutor.tutor.test;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xhtutor.tutor.bean.District;
import com.xhtutor.tutor.dao.CityMapper;
import com.xhtutor.tutor.dao.DistrictMapper;
import com.xhtutor.tutor.dao.ProvinceMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class InsertCitys {

	@Autowired
	ProvinceMapper provinceMapper;
	
	@Autowired
	CityMapper cityMapper;
	
	@Autowired
	DistrictMapper districtMapper;
	
//	@Test
//	public void insertProvice() throws Exception{
//		
//		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ethan\\Desktop\\省.txt"));
//		String line = null;
//		while((line=br.readLine()) != null && line !="") {
//			String[] strs = line.split(",");
//			Province prov = new Province();
//			prov.setId(Integer.valueOf(strs[0]));
//			prov.setCode(strs[1]);
//			prov.setName(strs[2]);
//			System.out.println("id:" + prov.getId() + "  code:" +prov.getCode() + "  name:" + prov.getName());
//			provinceMapper.insert(prov);
//		}
//		br.close();
//		System.out.println("success");
//		Province prov = provinceMapper.selectByPrimaryKey(1);
//		System.out.println("id:" + prov.getId() + "  code:" +prov.getCode() + "  name:" + prov.getName());
//		
//	}
	
	//城市
//	@Test
//	public void insertCity() throws Exception{
//		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ethan\\Desktop\\城.txt"));
//		String line = null;
//		while((line=br.readLine()) != null && line !="") {
//			String[] strs = line.split(",");
//			City city = new City();
//			city.setId(Integer.valueOf(strs[0]));
//			city.setCode(strs[1]);
//			city.setName(strs[2]);
//			city.setProvId(Integer.valueOf(strs[3]));
//			cityMapper.insert(city);
//			System.out.println(city.getId() + " " + city.getCode() + " " + city.getName() + " " + city.getProvId());
//		}
//		br.close();
//		System.out.println("success");
//		
//	}

	//区
	@Test
	public void insertCity() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ethan\\Desktop\\区.txt"));
		String line = null;
		while((line=br.readLine()) != null && line !="") {
			String[] strs = line.split(",");
			District distr = new District();
			distr.setId(Integer.valueOf(strs[0]));
			distr.setCode(strs[1]);
			distr.setName(strs[2]);
			distr.setCityId(Integer.valueOf(strs[3]));
			districtMapper.insert(distr);
			System.out.println(distr.getId() + " " + distr.getCode() + " " + distr.getName() + " " + distr.getCityId());
		}
		br.close();
		System.out.println("success");
	}
	
}
