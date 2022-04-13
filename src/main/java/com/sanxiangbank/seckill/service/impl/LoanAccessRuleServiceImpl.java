package com.sanxiangbank.seckill.service.impl;

import com.sanxiangbank.seckill.dao.LoanAccessRuleMapper;
import com.sanxiangbank.seckill.entity.LoanAccessRule;
import com.sanxiangbank.seckill.service.LoanAccessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanAccessRuleServiceImpl implements LoanAccessRuleService {
    @Autowired
    LoanAccessRuleMapper loanAccessRuleMapper;

    @Override
    public int insert(LoanAccessRule rule) {
        return loanAccessRuleMapper.insert(rule);
    }

    @Override
    public LoanAccessRule select() {
        return loanAccessRuleMapper.select();
    }
}
