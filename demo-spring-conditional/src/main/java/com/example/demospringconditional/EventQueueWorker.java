package com.example.demospringconditional;

import com.example.demospringconditional.event.EventHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@RequiredArgsConstructor
public class EventQueueWorker {

    private final EventQueue eventQueue;
    private final ApplicationEventPublisher publisher;

    @EventListener(ApplicationReadyEvent.class)
    public void startEventQueue() {
        startEventProducer();
        startEventConsumer();

    }

    private void startEventProducer() {
        Thread eventProducerThread = new Thread(() -> {
            while (true) {
                try {
                    UUID id = UUID.randomUUID();
                    Event event = Event.builder()
                            .id(id)
                            .payload("Payload for event " + id)
                            .build();
                    eventQueue.enqueue(event);
                    System.out.println("WRITE new event");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        eventProducerThread.start();
    }

    private void startEventConsumer() {
        Thread eventConsumerThread = new Thread(() -> {
            while (true) {
                Event event = eventQueue.dequeue();
//                System.out.println("READ " + event);
                publisher.publishEvent(new EventHolder(this, event));
            }
        });
        eventConsumerThread.start();
    }

}
