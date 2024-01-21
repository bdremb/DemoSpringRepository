package com.example.demospringconditional.config;

import com.example.demospringconditional.EventQueue;
import com.example.demospringconditional.EventQueueWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EventQueue.class)
public class EventQueueConfiguration {

    @Bean
    public EventQueueWorker eventQueueWorker(EventQueue eventQueue, ApplicationEventPublisher applicationEventPublisher) {
        return new EventQueueWorker(eventQueue, applicationEventPublisher);
    }
}
