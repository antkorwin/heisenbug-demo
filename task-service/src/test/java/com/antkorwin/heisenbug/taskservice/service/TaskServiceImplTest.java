package com.antkorwin.heisenbug.taskservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.antkorwin.heisenbug.taskservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.taskservice.model.Task;
import com.antkorwin.heisenbug.taskservice.repository.TaskRepository;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import org.springframework.context.ApplicationEventPublisher;

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
public class TaskServiceImplTest {

    private static final String TASK_TITLE = "turn to the dark side";
    private static final int TASK_ESTIMATE = 10;
    private static final UUID TASK_ID = UUID.randomUUID();


    private TaskRepository taskRepository = mock(TaskRepository.class);
    private ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
    private TaskService taskService = new TaskServiceImpl(taskRepository, publisher);

    @Test
    public void create() throws Exception {
        // Arrange
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        Task expectedTask = mock(Task.class);
        when(taskRepository.save(any())).thenReturn(expectedTask);

        // Act
        Task task = taskService.create(TASK_TITLE, TASK_ESTIMATE);

        // Assert
        assertThat(task).isEqualTo(expectedTask);

        verify(taskRepository).save(captor.capture());
        assertThat(captor.getValue()).extracting(Task::getTitle,
                                                 Task::getEstimate)
                                     .contains(TASK_TITLE,
                                               TASK_ESTIMATE);
    }

    @Test
    public void getList() throws Exception {
        // Arrange
        List<Task> expectedTasks = mock(List.class);
        when(taskRepository.findAll()).thenReturn(expectedTasks);
        // Act
        List<Task> list = taskService.getList();
        // Assert
        assertThat(list).isEqualTo(expectedTasks);
        verify(taskRepository).findAll();
    }

    @Test
    public void taskComplete() {
        Task task = mock(Task.class);
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(task));
        // Act
        taskService.complete(TASK_ID);
        // Assert
        verify(taskRepository).delete(task);
    }
}