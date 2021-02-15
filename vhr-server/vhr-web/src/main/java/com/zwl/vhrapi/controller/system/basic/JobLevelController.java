package com.zwl.vhrapi.controller.system.basic;

import com.zwl.vhrapi.model.JobLevel;
import com.zwl.vhrapi.model.Position;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.model.RespPageBean;
import com.zwl.vhrapi.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位职称操作相关的接口
 * @author zwl
 * @data 2020/12/6 15:32
 **/
@RestController
@Api(tags = "职位职称操作相关的接口")
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {

    @Resource
    JobLevelService jobLevelService;

    @PostMapping("/")
    @ApiOperation("添加职位信息")
    public RespBean addJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.addJobLevel(jobLevel) == 1){
            return RespBean.ok("职称添加成功");
        }
        return RespBean.error("职称添加失败");
    }

    @PutMapping("/")
    @ApiOperation("根据id更新职称信息")
    public RespBean updateJobLevel(@RequestBody JobLevel jobLevel){
        if(jobLevelService.updatePosition(jobLevel) == 1){
            return RespBean.ok("职称信息更新成功");
        }
        return RespBean.error("职称信息更新失败！");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除职称信息")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if(jobLevelService.deletePosition(id) == 1){
            return RespBean.ok("删除职称信息成功");
        }
        return RespBean.error("删除职称信息失败");
    }

    @DeleteMapping("/")
    @ApiOperation("批量删除职称信息")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if(jobLevelService.deletePositionByIds(ids) == ids.length){
            return RespBean.ok("批量删除成功");
        }
        return RespBean.error("批量删除失败！");
    }

    @GetMapping("/")
    @ApiOperation("获取职位信息的分页查询数据")
    public RespPageBean getJobLevelByPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer size){
        return jobLevelService.getJobLevelByPage(page, size);

    }
}
