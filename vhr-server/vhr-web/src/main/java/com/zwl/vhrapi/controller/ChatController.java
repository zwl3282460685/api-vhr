package com.zwl.vhrapi.controller;

import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.service.HrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zwl
 * @data 2020/12/24 21:13
 **/
@RestController
@RequestMapping("/chat")
@Api(tags = "聊天页面相关接口")
public class ChatController {

    @Autowired
    HrService hrService;

    @GetMapping("/hrs")
    @ApiOperation("获取所有的Hr")
    public List<Hr> getAllHr(){
        return hrService.getAllHrsExceptCurrentHr();
    }
}
