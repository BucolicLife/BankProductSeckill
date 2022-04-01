package com.sanxiangbank.seckill.service;

import com.sanxiangbank.seckill.dao.UserMapper;
import com.sanxiangbank.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean signIn(String tel, String password) {
        User user = getUser(tel);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public boolean signUp(User user) {
        if (getUser(user.getTel()) != null)
            return false;
        userMapper.addUser(user);
        return true;
    }

    @Override
    public User getUser(String tel) {
        return userMapper.queryUserByTel(tel);
    }

    @Override
    public List<User> getTotalUser() {
        return userMapper.queryUserList();
    }
}
