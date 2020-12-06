package com.zwl.vhrapi.controller.system.basic;

import com.zwl.vhrapi.model.Position;
import com.zwl.vhrapi.model.RespBean;
import com.zwl.vhrapi.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.geometry.Pos;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 职位管理
 * @author zwl
 * @data 2020/12/6 9:28
 **/
@RestController
@RequestMapping("/system/basic/pos")
@Api(tags = "职位信息接口")
public class PositionController {

    @Resource
    PositionService positionService;

    @GetMapping("/")
    @ApiOperation("获取所有的职位信息")
    public List<Position> getAllPosition(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    @ApiOperation("添加职位信息")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    @ApiOperation("根据id更新职位信息")
    public RespBean updatePosition(@RequestBody  Position position){
        if(positionService.updatePosition(position) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除职位信息")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.deletePosition(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    @ApiOperation("批量删除职位信息")
    public RespBean deletePositionByIds(Integer[] ids){
        if(positionService.deletePositionByIds(ids) == ids.length){
            return RespBean.ok("批量删除成功");
        }
        return RespBean.error("批量删除失败！");
    }
}
