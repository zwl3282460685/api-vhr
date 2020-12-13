package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.EmployeeMapper;
import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/13 11:51
 **/
@Service
public class EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    //获取分页查询数据
    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if(null != page && null != size){
            page = (page -1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, keyword);
        Long total = employeeMapper.getTotal(keyword);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    //添加员工
    public int addEmp(Employee employee) {
        return employeeMapper.insertSelective(employee);
    }
}
