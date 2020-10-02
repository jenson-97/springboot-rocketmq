package com.lsnu.consumer;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.lsnu.entitis.Student;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Jenson
 * @since 2020/10/2 11:33
 **/
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}",consumerGroup = "${rocketmq.consumer.group}")
public class ConsumerResult implements RocketMQListener<MessageExt> {

    @SneakyThrows
    public void onMessage(MessageExt messageExt) {
        log.info("消费者开始消费，当前时间[{}]", DateUtil.now());
        String result = new String(messageExt.getBody(),"UTF-8");
        Student getStudent = JSONObject.parseObject(result, Student.class);
        log.info("消息消费者获得的结果为:[{}],学生对象的姓名属性：[{}]",result,getStudent.getName());
    }
}
