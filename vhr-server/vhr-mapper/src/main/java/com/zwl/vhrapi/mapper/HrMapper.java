package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Hr;
import com.zwl.vhrapi.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

    List<Hr> getAllHrs(@Param("hrId") Integer hrId, @Param("keywords")String keywords);

    List<Hr> getAllHrsExceptCurrentHr(Integer id);

    Integer updateHrPasswd(@Param("hrid") Integer hrid, @Param("encodePass") String encodePass);

    int updateHrUserFace(String url, Integer id);
}