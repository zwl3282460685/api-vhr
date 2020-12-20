package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Politicsstatus;

import java.util.List;

public interface PoliticsstatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Politicsstatus record);

    int insertSelective(Politicsstatus record);

    Politicsstatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Politicsstatus record);

    int updateByPrimaryKey(Politicsstatus record);

    //获取所有的政治面貌
    List<Politicsstatus> getAllPoliticsstatus();
}