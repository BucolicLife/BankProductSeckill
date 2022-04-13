package com.sanxiangbank.seckill.service;

import com.sanxiangbank.seckill.entity.LoanAccessRule;

import java.util.List;

public interface LoanService {
    public List<LoanAccessRule> select();

    public List<LoanAccessRule> selectByIDCardNum(String id);
}
