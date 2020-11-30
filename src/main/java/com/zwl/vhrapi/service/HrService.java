package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.HrMapper;
import com.zwl.vhrapi.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HrService implements UserDetailsService {

    @Resource
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(null == hr){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return hr;
    }
}
