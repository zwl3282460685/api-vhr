package com.zwl.vhrapi.controller;

import com.zwl.vhrapi.config.VerificationCode;
import com.zwl.vhrapi.model.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@Api(tags = "用户登录接口")
public class LoginController {

    @GetMapping(value = "/login")
    @ApiOperation("登录接口")
    //@CrossOrigin("*") 不推荐
    public RespBean login(){
        return RespBean.error("尚未登录，请先登录！");
    }

    @GetMapping("/verifyCode")
    @ApiOperation("验证码生成接口")
    @ResponseBody
    public void verifyCode(HttpSession httpSession, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        httpSession.setAttribute("verify_code", text);
        VerificationCode.output(image, resp.getOutputStream());
    }
}
