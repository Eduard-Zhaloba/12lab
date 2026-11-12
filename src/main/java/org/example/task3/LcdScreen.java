package org.example.task3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LcdScreen implements Equipment {
    @Override
    public void displayInfo(String boardType, String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + time + "] [LCD-екран] " + boardType + ": " + message);
    }
}