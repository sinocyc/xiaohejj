package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.TutorGradeExample;
import com.xhtutor.tutor.bean.TutorGradeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TutorGradeMapper {
    long countByExample(TutorGradeExample example);

    int deleteByExample(TutorGradeExample example);

    int deleteByPrimaryKey(TutorGradeKey key);

    int insert(TutorGradeKey record);

    int insertSelective(TutorGradeKey record);

    List<TutorGradeKey> selectByExample(TutorGradeExample example);

    int updateByExampleSelective(@Param("record") TutorGradeKey record, @Param("example") TutorGradeExample example);

    int updateByExample(@Param("record") TutorGradeKey record, @Param("example") TutorGradeExample example);
}