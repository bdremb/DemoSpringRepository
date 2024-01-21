package com.example.demoparking;

import org.springframework.shell.standard.ShellComponent;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class ParkingShell {

    private final Map<Integer, Integer> slots = new HashMap<>();

    public String init(int big, int medium, int small) {
        slots.put(1, big);
        slots.put(2, medium);
        slots.put(3, small);

        return MessageFormat.format("Slots: [big: {0}, medium: {1}, small: {2}]", big, medium, small);
    }
}
