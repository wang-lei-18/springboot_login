package com.example.demo.dao;

import com.example.demo.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    int insertUser(TokenEntity tokenEntity);

    int updateUser(TokenEntity tokenEntity);

    TokenEntity selectTokenByUserId(int userId);

    TokenEntity selectTokenByToken(String token);
}
