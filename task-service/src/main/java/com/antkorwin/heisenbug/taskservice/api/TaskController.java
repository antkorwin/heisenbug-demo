package com.antkorwin.heisenbug.taskservice.api;

import java.util.List;
import java.util.UUID;

import com.antkorwin.heisenbug.taskservice.api.dto.in.TaskCreateDto;
import com.antkorwin.heisenbug.taskservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.taskservice.mapper.TaskMapper;
import com.antkorwin.heisenbug.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@RequestBody TaskCreateDto taskCreateDto) {
        return taskMapper.toDto(taskService.create(taskCreateDto.getTitle(),
                                                   taskCreateDto.getEstimate()));
    }

    @GetMapping("list")
    public List<TaskDto> getList() {
        return taskMapper.toListDto(taskService.getList());
    }

    @PostMapping("/{taskId}/complete")
    public void completeTask(@PathVariable("taskId") UUID taskId){
        taskService.complete(taskId);
    }

    @GetMapping("find")
    public TaskDto findByTitle(@RequestParam("title") String title){
        return taskMapper.toDto(taskService.find(title));
    }
}
