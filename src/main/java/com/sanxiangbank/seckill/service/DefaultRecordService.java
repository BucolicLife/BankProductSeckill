package com.sanxiangbank.seckill.service;


import com.sanxiangbank.seckill.entity.DefaultRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DefaultRecordService {

    public int insert(String idcard);

    public List<DefaultRecord> selectTotal();

    public List<DefaultRecord> selectByIDCardNum(String id);

    public int queryDefaultTimes(String id);

}
