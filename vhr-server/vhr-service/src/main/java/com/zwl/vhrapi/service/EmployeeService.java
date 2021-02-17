package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.EmployeeMapper;
import com.zwl.vhrapi.mapper.NationMapper;
import com.zwl.vhrapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zwl
 * @data 2020/12/13 11:51
 **/
@Service
public class EmployeeService {

    public final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    NationMapper nationMapper;
    @Autowired
    MailSendLogService mailSendLogService;

    SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    //获取分页查询数据
    public RespPageBean getEmployeeByPage(Integer page, Integer size, Employee emp, Date[] beginDataScope) {
        if(null != page && null != size){
            page = (page -1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, emp, beginDataScope);
        Long total = employeeMapper.getTotal(emp, beginDataScope);
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
        int result = employeeMapper.insertSelective(employee);
        if(result == 1){
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            String msgId = UUID.randomUUID().toString(); //生成rabbitmq消息的id
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgId(msgId);
            mailSendLog.setCreateTime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setEmpId(emp.getId());
            mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
            mailSendLogService.insertMailSendLog(mailSendLog);
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_KEY_NAME,emp, new CorrelationData(msgId)); //向rabbitmq中添加发送邮件的任务
        }
        return result;
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

    public RespPageBean getEmployeeByPageWithSalary(Integer page, Integer size) {
        if(page != null && size != null){
            page = (page - 1) * size;
        }
        List<Employee> list = employeeMapper.getEmployeeByPageWithSalary(page, size);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setTotal(employeeMapper.getTotal(null, null));
        respPageBean.setData(list);
        return respPageBean;

    }

    public Integer updateEmployeeSalaryById(Integer eid, Integer sid) {
        return employeeMapper.updateEmployeeSalaryById(eid, sid);
    }

    /**
     * 批量删除员工信息
     * @param ids
     * @return
     */
    public int deletePositionByIds(Integer[] ids) {
        return employeeMapper.deleteEmpByIds(ids);
    }

    public Employee getEmployeeById(Integer empId) {
        return employeeMapper.getEmployeeById(empId);
    }
}
