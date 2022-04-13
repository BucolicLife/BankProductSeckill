package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.controller.RestResponseController.ResultData;
import com.sanxiangbank.seckill.entity.LoanAccessRule;
import com.sanxiangbank.seckill.service.LoanAccessRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/LoanAccessRule")
public class LoanAccessRuleController {

    @Autowired
    LoanAccessRuleService service;

    @RequestMapping("/update")
    public ResultData update(LoanAccessRule rule) {
        log.info("配置贷款准入规则" + rule.toString());
        service.insert(rule);
        return ResultData.success(service.select());
    }

    @RequestMapping("/select")
    public ResultData select() {
        LoanAccessRule rule = service.select();
        log.info("查询贷款准入规则" + rule.toString());
        return ResultData.success(rule);
    }
}
