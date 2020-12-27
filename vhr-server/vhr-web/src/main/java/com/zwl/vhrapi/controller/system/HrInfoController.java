package com.zwl.vhrapi.controller.system;

import com.zwl.vhrapi.model.Hr;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwl
 * @data 2020/12/27 12:50
 **/
@RestController
@Api(tags = "hr个人中心接口")
public class HrInfoController {

    @GetMapping("/hr/info")
    @ApiOperation("获取当前登录的Hr的信息")
    public Hr getCurrentHr(Authentication authentication){
        return (Hr) authentication.getPrincipal();//获取当前用户
    }
}
