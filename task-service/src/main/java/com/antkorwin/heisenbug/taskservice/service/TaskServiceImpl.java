package com.antkorwin.heisenbug.taskservice.service;

import com.antkorwin.heisenbug.taskservice.model.Task;
import com.antkorwin.heisenbug.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Task create(String title, int estimate) {

        return taskRepository.save(Task.builder()
                                            .title(title)
                                            .estimate(estimate)
                                            .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getList() {
        return taskRepository.findAll();
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void complete(UUID taskId) {

        Task task = taskRepository.findById(taskId)
                                  .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
        applicationEventPublisher.publishEvent(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Task find(String title) {
        return taskRepository.getTopSimilarity(title);
    }
}
