package com.antkorwin.heisenbug.archiveservice.api;

import com.antkorwin.heisenbug.archiveservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.archiveservice.mapper.TaskMapper;
import com.antkorwin.heisenbug.archiveservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("list")
    public List<TaskDto> getList(){
        return taskMapper.toListDto(taskService.getList());
    }
}
