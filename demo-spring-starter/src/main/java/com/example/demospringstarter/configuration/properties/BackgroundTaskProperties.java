package com.example.demospringstarter.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(prefix = "background-executor")
public class BackgroundTaskProperties {

    private boolean enabled;

    private String defaultExecutor;

    private int taskSize;

    @NestedConfigurationProperty
    private CronExecutorProperties cron;

    @NestedConfigurationProperty
    private TimeExecutorProperties time;
}
