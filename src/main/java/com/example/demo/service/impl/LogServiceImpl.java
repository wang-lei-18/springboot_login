package com.example.demo.service.impl;

import com.example.demo.dao.LogMapper;
import com.example.demo.entity.LogEntity;
import com.example.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int insertLog(LogEntity logEntity) {
        return logMapper.insertLog(logEntity);
    }
}
