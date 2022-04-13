package com.sanxiangbank.seckill.service.impl;

import com.sanxiangbank.seckill.dao.LoanMapper;
import com.sanxiangbank.seckill.entity.Loan;
import com.sanxiangbank.seckill.entity.LoanAccessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService implements com.sanxiangbank.seckill.service.LoanService {

    @Autowired
    LoanMapper loanMapper;

    @Override
    public List<LoanAccessRule> select() {
        return loanMapper.select();
    }

    @Override
    public List<LoanAccessRule> selectByIDCardNum(String id) {
        return loanMapper.selectByIDCardNum(id);
    }
}
