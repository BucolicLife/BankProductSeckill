package com.sanxiangbank.seckill.dao;

import com.sanxiangbank.seckill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    User queryUserById(int id);

    User queryUserByTel(String tel);

    int addUser(User user);

    List<User> queryUserList();

}
