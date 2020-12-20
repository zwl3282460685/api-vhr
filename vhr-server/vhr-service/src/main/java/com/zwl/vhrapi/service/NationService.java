package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.NationMapper;
import com.zwl.vhrapi.model.Nation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 民族
 * @author zwl
 * @data 2020/12/14 14:24
 **/
@Service
public class NationService {

    @Resource
    NationMapper nationMapper;

    public List<Nation> getAllNation() {
        return nationMapper.getAllNation();
    }
}
