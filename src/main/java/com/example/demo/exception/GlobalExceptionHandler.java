package com.example.demo.exception;
import com.example.demo.entity.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(TokenException.class)
    public AjaxResult tokenException(TokenException e) {
        AjaxResult ajax = new AjaxResult();
        ajax.put("code", e.getErrNo());
        ajax.put("msg", e.getErrMsg());
        return ajax;
    }
}
