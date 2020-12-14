package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.PoliticsstatusMapper;
import com.zwl.vhrapi.model.Politicsstatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 政治面貌
 * @author zwl
 * @data 2020/12/14 14:27
 **/
@Service
public class PoliticsstatusService {

    @Resource
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusMapper.getAllPoliticsstatus() ;
    }
}
