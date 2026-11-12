package org.example.task3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Projector implements Equipment {
    @Override
    public void displayInfo(String boardType, String message) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("[" + time + "] [Проектор] " + boardType + ": " + message);
    }
}