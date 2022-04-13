package com.sanxiangbank.seckill.dao;

import com.sanxiangbank.seckill.entity.LoanAccessRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoanMapper {
    public List<LoanAccessRule> select();

    public List<LoanAccessRule> selectByIDCardNum(String id);

}
