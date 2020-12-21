package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * 登录校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
