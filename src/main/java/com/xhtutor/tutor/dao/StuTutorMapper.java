package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.StuTutorExample;
import com.xhtutor.tutor.bean.StuTutorKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuTutorMapper {
    long countByExample(StuTutorExample example);

    int deleteByExample(StuTutorExample example);

    int deleteByPrimaryKey(StuTutorKey key);

    int insert(StuTutorKey record);

    int insertSelective(StuTutorKey record);

    List<StuTutorKey> selectByExample(StuTutorExample example);

    int updateByExampleSelective(@Param("record") StuTutorKey record, @Param("example") StuTutorExample example);

    int updateByExample(@Param("record") StuTutorKey record, @Param("example") StuTutorExample example);
}