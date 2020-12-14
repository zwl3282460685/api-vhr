package com.zwl.vhrapi.controller.emp;

import com.zwl.vhrapi.model.*;
import com.zwl.vhrapi.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    NationService nationService;

    @Resource
    PoliticsstatusService politicsstatusService;

    @Resource
    JobLevelService jobLevelService;

    @Resource
    PositionService positionService;

    @Resource
    DepartmentService departmentService;

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

    @GetMapping("/nations")
    @ApiOperation("获取所有的民族信息")
    public List<Nation> getAllNation(){
        return nationService.getAllNation();
    }

    @GetMapping("/politicsstatus")
    @ApiOperation("获取政治面貌信息")
    public List<Politicsstatus> getPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/jobLevels")
    @ApiOperation("获取所有的职称信息")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    @ApiOperation("获取所有的职位信息")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkId")
    @ApiOperation("获取自动生成的工号")
    public RespBean maxWorkId(){
        RespBean resBean = RespBean.build().setStatus(200).
                setObj(String.format("%08d", employeeService.maxWorkId() + 1));
        return resBean;
    }

    @GetMapping("/deps")
    @ApiOperation("获取所有的部门信息")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartment();
    }
}
