package com.example.demo.controller;

import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("registerUser")
    @ResponseBody
    public AjaxResult registerUser(UserEntity user, String validateCode) {
        AjaxResult ajax = success();
        try{
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpSession session = request.getSession();
            if (!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(validateCode)) {
                return error("验证码输入错误");
            }
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            userService.insertUser(user);
            // 创建token
            Map<String, Object> map = userService.createToken(user);
            for(String key : map.keySet()) {
                ajax.put(key, map.get(key));
            }
        } catch (Exception e){
            ajax = error("用户注册失败");
        }
        return ajax;
    }

    @GetMapping()
    public String register() {
        return "register";
    }

    @PostMapping("checkUsername")
    @ResponseBody
    public AjaxResult checkUsername(String username) {
        AjaxResult ajax = success();
        try {
            UserEntity userEntity = userService.selectUserByUsername(username);
            if(null != userEntity) {
                ajax = new AjaxResult("用户名已存在", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajax = error("注册失败！！！");
        }
        return ajax;
    }

}
