package com.antkorwin.heisenbug.archiveservice.service;

import com.antkorwin.heisenbug.archiveservice.model.Task;
import com.antkorwin.heisenbug.archiveservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(UUID id, String title, int estimate) {

        Task task = Task.builder()
                        .title(title)
                        .estimate(estimate)
                        .id(id.toString())
                        .build();

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getList() {
        return taskRepository.findAll();
    }
}
