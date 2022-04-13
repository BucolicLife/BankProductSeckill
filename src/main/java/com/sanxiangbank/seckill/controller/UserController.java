package com.sanxiangbank.seckill.controller;

import com.sanxiangbank.seckill.controller.RestResponseController.ResultData;
import com.sanxiangbank.seckill.controller.RestResponseController.ReturnCode;
import com.sanxiangbank.seckill.entity.User;
import com.sanxiangbank.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResultData login(@RequestParam String tel, @RequestParam String password) {
        //获取一个用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(tel, password);
        try {
            subject.login(token);//执行登录的方法，如果没有异常就说明ok了
            User r = userService.getUser(tel);
            return ResultData.success(r);
        } catch (UnknownAccountException e) {//用户名不存在
            return ResultData.fail(ReturnCode.USERNAME_OR_PASSWORD_ERROR);
        } catch (IncorrectCredentialsException e) {//密码不存在
            return ResultData.fail(ReturnCode.USERNAME_OR_PASSWORD_ERROR);
        }
    }

    @PostMapping("/user/signup")
    public ResultData registry(User user) {
        log.info("注册新用户{}", user);
        if (userService.exist(user.getTel())) {
            return ResultData.fail(ReturnCode.USER_EXISTED);
        }
        if (userService.signUp(user)) {
            return ResultData.success(user);
        } else {
            return ResultData.fail(ReturnCode.RC999);
        }
    }

    @PostMapping("/user/total")
    public ResultData all() {
        return ResultData.success(userService.getTotalUser());
    }

    @PostMapping("/user/exist")
    public boolean exist(@RequestParam String tel) {
        return userService.exist(tel);
    }
}
