package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.PositionMapper;
import com.zwl.vhrapi.model.Position;
import com.zwl.vhrapi.model.RespBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/6 9:32
 **/
@Service
public class PositionService {
    @Resource
    PositionMapper positionMapper;

    //得到所有的职位信息
    public List<Position> getAllPositions(){
        return positionMapper.getAllPosition();
    }

    //添加职位信息
    public Integer addPosition(Position position) {
       position.setEnabled(true);
       position.setCreateDate(new Date());
       return positionMapper.insertSelective(position);
    }

    //更新职位信息
    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    //删除职位信息
    public int deletePosition(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    //批量删除职位信息
    public Integer deletePositionByIds(Integer[] ids) {
        return positionMapper.deletePositionByIds(ids);
    }
}
