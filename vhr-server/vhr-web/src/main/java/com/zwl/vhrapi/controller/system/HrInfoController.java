package com.zwl.vhrapi.controller.system;

import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.service.HrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zwl
 * @data 2020/12/27 12:50
 **/
@RestController
@Api(tags = "hr个人中心接口")
public class HrInfoController {

    @Autowired
    HrService hrService;

    @GetMapping("/hr/info")
    @ApiOperation("获取当前登录的Hr的信息")
    public Hr getCurrentHr(Authentication authentication){
        return (Hr) authentication.getPrincipal();//获取当前用户
    }

    @PutMapping("/hr/info")
    @ApiOperation("更新Hr个人信息")
    public RespBean updateHrInfo(@RequestBody Hr hr, Authentication authentication){
        if(hrService.updateHr(hr) == 1){
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(hr, authentication.getCredentials(),
                            authentication.getAuthorities()));
            return RespBean.ok("个人信息更新成功！");
        }
        return RespBean.error("个人信息更新失败！");
    }

    @PutMapping("/hr/pass")
    @ApiOperation("修改个人密码")
    public RespBean updateHrPasswd(@RequestBody Map<String, Object> info){
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer hrid = (Integer) info.get("hrid");
        if(hrService.updateHrPasswd(oldPass, pass, hrid)){
            return RespBean.ok("密码修改成功");
        }
        return RespBean.error("密码修改失败");
    }
}
