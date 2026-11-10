package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Схема салону одного рейсу. Номер рейсу та колекція місць належать схемі,
 * а не дублюються в кожному об'єкті Seat.
 */
public final class FlightCabin {
    private static final char[] SEAT_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F'};

    private final String flightNumber;
    private final List<Seat> seats = new ArrayList<>();

    public FlightCabin(String flightNumber, SeatClassFactory factory) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("Номер рейсу не може бути порожнім");
        }
        this.flightNumber = flightNumber;
        createSeatMap(factory);
    }

    private void createSeatMap(SeatClassFactory factory) {
        // 30 рядів по 6 місць = 180 місць.
        // Перші 4 ряди: бізнес-клас (24 місця), решта: економ (156 місць).
        SeatClassData businessData = factory.getSeatClassData(SeatClass.BUSINESS);
        SeatClassData economyData = factory.getSeatClassData(SeatClass.ECONOMY);

        for (int row = 1; row <= 30; row++) {
            SeatClassData data = row <= 4 ? businessData : economyData;
            for (char letter : SEAT_LETTERS) {
                seats.add(new Seat(row, letter, data));
            }
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public Seat findSeat(int row, char letter) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getLetter() == letter)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Місце не знайдено: " + row + letter
                ));
    }
}
