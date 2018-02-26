package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.TutorModeExample;
import com.xhtutor.tutor.bean.TutorModeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TutorModeMapper {
    long countByExample(TutorModeExample example);

    int deleteByExample(TutorModeExample example);

    int deleteByPrimaryKey(TutorModeKey key);

    int insert(TutorModeKey record);

    int insertSelective(TutorModeKey record);

    List<TutorModeKey> selectByExample(TutorModeExample example);

    int updateByExampleSelective(@Param("record") TutorModeKey record, @Param("example") TutorModeExample example);

    int updateByExample(@Param("record") TutorModeKey record, @Param("example") TutorModeExample example);
}