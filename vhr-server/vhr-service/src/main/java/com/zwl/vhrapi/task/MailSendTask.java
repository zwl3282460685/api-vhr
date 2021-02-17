package com.zwl.vhrapi.task;

import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.MailConstants;
import com.zwl.vhrapi.model.MailSendLog;
import com.zwl.vhrapi.service.EmployeeService;
import com.zwl.vhrapi.service.MailSendLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 邮件发送失败后的重试定时任务
 * @author zwl
 * @data 2021/2/17 12:23
 **/
@Component
public class MailSendTask {
    @Autowired
    MailSendLogService mailSendLogService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    EmployeeService employeeService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void mailResendTask(){
        List<MailSendLog> mailSendLogs = mailSendLogService.getMailSendLogByStatus();
        mailSendLogs.forEach(mailSendLog -> {
            if(mailSendLog.getCount() >= 3){
                mailSendLogService.updateMailSendLogStatus(mailSendLog.getMsgId(), 2); //直接设置该条消息发送失败
            }else{
                mailSendLogService.updateCount(mailSendLog.getMsgId(), new Date());
                Employee emp = employeeService.getEmployeeById(mailSendLog.getEmpId());
                rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(mailSendLog.getMsgId()));
            }
        });
    }
}
