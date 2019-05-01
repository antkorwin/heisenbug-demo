package com.antkorwin.heisenbug.taskservice.api;

import com.antkorwin.heisenbug.taskservice.api.dto.in.TaskCreateDto;
import com.antkorwin.heisenbug.taskservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.taskservice.config.RabbitMqConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.mvc.requester.MvcRequester;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.jupiter.tools.spring.test.rabbitmq.annotation.ExpectedMessages;
import com.jupiter.tools.spring.test.rabbitmq.annotation.meta.EnableRabbitMqTest;
import com.jupiter.tools.spring.test.web.annotation.EnableRestTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableRestTest
@EnableRabbitMqTest
@EnablePostgresIntegrationTest
class TaskControllerIT {

    private static final String TASK_TITLE = "turn to the dark side";
    private static final int TASK_ESTIMATE = 10;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true)
    @ExpectedDataSet("datasets/expected/create_task.json")
    void createTask() {
        // Arrange
        TaskCreateDto taskCreateDto = TaskCreateDto.builder()
                                                   .title(TASK_TITLE)
                                                   .estimate(TASK_ESTIMATE)
                                                   .build();
        // Act
        TaskDto task = MvcRequester.on(mockMvc)
                                   .to("/tasks/create")
                                   .post(taskCreateDto)
                                   .expectStatus(HttpStatus.CREATED)
                                   .returnAs(TaskDto.class);
        // Assert
        assertThat(task).extracting(TaskDto::getTitle,
                                    TaskDto::getEstimate)
                        .contains(TASK_TITLE,
                                  TASK_ESTIMATE);
    }

    @Test
    @DataSet(value = "datasets/init/task_list.json", cleanBefore = true, cleanAfter = true)
    void getList() {
        // Arrange
        // Act
        List<TaskDto> taskList = MvcRequester.on(mockMvc)
                                             .to("/tasks/list")
                                             .get()
                                             .doReturn(new TypeReference<List<TaskDto>>() {});
        // Assert
        assertThat(taskList).hasSize(2)
                            .extracting(TaskDto::getTitle)
                            .contains("turn to the dark side",
                                      "break the resistance");
    }


    @Test
    @DataSet(value = "datasets/init/task_list.json", cleanBefore = true, cleanAfter = true)
    @ExpectedMessages(queue = RabbitMqConfig.TASK_EXPORT_QUEUE,
                      messagesFile = "/datasets/jms/expected/complete_task.json")
    @ExpectedDataSet("datasets/expected/complete_task.json")
    void completeTask() {

        UUID taskId = UUID.fromString("bf71b8d4-c22c-4223-82c7-28900aefffff");

        MvcRequester.on(mockMvc)
                    .to("/tasks/{taskId}/complete", taskId)
                    .post()
                    .expectStatus(HttpStatus.OK);
    }


    @Test
    @Sql("/sql/trgm.sql")
    @DataSet(value = "datasets/init/task_list.json", cleanBefore = true, cleanAfter = true)
    void testFindBySimilarity() {
        // Act
        TaskDto task = MvcRequester.on(mockMvc)
                                   .to("/tasks/find")
                                   .withParam("title", "bark side")
                                   .get()
                                   .returnAs(TaskDto.class);
        // Assert
        assertThat( task).isNotNull()
                         .extracting(TaskDto::getTitle)
                         .isEqualTo("turn to the dark side");
    }
}