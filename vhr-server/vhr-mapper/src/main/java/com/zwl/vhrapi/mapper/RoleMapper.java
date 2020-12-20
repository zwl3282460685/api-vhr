package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //获取所有的用户角色
    List<Role> getAllRoles();

}