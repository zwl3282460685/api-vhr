package com.zwl.vhrapi.controller.salary;

import com.zwl.vhrapi.mapper.SalaryMapper;
import com.zwl.vhrapi.model.Salary;
import com.zwl.vhrapi.service.SalaryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Salary> getAllSalary(){
        return salaryMapper.getAllSalary();
    }
}
