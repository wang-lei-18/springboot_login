package com.example.demo.controller;

import com.example.demo.annotation.Login;
import com.example.demo.annotation.LoginUser;
import com.example.demo.entity.AjaxResult;
import com.example.demo.entity.LogEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("save")
    @Login
    @ResponseBody
    public AjaxResult save(@LoginUser UserEntity userEntity) {
        AjaxResult ajax = AjaxResult.success();
        try {
            LogEntity logEntity = new LogEntity();
            logEntity.setCreateTime(new Date());
            logEntity.setUserId(userEntity.getId());
            logService.insertLog(logEntity);
        } catch (Exception e){
            e.printStackTrace();
            ajax = AjaxResult.error("操作报错");
        }
        return ajax;
    }
}
