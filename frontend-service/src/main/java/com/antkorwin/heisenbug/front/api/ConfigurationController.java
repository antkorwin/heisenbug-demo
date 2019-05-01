package com.antkorwin.heisenbug.front.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 2019-04-29
 * <p>
 * Retrieves properties with `ws` prefix from application.yml
 * and gives them by the rest-api
 *
 * @author Korovin Anatoliy
 */
@RestController
@ConfigurationProperties(prefix = "ws")
public class ConfigurationController {
    private Map<String, String> options = new HashMap<>();

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    @GetMapping(value = "/app_config/config", produces = "application/javascript")
    public String getApplicationConfig() {
        return options.entrySet().stream()
                      .map(item -> String.format("var %s = \"%s\";", item.getKey(), item.getValue()))
                      .collect(Collectors.joining("\n"));
    }
}
