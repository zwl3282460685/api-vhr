package com.zwl.vhrapi.controller.salary;

import com.zwl.vhrapi.mapper.SalaryMapper;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.model.Salary;
import com.zwl.vhrapi.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/20 19:24
 **/
@RestController
@RequestMapping("/sal/sob")
@Api(tags = "工资账套管理接口")
public class SalaryController {

    @Resource
    SalaryService salaryService;
    @Resource
    SalaryMapper salaryMapper;

    @GetMapping("/")
    @ApiOperation("获取所有的账套信息")
    public List<Salary> getAllSalary(){
        return salaryMapper.getAllSalary();
    }

    @PostMapping("/")
    @ApiOperation("添加账套信息")
    public RespBean addSalary(@RequestBody Salary salary){
        if(salaryService.addSalary(salary) == 1){
            return RespBean.ok("添加账套信息成功");
        }
        return RespBean.error("添加账套信息失败");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除账套信息")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        if(salaryService.deleteSalaryById(id) == 1){
            return RespBean.ok("账套信息删除成功");
        }
        return RespBean.error("账套信息删除失败！");
    }

    @PutMapping("/")
    @ApiOperation("根据id更新账套信息")
     RespBean updateSalaryById(@RequestBody Salary salary){
        if(salaryService.updateSalaryById(salary) == 1){
            return RespBean.ok("账套信息更新成功");
        }
        return RespBean.error("账套信息更新失败！");
    }
}
