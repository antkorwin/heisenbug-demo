package com.antkorwin.heisenbug.archiveservice.api;

import com.antkorwin.heisenbug.archiveservice.api.dto.out.TaskDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jupiter.tools.mvc.requester.MvcRequester;
import com.jupiter.tools.spring.test.mongo.annotation.MongoDataSet;
import com.jupiter.tools.spring.test.mongo.junit5.meta.annotation.MongoDbIntegrationTest;
import com.jupiter.tools.spring.test.web.annotation.EnableRestTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@EnableRestTest
@MongoDbIntegrationTest
class TaskControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @MongoDataSet(value = "dataset/task_list.json", cleanAfter = true, cleanBefore = true)
    void getList() {
        // Act
        List<TaskDto> taskList = MvcRequester.on(mockMvc)
                                             .to("/tasks/list")
                                             .get()
                                             .doReturn(new TypeReference<List<TaskDto>>() {});
        // Assert
        assertThat(taskList).hasSize(2)
                            .extracting(TaskDto::getTitle)
                            .contains("first task","second task");
    }
}