package com.sanxiangbank.seckill.service.impl;

import com.sanxiangbank.seckill.dao.LoanAccessRuleMapper;
import com.sanxiangbank.seckill.dao.LoanMapper;
import com.sanxiangbank.seckill.entity.Loan;
import com.sanxiangbank.seckill.entity.LoanAccessRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoanServiceImpl implements com.sanxiangbank.seckill.service.LoanService {

    @Autowired
    LoanMapper loanMapper;

    @Autowired
    LoanAccessRuleMapper loanAccessRuleMapper;

    @Override
    public List<LoanAccessRule> select() {
        return loanMapper.select();
    }

    @Override
    public List<LoanAccessRule> selectByIDCardNum(String id) {
        return loanMapper.selectByIDCardNum(id);
    }

    @Override
    public Boolean isQualified(String uid) {
        LoanAccessRule rule = loanAccessRuleMapper.select();
        log.info(rule.toString());
        int ans = loanMapper.isQualified(uid, rule);
        log.info("逾期记录{}", ans);
        return ans < rule.getTimes();
    }

    @Override
    public boolean apply(String uid) {
        return isQualified(uid);
    }


}
