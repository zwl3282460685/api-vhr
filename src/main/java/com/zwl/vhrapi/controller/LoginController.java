package com.zwl.vhrapi.controller;

import com.zwl.vhrapi.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping(value = "/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请先登录！");
    }
}
