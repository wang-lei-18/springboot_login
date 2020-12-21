package com.example.demo.service.impl;

import com.example.demo.dao.TokenMapper;
import com.example.demo.entity.TokenEntity;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    // token超时时间，单位为分钟（默认30分钟）
    @Value("${token.expireTime}")
    private int tokenExpireTime;

    @Override
    public int insertUser(TokenEntity tokenEntity) {
        return tokenMapper.insertUser(tokenEntity);
    }

    @Override
    public TokenEntity createToken(int userId) {
        // 当前时间
        Date now = new Date();

        // 过期时间
        Date expireTime = new Date(now.getTime() + (60 * tokenExpireTime) * 1000);

        TokenEntity tokenEntity = this.selectTokenByUserId(userId);

        // 生成token
        String token = generateToken();

        if(null == tokenEntity) {
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenMapper.insertUser(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setUpdateTime(now);
            tokenMapper.updateUser(tokenEntity);
        }


        return tokenEntity;
    }

    @Override
    public int updateUser(TokenEntity tokenEntity) {
        return tokenMapper.updateUser(tokenEntity);
    }

    @Override
    public TokenEntity selectTokenByUserId(int userId) {
        return tokenMapper.selectTokenByUserId(userId);
    }

    @Override
    public TokenEntity selectTokenByToken(String token) {
        return tokenMapper.selectTokenByToken(token);
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
