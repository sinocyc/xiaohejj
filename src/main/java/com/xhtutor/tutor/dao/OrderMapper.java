package com.xhtutor.tutor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xhtutor.tutor.bean.Order;
import com.xhtutor.tutor.bean.OrderExample;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    
    /**
     * 
     * @param paramMap 参数：subjectId, gradeId, distrId, cityId, start, num
     * @return
     */
    List<Order> selectByCondition(Map<String, Object> paramMap);
    
    List<Order> selectOrderDetailByIds(List<Integer> orderIdList);
    
}