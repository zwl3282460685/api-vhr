package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.EmployeeMapper;
import com.zwl.vhrapi.mapper.NationMapper;
import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.Nation;
import com.zwl.vhrapi.model.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @data 2020/12/13 11:51
 **/
@Service
public class EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    NationMapper nationMapper;

    SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    //获取分页查询数据
    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if(null != page && null != size){
            page = (page -1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, keyword);
        Long total = employeeMapper.getTotal(keyword);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    //添加员工
    public int addEmp(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        Double month = (Double.parseDouble(yearDateFormat.format(endContract)) - Double.parseDouble(yearDateFormat.format(beginContract))) * 12 +
                (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        return employeeMapper.insertSelective(employee);
    }

    //自动生成工号
    public Integer maxWorkId() {
        return employeeMapper.maxWorkId();
    }

    public Integer deleteEmpById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }
}
