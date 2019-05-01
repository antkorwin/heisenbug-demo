package com.antkorwin.heisenbug.taskservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@Configuration
public class RabbitMqConfig {

    public static final String TASK_EXPORT_QUEUE = "task-export-queue";

    @Bean
    public Queue testQueue() {
        return new Queue(TASK_EXPORT_QUEUE);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
