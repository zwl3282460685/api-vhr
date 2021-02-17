package com.zwl.vhrapi.model;

/**
 * rabbitmq中消息队列所使用的常量
 * @author zwl
 * @data 2021/2/17 11:35
 **/
public class MailConstants {
    public static final Integer DELIVERING = 0;    //消息投递中
    public static final Integer SUCCESS = 1;       //消息投递成功
    public static final Integer FAILURE = 2;       //消息投递失败
    public static final Integer MAX_TRY_COUNT = 3; //最大重试次数
    public static final Integer MSG_TIMEOUT = 1;   //消息超时时间
    public static final String MAIL_QUEUE_NAME = "zwl.mail.queue"; //队列名称
    public static final String MAIL_EXCHANGE_NAME = "zwl.mail.exchange"; //交换机名称
    public static final String MAIL_ROUTING_KEY_NAME = "zwl.mail.routing.key"; //路由名称
}
