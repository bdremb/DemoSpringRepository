package com.example.demospringstarter.configuration;

import com.example.demospringstarter.configuration.properties.BackgroundTaskProperties;
import com.example.demospringstarter.core.impl.TaskStopper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Configuration
@ConditionalOnProperty("background-executor.enabled")
@EnableConfigurationProperties(BackgroundTaskProperties.class)
@Slf4j
public class BackgroundConfiguration {

    @Bean
    public ConcurrentTaskScheduler concurrentTaskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public TaskStopper taskStopper(BackgroundTaskProperties properties) {
        return new TaskStopper(new LinkedHashMap<>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, ScheduledFuture<?>> eldest) {
                if (size() > properties.getTaskSize()) {
                    eldest.getValue().cancel(true);
                    log.debug("Eldest task preparing for delete. ID is {}", eldest.getKey());
                    return true;
                }
                return false;
            }
        });
    }

}
