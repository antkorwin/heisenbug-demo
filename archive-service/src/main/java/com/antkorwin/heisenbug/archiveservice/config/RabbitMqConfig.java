package com.antkorwin.heisenbug.archiveservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@Configuration
public class RabbitMqConfig {

    public static final String TASK_EXPORT_QUEUE = "task-export-queue";

    @Bean
    public Queue testQueue() {
        return new Queue("task-export-queue");
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
