package com.antkorwin.heisenbug.archiveservice.service;


import com.antkorwin.heisenbug.archiveservice.model.Task;

import java.util.List;
import java.util.UUID;


/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
public interface TaskService {

    Task createTask(UUID id, String title, int estimate);

    List<Task> getList();
}
