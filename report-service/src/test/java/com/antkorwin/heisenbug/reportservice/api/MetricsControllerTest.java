package com.antkorwin.heisenbug.reportservice.api;

import com.antkorwin.heisenbug.reportservice.feign.TaskServiceFeign;
import com.antkorwin.heisenbug.reportservice.model.Task;
import com.antkorwin.heisenbug.reportservice.model.Value;
import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import com.jupiter.tools.spring.test.web.extension.ribbon.RedirectRibbonToEmbeddedWebServer;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created on 2019-04-29
 *
 * @author Korovin Anatoliy
 */
@EnableEmbeddedWebServerTest
@RedirectRibbonToEmbeddedWebServer("task-service")
class MetricsControllerTest {

    @Autowired
    private MetricsController metricsController;

    @Test
    void testGetMetrics() {
        // Act
        List<Value> metrics = metricsController.getAll();
        // Assert
        assertThat(metrics).hasSize(4)
                           .extracting(Value::getName, Value::getValue)
                           .contains(Tuple.tuple("0..10 - easy 🙂", 25.0),
                                     Tuple.tuple("11..20 - cool 👍", 0.0),
                                     Tuple.tuple("21..40 - hard 💪", 50.0),
                                     Tuple.tuple("41..1000 - unicorn 🦄", 25.0));
    }

    @TestConfiguration
    public static class TestConfig {

        @RestController
        @RequestMapping("tasks")
        public class TestController implements TaskServiceFeign {

            @Override
            @GetMapping("/list")
            public List<Task> getAllTask() {
                Task t1 = Task.builder().estimate(10).build();
                Task t2 = Task.builder().estimate(30).build();
                Task t3 = Task.builder().estimate(33).build();
                Task t4 = Task.builder().estimate(70).build();
                return Arrays.asList(t1, t2, t3, t4);
            }
        }
    }
}