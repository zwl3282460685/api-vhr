package com.zwl.vhrapi.model;

import java.util.List;

/**
 * 保存分页查询结果信息
 * @author zwl
 * @data 2020/12/13 11:45
 **/
public class RespPageBean {

    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
