package com.zwl.vhrapi.mapper;


import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.RespPageBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmployeeByPage(@Param("page") Integer page, @Param("size") Integer size,
                                     @Param("emp") Employee employee,
                                     @Param("beginDataScope") Date[] beginDataScope);

    //获取总个数
    Long getTotal(@Param("emp") Employee emp, @Param("beginDataScope") Date[] beginDataScope);

    Integer maxWorkId();

    Integer addEmps(@Param("list") List<Employee> list);

    Employee getEmployeeById(Integer id);

    List<Employee> getEmployeeByPageWithSalary(@Param("page") Integer page, @Param("size") Integer size);
}