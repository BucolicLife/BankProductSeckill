package com.sanxiangbank.seckill.dao;

import com.sanxiangbank.seckill.entity.StockOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    StockOrder selectByPrimaryKey(Integer id);

    List<StockOrder> selectByUserID(Integer UserId);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}