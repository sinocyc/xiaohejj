package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.TutorOrderExample;
import com.xhtutor.tutor.bean.TutorOrderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TutorOrderMapper {
    long countByExample(TutorOrderExample example);

    int deleteByExample(TutorOrderExample example);

    int deleteByPrimaryKey(TutorOrderKey key);

    int insert(TutorOrderKey record);

    int insertSelective(TutorOrderKey record);

    List<TutorOrderKey> selectByExample(TutorOrderExample example);

    int updateByExampleSelective(@Param("record") TutorOrderKey record, @Param("example") TutorOrderExample example);

    int updateByExample(@Param("record") TutorOrderKey record, @Param("example") TutorOrderExample example);
}