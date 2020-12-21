package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(UserEntity userEntity);

    UserEntity selectUserByUsername(String username);

    UserEntity selectUserById(int id);
}
