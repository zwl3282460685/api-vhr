package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.MenuMapper;
import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    public List<Menu> getMenusByHrId(){
        return menuMapper.getMenusByHrId(((Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
