package com.antkorwin.heisenbug.taskservice.api;

import java.util.List;

import com.antkorwin.heisenbug.taskservice.api.dto.in.TaskCreateDto;
import com.antkorwin.heisenbug.taskservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.taskservice.mapper.TaskMapper;
import com.antkorwin.heisenbug.taskservice.model.Task;
import com.antkorwin.heisenbug.taskservice.repository.TaskRepository;
import com.antkorwin.heisenbug.taskservice.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
class TaskControllerTest {

    private static final String TASK_TITLE = "turn to the dark side";
    private static final int TASK_ESTIMATE = 10;

    private TaskService taskService = mock(TaskService.class);
    private TaskMapper taskMapper = mock(TaskMapper.class);
    private TaskController taskController = new TaskController(taskService, taskMapper);

    @Test
    void createTask() {
        // Arrange
        Task mockTask = mock(Task.class);
        when(taskService.create(eq(TASK_TITLE), eq(TASK_ESTIMATE))).thenReturn(mockTask);

        TaskDto expectedTaskDto = mock(TaskDto.class);
        when(taskMapper.toDto(eq(mockTask))).thenReturn(expectedTaskDto);

        TaskCreateDto createTaskDto = TaskCreateDto.builder()
                                                   .title(TASK_TITLE)
                                                   .estimate(TASK_ESTIMATE)
                                                   .build();
        // Act
        TaskDto taskDto = taskController.createTask(createTaskDto);

        // Assert
        assertThat(taskDto).isEqualTo(expectedTaskDto);
        verify(taskService).create(eq(TASK_TITLE),eq(TASK_ESTIMATE));
        verify(taskMapper).toDto(eq(mockTask));
    }


    @Test
    void getList() {
        // Arrange
        List<Task> tasks = mock(List.class);
        when(taskService.getList()).thenReturn(tasks);

        List<TaskDto> expectedTasks = mock(List.class);
        when(taskMapper.toListDto(eq(tasks))).thenReturn(expectedTasks);

        // Act
        List<TaskDto> resultList = taskController.getList();

        // Assert
        assertThat(resultList).isEqualTo(expectedTasks);
        verify(taskService).getList();
    }
}