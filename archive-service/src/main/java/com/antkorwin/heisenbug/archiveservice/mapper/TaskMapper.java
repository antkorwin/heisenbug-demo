package com.antkorwin.heisenbug.archiveservice.mapper;

import com.antkorwin.heisenbug.archiveservice.api.dto.out.TaskDto;
import com.antkorwin.heisenbug.archiveservice.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@Mapper
public interface TaskMapper {

    List<TaskDto> toListDto(List<Task> tasks);
}
