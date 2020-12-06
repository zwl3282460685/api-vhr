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
}
