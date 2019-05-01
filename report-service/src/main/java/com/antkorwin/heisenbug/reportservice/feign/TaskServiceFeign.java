package com.antkorwin.heisenbug.reportservice.feign;

import java.util.List;

import com.antkorwin.heisenbug.reportservice.model.Task;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 24.09.2018.
 *
 * @author Korovin Anatoliy
 */
@FeignClient(name = "task-service", path = "tasks/")
public interface TaskServiceFeign {

    @GetMapping("/list")
    List<Task> getAllTask();
}
