package com.antkorwin.heisenbug.archiveservice.event;

import com.antkorwin.heisenbug.archiveservice.config.RabbitMqConfig;
import com.antkorwin.heisenbug.archiveservice.service.TaskService;
import com.jupiter.tools.spring.test.rabbitmq.annotation.meta.EnableRabbitMqTest;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@EnableRabbitMqTest
class TaskEventListenerIT {

    private static final String TASK_TITLE = "test title";
    private static final UUID TASK_ID = UUID.randomUUID();
    private static final int TASK_ESTIMATE = 17;

    @MockBean
    private TaskService taskService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void testCompleteTask() {
        // Act
        amqpTemplate.convertAndSend(RabbitMqConfig.TASK_EXPORT_QUEUE, CompleteTaskEvent.builder()
                                                                                       .title(TASK_TITLE)
                                                                                       .estimate(TASK_ESTIMATE)
                                                                                       .id(TASK_ID)
                                                                                       .build());
        // Assert
        Awaitility.await()
                  .atMost(3, TimeUnit.SECONDS)
                  .untilAsserted(() -> verify(taskService).createTask(eq(TASK_ID),
                                                                      eq(TASK_TITLE),
                                                                      eq(TASK_ESTIMATE)));
    }
}