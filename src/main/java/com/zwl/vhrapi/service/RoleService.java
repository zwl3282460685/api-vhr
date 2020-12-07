package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.RoleMapper;
import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author zwl
 * @data 2020/12/6 21:08
 **/
@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;

    //获取所有的用户角色
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    //在权限组中添加一个角色
    public int addRole(Role role) {
        if(!role.getName().startsWith("Role_")){
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    //根据rid删除权限组中的角色
    public int deleteRoleById(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
