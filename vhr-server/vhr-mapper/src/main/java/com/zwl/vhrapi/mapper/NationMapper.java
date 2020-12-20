package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Nation;

import java.util.List;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);

    //获取所有的民族信息
    List<Nation> getAllNation();
}