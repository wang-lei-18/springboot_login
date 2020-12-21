package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.TokenEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public int insertUser(UserEntity userEntity) {
        return userMapper.insertUser(userEntity);
    }

    @Override
    public UserEntity selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public UserEntity selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 生成token
     * @param userEntity
     * @return
     */
    @Override
    public Map createToken(UserEntity userEntity) {
        // 获取登录token
        TokenEntity tokenEntity = tokenService.createToken(userEntity.getId());

        Map<String, Object> map = new HashMap<>();
        map.put("userid", userEntity.getId());
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime());
        return map;
    }
}


