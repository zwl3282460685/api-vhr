package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.JobLevelMapper;
import com.zwl.vhrapi.model.JobLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO
 * 职称操作的service
 * @author zwl
 * @data 2020/12/6 15:37
 **/
@Service
public class JobLevelService {

    @Resource
    JobLevelMapper jobLevelMapper;

    //获取所有的职称信息
    public List<JobLevel> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    //添加职称信息
    public int addJobLevel(JobLevel jobLevel) {
        jobLevel.setEnabled(true);
        jobLevel.setCreateDate(new Date());
        return jobLevelMapper.insertSelective(jobLevel);
    }

    //根据id更新职称信息
    public int updatePosition(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    //根据id删除职称信息
    public int deletePosition(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    //批量删除职称信息
    public int deletePositionByIds(Integer[] ids) {
        return jobLevelMapper.deleteByIds(ids);
    }
}
