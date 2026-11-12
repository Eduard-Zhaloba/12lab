package org.example;

import org.example.task1.Task1Demo;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 1: COMPOSITE ===");
        runTask1();

        System.out.println("\n=== ЗАВДАННЯ 2: FLYWEIGHT ===");
        runTask2();

        System.out.println("\n=== ЗАВДАННЯ 3: BRIDGE ===");
        runTask3();
    }

    public static void runTask1() {
        Task1Demo.run();
    }

    public static void runTask2() {
        // Код виклику Task2Demo додамо після перевірки файлів
    }

    public static void runTask3() {
        // Код виклику Task3Demo додамо пізніше
    }
}