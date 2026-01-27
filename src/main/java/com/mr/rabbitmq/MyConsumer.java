package com.mr.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Slf4j
@Component
public class MyConsumer {

    /**
     * 监听队列,当队列中有消息，则监听器工作，处理接收到的消息
     */
    @RabbitListener(queues = "custer_queue1")
    public void process(Message message) {
        byte[] body = message.getBody();
        log.info("接收到的mq队列消息是：" + new String(body));
    }
}
