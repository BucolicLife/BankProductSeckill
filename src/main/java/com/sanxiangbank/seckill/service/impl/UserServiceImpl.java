package com.sanxiangbank.seckill.service.impl;

import com.sanxiangbank.seckill.dao.CacheKey;
import com.sanxiangbank.seckill.dao.UserMapper;
import com.sanxiangbank.seckill.entity.Stock;
import com.sanxiangbank.seckill.entity.User;
import com.sanxiangbank.seckill.service.StockService;
import com.sanxiangbank.seckill.service.UserService;
import com.sanxiangbank.seckill.util.Common;
import com.sanxiangbank.seckill.util.Sm3Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String SALT = "randomString";
    private static final int ALLOW_COUNT = 10;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private StockService stockService;

    @Override
    public boolean signIn(String tel, String password) {
        User user = getUser(tel);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    @Override
    public boolean signUp(User user) throws Exception {
        if (exist(user.getTel())) {
            return false;
        }
        user.setPassword(Sm3Util.sm3bcHex((user.getPassword() + Common.PASSWORD_SALT).getBytes()));
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

    @Override
    public boolean exist(String tel) {
        return getUser(tel) != null;
    }


    @Override
    public String getVerifyHash(Integer sid, Integer userId) throws Exception {

        // ??????????????????????????????
        LOGGER.info("???????????????????????????????????????");


        // ?????????????????????
        User user = userMapper.queryUserById(userId);
        if (user == null) {
            throw new Exception("???????????????");
        }
        LOGGER.info("???????????????[{}]", user.toString());

        // ?????????????????????
        Stock stock = stockService.getStockById(sid);
        if (stock == null) {
            throw new Exception("???????????????");
        }
        LOGGER.info("???????????????[{}]", stock.toString());

        // ??????hash
        String verify = SALT + sid + userId;
        String verifyHash = DigestUtils.md5DigestAsHex(verify.getBytes());

        // ???hash???????????????????????????redis
        String hashKey = CacheKey.HASH_KEY.getKey() + "_" + sid + "_" + userId;
        stringRedisTemplate.opsForValue().set(hashKey, verifyHash, 3600, TimeUnit.SECONDS);
        LOGGER.info("Redis?????????[{}] [{}]", hashKey, verifyHash);
        return verifyHash;
    }

    @Override
    public int addUserCount(Integer userId) throws Exception {
        String limitKey = CacheKey.LIMIT_KEY.getKey() + "_" + userId;
        stringRedisTemplate.opsForValue().setIfAbsent(limitKey, "0", 3600, TimeUnit.SECONDS);
        Long limit = stringRedisTemplate.opsForValue().increment(limitKey);
        return Integer.parseInt(String.valueOf(limit));
    }

    @Override
    public boolean getUserIsBanned(Integer userId) {
        String limitKey = CacheKey.LIMIT_KEY.getKey() + "_" + userId;
        String limitNum = stringRedisTemplate.opsForValue().get(limitKey);
        if (limitNum == null) {
            LOGGER.error("?????????????????????????????????????????????????????????");
            return true;
        }
        return Integer.parseInt(limitNum) > ALLOW_COUNT;
    }
}
