package com.zwl.vhrapi.controller.system;

import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.service.HrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/9 20:33
 **/
@RestController
@RequestMapping("/system/hr")
@Api(tags = "操作员管理接口")
public class HrController {

    @Resource
    HrService hrService;

    @GetMapping("/")
    @ApiOperation("获取所有的hr")
    public List<Hr> getAllHrs(){
        return hrService.getAllHrs();
    }
}
