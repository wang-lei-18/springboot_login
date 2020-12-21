package com.example.demo.dao;

import com.example.demo.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    int insertLog(LogEntity logEntity);
}
