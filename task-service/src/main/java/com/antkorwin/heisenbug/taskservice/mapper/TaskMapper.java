package com.antkorwin.heisenbug.taskservice.mapper;

import java.util.List;

import com.antkorwin.heisenbug.taskservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.taskservice.model.Task;
import org.mapstruct.Mapper;

/**
 * Created on 26.04.2019.
 *
 * @author Korovin Anatoliy
 */
@Mapper
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toListDto(List<Task> tasks);
}
