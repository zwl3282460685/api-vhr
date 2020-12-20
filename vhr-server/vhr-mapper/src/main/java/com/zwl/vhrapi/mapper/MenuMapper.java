package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusByHrId(Integer hrId);

    List<Menu> getAllMenusWithRole();

    //获取多有的菜单项
    List<Menu> getAllMenus();

    //根据角色id获取相应操作的权限列表的id
    List<Integer> getMidsByRid(Integer rid);
}