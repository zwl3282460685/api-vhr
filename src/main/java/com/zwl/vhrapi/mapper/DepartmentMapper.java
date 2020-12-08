package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    //获取所有的部门
    List<Department> getAllDepartmentByParentId(int i);

    //添加部门
    void addDep(Department department);

    void deleteDepById(Department dep);
}