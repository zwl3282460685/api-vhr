package com.zwl.vhrapi.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.security.auth.login.Configuration;
import java.util.Collection;

/**
 * 判断当前用户是否具备访问接口所需的角色
 * @author zwl
 * @data 2020/12/5 16:23
 **/
@Component
public class CustomerUrlDecisionManager implements AccessDecisionManager {

    /**
     * 分析所访问地址需要的角色
     * @param authentication 用户信息
     * @param object
     * @param configAttributes MyFilter类方法返回的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        for(ConfigAttribute configAttribute : configAttributes){
            String needRole = configAttribute.getAttribute();    //用户访问url所需的角色
            if("ROLE_LOGIN".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken){ //未登录情况
                    throw new AccessDeniedException("尚未登录");
                }else{
                    return;
                }
            }

            //获取当前登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
