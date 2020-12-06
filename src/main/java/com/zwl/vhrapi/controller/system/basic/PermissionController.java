package com.zwl.vhrapi.controller.system.basic;

import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.model.Role;
import com.zwl.vhrapi.service.MenuService;
import com.zwl.vhrapi.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限组操作接口
 * @author zwl
 * @data 2020/12/6 21:03
 **/
@RestController
@Api(tags = "权限组操作接口")
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @GetMapping("/")
    @ApiOperation("获取所有的角色信息")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    @ApiOperation("获取所有菜单项")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }
}
