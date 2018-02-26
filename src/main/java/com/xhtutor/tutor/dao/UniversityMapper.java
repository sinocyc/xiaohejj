package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.University;
import com.xhtutor.tutor.bean.UniversityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UniversityMapper {
    long countByExample(UniversityExample example);

    int deleteByExample(UniversityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(University record);

    int insertSelective(University record);

    List<University> selectByExample(UniversityExample example);

    University selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") University record, @Param("example") UniversityExample example);

    int updateByExample(@Param("record") University record, @Param("example") UniversityExample example);

    int updateByPrimaryKeySelective(University record);

    int updateByPrimaryKey(University record);
    
    
    int insertAndGetId(University record);
}