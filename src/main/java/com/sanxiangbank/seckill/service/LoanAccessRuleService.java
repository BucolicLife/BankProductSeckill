package com.sanxiangbank.seckill.service;

public interface LoanAccessRuleService {
    public int insert(com.sanxiangbank.seckill.entity.LoanAccessRule rule);
    public com.sanxiangbank.seckill.entity.LoanAccessRule select();
}
