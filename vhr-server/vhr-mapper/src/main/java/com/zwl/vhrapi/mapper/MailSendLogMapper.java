package com.zwl.vhrapi.mapper;

import com.zwl.vhrapi.model.MailSendLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zwl
 * @data 2021/2/17 11:39
 **/
public interface MailSendLogMapper {

    Integer updateMailSendLogStatus(@Param("msgId") String msgId, @Param("status") Integer status);

    Integer insertMailSendLog(MailSendLog mailSendLog);

    List<MailSendLog> getMailSendLogByStatus();

    Integer updateCount(@Param("msgId") String msgId, @Param("date") Date date);
}
