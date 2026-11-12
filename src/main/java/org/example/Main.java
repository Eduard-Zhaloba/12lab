package org.example;

import org.example.task1.Task1Demo;
import org.example.task2.Task2Demo;
import org.example.task3.DemoTask3;

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
        Task2Demo.run();
    }

    public static void runTask3() { DemoTask3.run(); }
}