package com.antkorwin.heisenbug.taskservice.event;

import com.antkorwin.heisenbug.taskservice.config.RabbitMqConfig;
import com.antkorwin.heisenbug.taskservice.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@Component
@RequiredArgsConstructor
public class CompleteTaskListener {

    private final AmqpTemplate amqpTemplate;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onTaskComplete(Task task) {

        System.out.println("send task: " + task.getTitle());
        amqpTemplate.convertAndSend(RabbitMqConfig.TASK_EXPORT_QUEUE,
                                    CompleteTaskEvent.builder()
                                                     .id(task.getId())
                                                     .title(task.getTitle())
                                                     .estimate(task.getEstimate())
                                                     .build());
    }
}
