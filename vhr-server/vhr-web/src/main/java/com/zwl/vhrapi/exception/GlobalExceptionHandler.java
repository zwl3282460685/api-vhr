package com.zwl.vhrapi.exception;

import com.zwl.vhrapi.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * 全局异常处理
 * @author zwl
 * @data 2020/12/6 10:57
 **/
//@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有其他关联数据，操作失败");
        }
        return RespBean.error("数据库异常，请联系管理员！");
    }
}
