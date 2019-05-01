package com.antkorwin.heisenbug.taskservice.service;

import java.util.List;
import java.util.UUID;

import com.antkorwin.heisenbug.taskservice.model.Task;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
public interface TaskService {

    Task create(String title, int estimate);

    List<Task> getList();

    void complete(UUID taskId);

    Task find(String title);
}
