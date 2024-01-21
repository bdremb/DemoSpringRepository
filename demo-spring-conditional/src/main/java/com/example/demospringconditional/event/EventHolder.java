package com.example.demospringconditional.event;

import com.example.demospringconditional.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {

    private final Event event;

    public EventHolder(Object source, Event event) {
        super(source);
        this.event = event;
    }

}
