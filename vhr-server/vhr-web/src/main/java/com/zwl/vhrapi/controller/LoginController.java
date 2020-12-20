package com.zwl.vhrapi.controller;

import com.zwl.vhrapi.model.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户登录接口")
public class LoginController {

    @GetMapping(value = "/login")
    @ApiOperation("登录接口")
    public RespBean login(){
        return RespBean.error("尚未登录，请先登录！");
    }
}
