package org.example.task2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрація Flyweight: 20 схем по 180 місць використовують
 * лише по одному спільному об'єкту для кожного класу обслуговування.
 */
public final class Task2Demo {
    private Task2Demo() {
    }

    public static void run() {
        log("Розпочато генерацію схем салонів для 20 рейсів");
        SeatClassFactory factory = new SeatClassFactory();
        List<FlightCabin> flights = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            flights.add(new FlightCabin("PS-" + (100 + i), factory));
        }

        Seat firstFlightSeat = flights.get(0).findSeat(12, 'A');
        Seat secondFlightSeat = flights.get(1).findSeat(18, 'C');
        firstFlightSeat.assignPassenger("Олександр Петренко");
        secondFlightSeat.assignPassenger("Марія Коваль");

        int totalSeatObjects = flights.stream()
                .mapToInt(flight -> flight.getSeats().size())
                .sum();

        System.out.println("\n=== СТАТИСТИКА СХЕМ САЛОНІВ ===");
        System.out.println("Кількість рейсів: " + flights.size());
        System.out.println("Місць на одному рейсі: " + flights.get(0).getSeats().size());
        System.out.println("Загальна кількість об'єктів місць: " + totalSeatObjects);
        System.out.println("Кількість об'єктів спільних даних: " + factory.getSharedObjectCount());

        System.out.println("\n=== СТВОРЕНІ СПІЛЬНІ ОБ'ЄКТИ ===");
        factory.getCreatedObjects().forEach((seatClass, data) ->
                System.out.println("- " + seatClass + ": " + data.getDescription())
        );

        System.out.println("\n=== ПОВНИЙ ОПИС МІСЦЯ З ПЕРШОГО РЕЙСУ ===");
        System.out.println(firstFlightSeat.describe(flights.get(0).getFlightNumber()));

        System.out.println("\n=== ПОВНИЙ ОПИС МІСЦЯ З ДРУГОГО РЕЙСУ ===");
        System.out.println(secondFlightSeat.describe(flights.get(1).getFlightNumber()));

        System.out.println("\n=== ПЕРЕВІРКА СПІЛЬНОГО ОБ'ЄКТА ===");
        System.out.println("Клас першого місця: " + firstFlightSeat.getSharedData().getSeatClass());
        System.out.println("Клас другого місця: " + secondFlightSeat.getSharedData().getSeatClass());
        System.out.println(
                "Посилання на той самий об'єкт: "
                        + (firstFlightSeat.getSharedData() == secondFlightSeat.getSharedData())
        );
        log("Завершено демонстрацію Завдання 2");
    }

    private static void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [Task2Demo] %s%n", time, event);
    }
}