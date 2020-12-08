package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.DepartmentMapper;
import com.zwl.vhrapi.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/8 20:18
 **/
@Service
public class DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    //获取所有的部门
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartmentByParentId(-1);
    }

    //添加部门
    public void addDep(Department department) {
        department.setEnabled(true);
        departmentMapper.addDep(department);
    }

    //根据id删除部门
    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }
}
