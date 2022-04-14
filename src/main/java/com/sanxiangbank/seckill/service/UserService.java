package com.sanxiangbank.seckill.service;

import com.sanxiangbank.seckill.dao.UserMapper;
import com.sanxiangbank.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    public boolean signIn(String tel, String password);

    public boolean signUp(User user) throws Exception;

    public User getUser(String tel);

    List<User> getTotalUser();

    boolean exist(String tel);
    /**
     * 获取用户验证Hash
     * @param sid
     * @param userId
     * @return
     * @throws Exception
     */
    public String getVerifyHash(Integer sid, Integer userId) throws Exception;

    /**
     * 添加用户访问次数
     * @param userId
     * @return
     * @throws Exception
     */
    public int addUserCount(Integer userId) throws Exception;

    /**
     * 检查用户是否被禁
     * @param userId
     * @return
     */
    public boolean getUserIsBanned(Integer userId);
}
