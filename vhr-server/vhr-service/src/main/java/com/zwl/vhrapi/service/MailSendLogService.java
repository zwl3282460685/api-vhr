package com.zwl.vhrapi.service;

import com.zwl.vhrapi.mapper.MailSendLogMapper;
import com.zwl.vhrapi.model.MailSendLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @data 2021/2/17 11:42
 **/
@Service
public class MailSendLogService {

    @Autowired
    MailSendLogMapper mailSendLogMapper;
    public Integer updateMailSendLogStatus(String msgId, Integer status) {
        return mailSendLogMapper.updateMailSendLogStatus(msgId, status);
    }

    public Integer insertMailSendLog(MailSendLog mailSendLog) {
        return mailSendLogMapper.insertMailSendLog(mailSendLog);
    }

    public List<MailSendLog> getMailSendLogByStatus() {
        return mailSendLogMapper.getMailSendLogByStatus();
    }

    public Integer updateCount(String msgId, Date date) {
        return mailSendLogMapper.updateCount(msgId, date);
    }
}
