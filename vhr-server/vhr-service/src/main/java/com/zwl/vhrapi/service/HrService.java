package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.HrMapper;
import com.zwl.vhrapi.mapper.HrRoleMapper;
import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.utils.HrUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Resource
    HrMapper hrMapper;

    @Resource
    HrRoleMapper hrRoleMapper;

    public List<Hr> getAllHrsExceptCurrentHr() {
        return hrMapper.getAllHrsExceptCurrentHr(HrUtils.getCurrentHr().getId());
    }

    //获取所有的Hr
    public  List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(), keywords);
    }

    //springSecurity从数据库读取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(null == hr){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public int updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public int deleteHrById(Integer id) {
        return hrMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public boolean updateHrRole(Integer hrId, Integer[] rids) {
        hrRoleMapper.deleteByHrId(hrId);
        return hrRoleMapper.addRole(hrId, rids) == rids.length;
    }

    public Boolean updateHrPasswd(String oldPass, String pass, Integer hrid) {
        Hr hr = hrMapper.selectByPrimaryKey(hrid);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(oldPass, hr.getPassword())){
            String encodePass = encoder.encode(pass);
            Integer res = hrMapper.updateHrPasswd(hrid, encodePass);
            if(res == 1){
                return true;
            }
        }
        return false;
    }

    public int updateHrUserFace(String url, Integer id) {
        return hrMapper.updateHrUserFace(url, id);
    }
}
