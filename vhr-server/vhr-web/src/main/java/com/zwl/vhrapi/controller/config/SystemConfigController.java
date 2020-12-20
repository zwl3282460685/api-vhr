package com.zwl.vhrapi.controller.config;

import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
@Api(tags = "用户菜单项接口")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    @ApiOperation("根基用户角色获得相应的菜单项接口")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId();
    }
}
