package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.MenuMapper;
import com.zwl.vhrapi.mapper.MenuRoleMapper;
import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.model.Menu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    @Resource
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().
                getPrincipal()).getId());
    }

    //根据url获取访问菜单所需要的角色
    //@Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    //获取所有的菜单项
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    //根据角色id获取相应操作的权限列表的id
    public List<Integer> getMidsByRid(Integer rid) {
        return menuMapper.getMidsByRid(rid);
    }

    //更新权限组中角色的权限
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        if(null == mids || mids.length == 0){
            return true;
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result == mids.length;
    }
}
