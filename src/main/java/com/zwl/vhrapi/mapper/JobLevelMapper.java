package com.zwl.vhrapi.mapper;

import com.zwl.vhrapi.model.JobLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobLevelMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    //获取所有的职称信息
    List<JobLevel> getAllJobLevels();

    //批量删除职称信息
    int deleteByIds(@Param("ids") Integer[] ids);
}