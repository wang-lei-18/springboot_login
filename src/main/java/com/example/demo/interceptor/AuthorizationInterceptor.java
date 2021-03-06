package com.example.demo.interceptor;

import com.example.demo.annotation.Login;
import com.example.demo.entity.TokenEntity;
import com.example.demo.exception.TokenException;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        String token = request.getHeader("token");

        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        // token为空
        if (StringUtils.isBlank(token)) {
            throw new TokenException("token不能为空", 401);
        }

        // 查询token信息
        TokenEntity tokenEntity = tokenService.selectTokenByToken(token);
        if (null == tokenEntity || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new TokenException("token失效，请重新登录", 501);
        }

        //设置userId到request里， 后续根据userId， 获取用户信息
        request.setAttribute(USER_KEY, tokenEntity.getUserId());
        return true;
    }
}
