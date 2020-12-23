package com.zwl.vhrapi.controller.salary;

import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.model.RespPageBean;
import com.zwl.vhrapi.model.Salary;
import com.zwl.vhrapi.service.EmployeeService;
import com.zwl.vhrapi.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zwl
 * @data 2020/12/22 20:39
 **/
@RestController
@RequestMapping("/salary/sobcfg")
@Api(tags = "员工薪资账套管理接口")
public class SobConfigController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SalaryService salaryService;

    @GetMapping("/")
    @ApiOperation("获取所有员工工资的分页信息")
    public RespPageBean getEmployeeByPageWithSalary(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeByPageWithSalary(page, size);
    }

    @GetMapping("/salaries")
    @ApiOperation("获取所有的账套信息")
    public List<Salary> getAllSalaries(){
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    @ApiOperation("修改员工账套信息")
    public RespBean updateEmployeeSalaryById(Integer eid, Integer sid){
        if(employeeService.updateEmployeeSalaryById(eid, sid) > 0){
            return RespBean.ok("员工账套信息修改成功");
        }
        return RespBean.error("员工账套信息修改失败！");
    }
}
