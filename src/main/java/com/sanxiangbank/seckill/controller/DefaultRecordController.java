package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.controller.RestResponseController.ResultData;
import com.sanxiangbank.seckill.service.DefaultRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DefaultRecord")
public class DefaultRecordController {

    @Autowired
    DefaultRecordService defaultRecordService;

    @RequestMapping("/insert")
    public ResultData insert(@RequestParam String IDCardNum) {
        return ResultData.success(defaultRecordService.insert(IDCardNum));
    }

    @RequestMapping("/select")
    public ResultData select(@RequestParam String IDCardNum) {
        return ResultData.success(defaultRecordService.selectByIDCardNum(IDCardNum));
    }

    @RequestMapping("/queryTimes")
    public ResultData queryTimes(@RequestParam String IDCardNum) {
        return ResultData.success(defaultRecordService.queryDefaultTimes(IDCardNum));
    }

    @RequestMapping("/selectTotal")
    public ResultData selectTotal() {
        return ResultData.success(defaultRecordService.selectTotal());
    }

}
