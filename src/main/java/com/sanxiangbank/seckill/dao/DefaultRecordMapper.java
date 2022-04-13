package com.sanxiangbank.seckill.dao;

import com.sanxiangbank.seckill.entity.DefaultRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DefaultRecordMapper {
    public int insert(@Param("id") String id);

    public List<DefaultRecord> selectTotal();

    public List<DefaultRecord> selectByIDCardNum(@Param("id") String id);
    public int queryDefaultTimes(@Param("id") String id);


}
