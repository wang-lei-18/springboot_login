package com.example.demo.service;

import com.example.demo.entity.TokenEntity;
import com.example.demo.entity.UserEntity;

import java.util.Map;

public interface TokenService {
    int insertUser(TokenEntity tokenEntity);

    TokenEntity createToken(int userId);

    int updateUser(TokenEntity tokenEntity);

    TokenEntity selectTokenByUserId(int userId);

    TokenEntity selectTokenByToken(String token);
}
