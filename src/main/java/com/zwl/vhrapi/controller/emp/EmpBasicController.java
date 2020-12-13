package com.zwl.vhrapi.controller.emp;

import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.model.RespPageBean;
import com.zwl.vhrapi.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zwl
 * @data 2020/12/13 11:44
 **/
@RestController
@RequestMapping("/emp/basic")
@Api(tags = "员工基本资料接口")
public class EmpBasicController {

    @Resource
    EmployeeService employeeService;

    @GetMapping("/")
    @ApiOperation("获取员工信息的分页查询数据及按关键字搜索")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size,
                                          String keyword){
        return employeeService.getEmployeeByPage(page, size, keyword);
    }

    @PostMapping("/")
    @ApiOperation("添加员工接口")
    public RespBean addEmp(@RequestBody Employee employee){
        if(employeeService.addEmp(employee) == 1){
            return RespBean.ok("员工添加成功！");
        }
        return RespBean.error("员工添加失败！");
    }
}
