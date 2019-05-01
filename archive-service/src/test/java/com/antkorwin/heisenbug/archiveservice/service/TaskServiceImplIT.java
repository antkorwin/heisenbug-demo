package com.antkorwin.heisenbug.archiveservice.service;

import com.antkorwin.heisenbug.archiveservice.model.Task;
import com.jupiter.tools.spring.test.mongo.annotation.ExpectedMongoDataSet;
import com.jupiter.tools.spring.test.mongo.annotation.ExportMongoDataSet;
import com.jupiter.tools.spring.test.mongo.annotation.MongoDataSet;
import com.jupiter.tools.spring.test.mongo.junit5.meta.annotation.MongoDbIntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@MongoDbIntegrationTest
class TaskServiceImplIT {

    private static final String TASK_TITLE = "Turn to the Dark Side...";
    private static final UUID TASK_ID = UUID.randomUUID();
    private static final int TASK_ESTIMATE = IntStream.rangeClosed(1, 100).sum();

    @Autowired
    private TaskService taskService;

    @Test
    @MongoDataSet(cleanAfter = true, cleanBefore = true)
    @ExpectedMongoDataSet("dataset/create_task.json")
    void createTask() {
        taskService.createTask(TASK_ID, TASK_TITLE, TASK_ESTIMATE);
    }

    @Test
    @MongoDataSet(value = "dataset/task_list.json", cleanAfter = true, cleanBefore = true)
    void getList() {
        List<Task> taskList = taskService.getList();
        assertThat(taskList).hasSize(2)
                            .extracting(Task::getTitle)
                            .contains("first task", "second task");
    }

    @Disabled
    @Test
    @ExportMongoDataSet(outputFile = "target/dataset/create_task.json")
    void exportMongoData() {
        taskService.createTask(TASK_ID, TASK_TITLE, TASK_ESTIMATE);
    }
}