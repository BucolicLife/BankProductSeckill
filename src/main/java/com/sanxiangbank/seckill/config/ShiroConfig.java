package com.sanxiangbank.seckill.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //3. shiroFilterFactoryBean

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
//        anon： 无需认证就可以访问
//        authc： 必须认证了才能访问
//        user： 必须拥有记住我功能才能用
//        perms： 拥有对某个资源的权限才能访问
//        role： 拥有某个角色权限
//        filterMap.put("/user/login","anon");
//        filterMap.put("/user/signup","anon");
//        filterMap.put("/user/exist","anon");
//        filterMap.put("/user/*","authc");
//        filterMap.put("/myorder","authc");
//        filterMap.put("/myinfo","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/");
        return bean;
    }

    //2. DefaultWebSecurityManager

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //1. 创建realm对象，需要自定义类

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}