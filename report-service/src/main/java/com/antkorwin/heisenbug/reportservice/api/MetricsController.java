package com.antkorwin.heisenbug.reportservice.api;

import java.util.List;

import com.antkorwin.heisenbug.reportservice.feign.TaskServiceFeign;
import com.antkorwin.heisenbug.reportservice.model.Task;
import com.antkorwin.heisenbug.reportservice.model.Value;
import com.antkorwin.heisenbug.reportservice.service.MetricsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 24.09.2018.
 *
 * @author Korovin Anatoliy
 */
@RestController
@RequestMapping("metrics")
public class MetricsController {

    private final TaskServiceFeign taskServiceFeign;
    private final MetricsService metricsService;

    @Autowired
    public MetricsController(TaskServiceFeign taskServiceFeign,
                             MetricsService metricsService) {
        this.taskServiceFeign = taskServiceFeign;
        this.metricsService = metricsService;
    }


    @GetMapping
    public List<Value> getAll() {
        List<Task> tasks = taskServiceFeign.getAllTask();
        return metricsService.evaluate(tasks);
    }
}

