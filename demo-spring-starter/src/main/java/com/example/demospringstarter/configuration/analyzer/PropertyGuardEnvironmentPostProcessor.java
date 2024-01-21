package com.example.demospringstarter.configuration.analyzer;

import com.example.demospringstarter.exception.BackgroundTaskPropertyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class PropertyGuardEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String defaultExecutor = environment.getProperty("background-executor.default-executor");
        String cronExpression = environment.getProperty("background-executor.cron.expression");
        String timeValue = environment.getProperty("background-executor.time.in-seconds-time");
        String taskSize = environment.getProperty("background-executor.executor.task-size");

        boolean enabled = Boolean.parseBoolean(environment.getProperty("background-executor.enabled"));

        if (enabled) {
            check(defaultExecutor, cronExpression, timeValue, taskSize);
        }

    }

    private void check(String defaultExecutor, String cronExpression, String timeValue, String taskSize) {
        boolean isInvalidType = !StringUtils.hasText(defaultExecutor)
                || (!Objects.equals(defaultExecutor, "cron")
                && Objects.equals(defaultExecutor, "time")
        );

        if (isInvalidType) {
            throw new BackgroundTaskPropertyException("Property background-executor.default-executor must be cron or time!");
        }

        if (Objects.equals(defaultExecutor, "cron") && !StringUtils.hasText(cronExpression)) {
            throw new BackgroundTaskPropertyException("Invalid cron expression for 'cron' type!");
        }

        if (Objects.equals(defaultExecutor, "time") && !StringUtils.hasText(timeValue)) {
            throw new BackgroundTaskPropertyException("Invalid time value for 'time' type!");
        }

        if (!StringUtils.hasText(taskSize) || !taskSize.matches("-?\\d*") || Integer.parseInt(taskSize) <= 0) {
            throw new BackgroundTaskPropertyException("Invalid tasks size value!");
        }


    }

}
