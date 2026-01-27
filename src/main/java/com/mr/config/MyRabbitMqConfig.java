package com.mr.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitMqConfig {

    private static String EXCHANGE_NAME = "custer_exchange";//交换机名称
    private static String QUEUE_NAME = "custer_queue1";//队列名称

    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    /**
     * 声明绑定关系
     *
     * @param queue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding queueBinding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
