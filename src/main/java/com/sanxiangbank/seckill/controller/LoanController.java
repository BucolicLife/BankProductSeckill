package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.controller.RestResponseController.ResultData;
import com.sanxiangbank.seckill.controller.RestResponseController.ReturnCode;
import com.sanxiangbank.seckill.entity.LoanAccessRule;
import com.sanxiangbank.seckill.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("loan")
public class LoanController {
    @Autowired
    LoanService service;

    @RequestMapping("/selectTotal")
    public ResultData selectTotal() {
        return ResultData.success(service.select());
    }

    @RequestMapping("/selectByID")
    public ResultData selectByID(@RequestParam String id) {
        return ResultData.success(service.selectByIDCardNum(id));
    }

    @RequestMapping("/apply/{uid}")
    public ResultData apply(@PathVariable String uid) {
        log.info("申请人身份证 " + uid);
        Boolean ans = service.apply(uid);
        if (ans) {
            return ResultData.success("资格审核通过，申请成功！");
        } else {
            return ResultData.fail(ReturnCode.RC999);
        }

    }

}
