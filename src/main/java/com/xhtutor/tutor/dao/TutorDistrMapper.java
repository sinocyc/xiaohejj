package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.TutorDistrExample;
import com.xhtutor.tutor.bean.TutorDistrKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TutorDistrMapper {
    long countByExample(TutorDistrExample example);

    int deleteByExample(TutorDistrExample example);

    int deleteByPrimaryKey(TutorDistrKey key);

    int insert(TutorDistrKey record);

    int insertSelective(TutorDistrKey record);

    List<TutorDistrKey> selectByExample(TutorDistrExample example);

    int updateByExampleSelective(@Param("record") TutorDistrKey record, @Param("example") TutorDistrExample example);

    int updateByExample(@Param("record") TutorDistrKey record, @Param("example") TutorDistrExample example);
}