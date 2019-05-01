package com.antkorwin.heisenbug.archiveservice.event;

import com.antkorwin.heisenbug.archiveservice.config.RabbitMqConfig;
import com.antkorwin.heisenbug.archiveservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@Component
@EnableRabbit
@RequiredArgsConstructor
public class TaskEventListener {

    private final TaskService taskService;

    @RabbitListener(queues = RabbitMqConfig.TASK_EXPORT_QUEUE)
    public void receive(CompleteTaskEvent task) {
        System.out.println("RECEIVED CompleteTask : " + task.getTitle());
        taskService.createTask(task.getId(),
                               task.getTitle(),
                               task.getEstimate());
    }

}
