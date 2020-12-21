package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.SalaryMapper;
import com.zwl.vhrapi.model.Salary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.invoke.SerializedLambda;
import java.util.Date;

/**
 * @author zwl
 * @data 2020/12/20 19:25
 **/
@Service
public class SalaryService {

    @Resource
    SalaryMapper salaryMapper;

    public Integer addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }

    public Integer deleteSalaryById(Integer id) {
         return salaryMapper.deleteByPrimaryKey(id);
    }

    public Integer updateSalaryById(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }
}
