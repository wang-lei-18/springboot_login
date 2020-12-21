package com.example.demo.controller;

import com.example.demo.annotation.Login;
import com.example.demo.annotation.LoginUser;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.demo.entity.AjaxResult.success;

@Controller
@RequestMapping("/api")
public class HelloController {

    @GetMapping("")
    public String index() {
        return "login";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("hello2")
    public String hello2() {
        return "hello2";
    }

}
