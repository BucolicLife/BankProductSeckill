package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.dao.UserMapper;
import com.sanxiangbank.seckill.entity.User;
import com.sanxiangbank.seckill.service.UserService;
import com.sanxiangbank.seckill.util.Common;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public User login(@RequestParam String tel, @RequestParam String password, HttpSession q) {
        System.out.println("tel:"+tel);
        User user=userService.getUser(tel);
        if(user==null||!user.getPassword().equals(password))return null;
        q.setAttribute(Common.CUR_USER,user);
        return user;
    }

    @PostMapping("/user/signup")
    public String login(User user) {
        System.out.println(user);
        return userService.signUp(user) ? "注册成功" : "注册失败";
    }

    @PostMapping("/user/total")
    public String all() {
        String ans = userService.getTotalUser().toString();
        return ans;
    }

}
