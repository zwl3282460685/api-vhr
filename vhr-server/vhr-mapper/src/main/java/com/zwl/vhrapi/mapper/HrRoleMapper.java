package com.zwl.vhrapi.mapper;

import com.zwl.vhrapi.model.HrRole;
import org.apache.ibatis.annotations.Param;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKey(HrRole record);

    //删除hr的角色
    void deleteByHrId(Integer hrId);

    //增加hr的角色
    Integer addRole(@Param("hrId") Integer hrId, @Param("rids") Integer[] rids);
}