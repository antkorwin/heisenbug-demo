package com.antkorwin.heisenbug.reportservice.service;

import java.util.List;

import com.antkorwin.heisenbug.reportservice.model.Task;
import com.antkorwin.heisenbug.reportservice.model.Value;

/**
 * Created on 24.09.2018.
 *
 * @author Korovin Anatoliy
 */
public interface MetricsService {

    List<Value> evaluate(List<Task> tasks);
}
