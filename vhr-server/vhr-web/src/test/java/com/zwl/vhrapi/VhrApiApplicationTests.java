package com.zwl.vhrapi;

import com.zwl.vhrapi.mapper.HrMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootTest
class VhrApiApplicationTests {

    @Resource
    HrMapper hrMapper;

    @Test
    void contextLoads() {
        System.out.println(hrMapper.selectByPrimaryKey(5).toString());
    }

}
