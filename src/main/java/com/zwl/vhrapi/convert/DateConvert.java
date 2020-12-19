package com.zwl.vhrapi.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换类
 * @author zwl
 * @data 2020/12/19 16:05
 **/
@Component
public class DateConvert implements Converter<String, Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        try {
            return sdf.parse(s) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

