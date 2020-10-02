package com.lsnu.producer.controllers;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.lsnu.entitis.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jenson
 * @since 2020/10/2 11:16
 **/
@Slf4j
@RestController
public class SendMessageController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.topic}")
    private String topic;

    @GetMapping("/send-message")
    public String sendMessage(Student student) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //将封装的student对象发送到MQ
        String body = JSONObject.toJSONString(student);
        //消息体
        Message message = new Message(topic,body.getBytes());
        //开始发送
        SendResult sendResult = rocketMQTemplate.getProducer().send(message);
        log.info("消息生产者发送消息成功，当前时间[{}]", DateUtil.now());
        //在页面显示发送结果
        return sendResult.toString();
    }

}
