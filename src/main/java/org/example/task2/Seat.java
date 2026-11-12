package org.example.task2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Контекст Flyweight: зберігає індивідуальні дані конкретного місця
 * та посилання на спільні характеристики класу обслуговування.
 */
public final class Seat {
    private final int row;
    private final char letter;
    private final SeatClassData sharedData;
    private String passenger;

    public Seat(int row, char letter, SeatClassData sharedData) {
        if (row < 1) {
            throw new IllegalArgumentException("Номер ряду має бути додатним");
        }
        if (letter < 'A' || letter > 'F') {
            throw new IllegalArgumentException("Літера місця має бути від A до F");
        }
        this.row = row;
        this.letter = letter;
        this.sharedData = Objects.requireNonNull(sharedData);
    }

    public int getRow() {
        return row;
    }

    public char getLetter() {
        return letter;
    }

    public String getPassenger() {
        return passenger;
    }

    public void assignPassenger(String passenger) {
        if (passenger == null || passenger.isBlank()) {
            throw new IllegalArgumentException("Ім'я пасажира не може бути порожнім");
        }
        this.passenger = passenger.trim();
        log("Пасажира '" + this.passenger + "' призначено на місце " + getSeatCode());
    }

    public SeatClassData getSharedData() {
        return sharedData;
    }

    public String getSeatCode() {
        return row + String.valueOf(letter);
    }

    public String describe(String flightNumber) {
        return """
                Рейс: %s
                Місце: %s
                Пасажир: %s
                --- Спільні характеристики ---
                %s""".formatted(
                flightNumber,
                getSeatCode(),
                passenger == null ? "не призначений" : passenger,
                sharedData.describe()
        );
    }

    private void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [Seat] %s%n", time, event);
    }
}