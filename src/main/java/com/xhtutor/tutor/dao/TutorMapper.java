package com.xhtutor.tutor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xhtutor.tutor.bean.Tutor;
import com.xhtutor.tutor.bean.TutorExample;

public interface TutorMapper {
    long countByExample(TutorExample example);

    int deleteByExample(TutorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tutor record);

    int insertSelective(Tutor record);

    List<Tutor> selectByExample(TutorExample example);

    Tutor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tutor record, @Param("example") TutorExample example);

    int updateByExample(@Param("record") Tutor record, @Param("example") TutorExample example);

    int updateByPrimaryKeySelective(Tutor record);

    int updateByPrimaryKey(Tutor record);
    
    
    // 添加的内容
    int insertAndGetId(Tutor tutor);
    
    int insertSelectiveAndGetId(Tutor tutor);
    
    /**
     * 
     * @param paramMap 参数：subjectId, gradeId, distrId, modeId, gender, teachCityId, start, num
     * @return
     */
    List<Tutor> selectByCondition(Map<String, Object> paramMap);

    List<Tutor> selectDetailByCondition(Map<String, Object> paramMap);
    
    Tutor selectDetailById(Integer id);
    
}