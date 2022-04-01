package com.sanxiangbank.seckill.config;

import com.sanxiangbank.seckill.util.Common;
import org.aopalliance.intercept.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object curUser = request.getSession().getAttribute(Common.CUR_USER);
        if (curUser == null) {
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }
        return true;
    }

}
