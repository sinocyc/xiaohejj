package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.TutorSubjectExample;
import com.xhtutor.tutor.bean.TutorSubjectKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TutorSubjectMapper {
    long countByExample(TutorSubjectExample example);

    int deleteByExample(TutorSubjectExample example);

    int deleteByPrimaryKey(TutorSubjectKey key);

    int insert(TutorSubjectKey record);

    int insertSelective(TutorSubjectKey record);

    List<TutorSubjectKey> selectByExample(TutorSubjectExample example);

    int updateByExampleSelective(@Param("record") TutorSubjectKey record, @Param("example") TutorSubjectExample example);

    int updateByExample(@Param("record") TutorSubjectKey record, @Param("example") TutorSubjectExample example);
}