package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.Mode;
import com.xhtutor.tutor.bean.ModeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModeMapper {
    long countByExample(ModeExample example);

    int deleteByExample(ModeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mode record);

    int insertSelective(Mode record);

    List<Mode> selectByExample(ModeExample example);

    Mode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mode record, @Param("example") ModeExample example);

    int updateByExample(@Param("record") Mode record, @Param("example") ModeExample example);

    int updateByPrimaryKeySelective(Mode record);

    int updateByPrimaryKey(Mode record);
}