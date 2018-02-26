package com.xhtutor.tutor.dao;

import com.xhtutor.tutor.bean.Forte;
import com.xhtutor.tutor.bean.ForteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForteMapper {
    long countByExample(ForteExample example);

    int deleteByExample(ForteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Forte record);

    int insertSelective(Forte record);

    List<Forte> selectByExample(ForteExample example);

    Forte selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Forte record, @Param("example") ForteExample example);

    int updateByExample(@Param("record") Forte record, @Param("example") ForteExample example);

    int updateByPrimaryKeySelective(Forte record);

    int updateByPrimaryKey(Forte record);
}