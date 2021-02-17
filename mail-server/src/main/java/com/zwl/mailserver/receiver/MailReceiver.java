package com.zwl.mailserver.receiver;

import com.rabbitmq.client.Channel;
import com.zwl.vhrapi.model.Employee;
import com.zwl.vhrapi.model.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * @author zwl
 * @data 2020/12/20 15:56
 **/
@Component
public class MailReceiver {
    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Resource
    JavaMailSender javaMailSender;
    @Resource
    MailProperties mailProperties;
    @Resource
    TemplateEngine templateEngine;

    @Autowired
    StringRedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) throws IOException {
        Employee emp = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG); //消息的标记，确认消息时会用用到
        String msgId = (String) headers.get("spring_returned_message_correlation");
        if(redisTemplate.opsForHash().entries("mail_log").containsKey(msgId)){
            //redis 中包含该key，说明该消息已被消费过
            logger.info(msgId + ":消息已经被消费");
            channel.basicAck(tag, false); //确认消息已消费
            return;
        }
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo(emp.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", emp.getName());
            context.setVariable("posName", emp.getPosition().getName());
            context.setVariable("jobLevelName", emp.getJobLevel().getName());
            context.setVariable("departmentName", emp.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
            redisTemplate.opsForHash().put("mail_log", msgId, "zwl");
            channel.basicAck(tag, false); //确认消息已消费
            logger.info(msgId + ":邮件发送成功！");
        } catch (MessagingException e) {
            channel.basicNack(tag, false, true);
            e.printStackTrace();
            logger.error("邮件发送失败！！" + e.getMessage());
        }
    }
}
