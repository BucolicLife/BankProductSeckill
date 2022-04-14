package com.sanxiangbank.seckill.config;

import com.sanxiangbank.seckill.entity.User;
import com.sanxiangbank.seckill.service.UserService;
import com.sanxiangbank.seckill.util.Common;
import com.sanxiangbank.seckill.util.Sm3Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
@Slf4j
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        // 用户名、密码， 数据中取
        User user = userService.getUser(userToken.getUsername());
        if (user == null) return null;
        // 密码认证，shiro做
        return new SimpleAuthenticationInfo("", user.getPassword(), "");
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //自定义认证加密方式
        CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
        // 设置自定义认证加密方式
        super.setCredentialsMatcher(customCredentialsMatcher);
    }

    /**
     * 自定义认证加密方式 使用国标SM3加密算法
     */
    public static class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
        @SneakyThrows
        @Override
        public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            Object accountCredentials = getCredentials(info);
            String p1 = Sm3Util.sm3bcHex((String.valueOf(token.getPassword()) + Common.PASSWORD_SALT).getBytes()), p2 = accountCredentials.toString();
//            log.info("{},{}", p1, p2);
            return p1.equals(p2);
        }
    }
}