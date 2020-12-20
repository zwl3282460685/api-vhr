package com.zwl.vhrapi.controller.system.basic;

import com.zwl.vhrapi.model.Department;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理接口
 * @author zwl
 * @data 2020/12/8 20:13
 **/
@RestController
@RequestMapping("/system/basic/department")
@Api(tags = "部门管理接口")
public class DepartmentController {

    @Resource
    DepartmentService departmentService;

    @GetMapping("/")
    @ApiOperation("获取所有的部门")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @PostMapping("/")
    @ApiOperation("添加部门")
    public RespBean addDep(@RequestBody Department department){
        departmentService.addDep(department);
        if(department.getResult() == 1){
            return RespBean.ok("部门添加成功",department);
        }
        return RespBean.error("部门添加失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除部门")
    public RespBean deleteDepById(@PathVariable Integer id){
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepById(dep);
        if(dep.getResult() == -2){
            return RespBean.error("该部门下有子部门，部门删除失败");
        }else if(dep.getResult() == -1){
            return RespBean.error("该部门下员工，部门删除失败");
        }else if(dep.getResult() == 1){
            return RespBean.ok("部门删除成功");
        }
        return RespBean.error("部门删除失败");
    }
}
