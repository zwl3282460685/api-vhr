package com.zwl.vhrapi.utils;

import com.zwl.vhrapi.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author zwl
 * @data 2020/12/9 20:39
 **/
//Hr工具类
public class HrUtils {

    //返回当前用户对象
    public static Hr getCurrentHr(){
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
