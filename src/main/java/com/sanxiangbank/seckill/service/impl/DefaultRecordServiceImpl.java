package com.sanxiangbank.seckill.service.impl;


import com.sanxiangbank.seckill.dao.DefaultRecordMapper;
import com.sanxiangbank.seckill.entity.DefaultRecord;
import com.sanxiangbank.seckill.service.DefaultRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRecordServiceImpl implements DefaultRecordService {

    @Autowired
    DefaultRecordMapper defaultRecordMapper;

    @Override
    public int insert(String idcard) {
        return defaultRecordMapper.insert(idcard);
    }

    @Override
    public List<DefaultRecord> selectTotal() {
        return defaultRecordMapper.selectTotal();
    }

    @Override
    public List<DefaultRecord> selectByIDCardNum(String id) {
        return defaultRecordMapper.selectByIDCardNum(id);
    }

    @Override
    public int queryDefaultTimes(String id) {
        return defaultRecordMapper.queryDefaultTimes(id);
    }
}
