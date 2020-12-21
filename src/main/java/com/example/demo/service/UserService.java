package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.Map;

public interface UserService {

    int insertUser(UserEntity userEntity);

    UserEntity selectUserByUsername(String username);

    UserEntity selectUserById(int id);

    Map createToken(UserEntity userEntity);

}
