package com.sanxiangbank.seckill.service;

import com.sanxiangbank.seckill.dao.UserMapper;
import com.sanxiangbank.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    public boolean signIn(String tel, String password);

    public boolean signUp(User user);

    public User getUser(String tel);

    List<User> getTotalUser();

}
