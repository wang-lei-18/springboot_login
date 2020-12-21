package com.example.demo.controller;

import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.example.demo.entity.AjaxResult.error;
import static com.example.demo.entity.AjaxResult.success;

@Controller
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @ResponseBody
    public AjaxResult login(UserEntity userEntity, String validateCode) {
        AjaxResult ajax = error("用户名或者密码不存在");
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            if (!validateCode.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
                return error("验证码输入错误");
            }
            UserEntity user = userService.selectUserByUsername(userEntity.getUsername());
            if(null != user) {
                if(DigestUtils.sha256Hex(userEntity.getPassword()).equals(user.getPassword())) {
                    // 创建token
                    Map<String, Object> map = userService.createToken(user);
                    ajax = success();
                    for(String key : map.keySet()) {
                        ajax.put(key, map.get(key));
                    }
                }
            }
        } catch (Exception e) {
            ajax = error("用户名或者密码不存在");
            e.printStackTrace();
        }
        return ajax;
    }
}
