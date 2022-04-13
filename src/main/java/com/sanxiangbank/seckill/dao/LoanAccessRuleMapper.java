package com.sanxiangbank.seckill.dao;

import com.sanxiangbank.seckill.entity.LoanAccessRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoanAccessRuleMapper {
    public int insert(LoanAccessRule rule);
    public LoanAccessRule select();
}
