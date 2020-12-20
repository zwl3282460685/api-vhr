package com.zwl.vhrapi.config;

import com.zwl.vhrapi.model.Menu;
import com.zwl.vhrapi.model.Role;
import com.zwl.vhrapi.service.MenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 根据用户的请求地址，分析出他所需要的角色
 * @author zwl
 * @data 2020/12/5 15:49
 **/
@Component
public class CustomerFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    MenuService menuService;

    //比对工具类
    AntPathMatcher antPathMatcher = new AntPathMatcher();


    //根据用户输入的rul，分析出所需要的角色并返回。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl(); //获取请求地址
        List<Menu> menus = menuService.getAllMenusWithRole();
        for(Menu menu : menus){
            if(antPathMatcher.match(menu.getUrl(), requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for(int i = 0; i < roles.size(); i++){
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
