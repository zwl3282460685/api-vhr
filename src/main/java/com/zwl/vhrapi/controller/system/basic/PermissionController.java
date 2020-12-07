package com.zwl.vhrapi.controller.system.basic;

import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.model.Role;
import com.zwl.vhrapi.service.MenuService;
import com.zwl.vhrapi.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/mid/{rid}")
    @ApiOperation("根据角色id获取相应操作的权限列表的id")
    public List<Integer> getMidsByRid(@PathVariable("rid") Integer rid){
        return menuService.getMidsByRid(rid);
    }

    @PutMapping("/")
    @ApiOperation("更新权限组中角色的权限")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        if(menuService.updateMenuRole(rid, mids)){
            return RespBean.ok("权限更新成功");
        }
        return RespBean.error("权限更新失败");
    }

    @PostMapping("/role")
    @ApiOperation("在权限组中添加一个角色")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1){
            return RespBean.ok("角色添加成功");
        }
        return RespBean.error("角色添加失败！");
    }

    @DeleteMapping("/role/{rid}")
    @ApiOperation("根据rid删除权限组中的角色")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if(roleService.deleteRoleById(rid) == 1){
            return RespBean.ok("角色删除成功！");
        }
        return RespBean.error("角色删除失败！");
    }
}
