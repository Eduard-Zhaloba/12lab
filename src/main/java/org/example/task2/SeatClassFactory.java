package org.example.task2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Flyweight Factory: повторно повертає вже створені спільні об'єкти.
 */
public final class SeatClassFactory {
    private final Map<SeatClass, SeatClassData> sharedData =
            new EnumMap<>(SeatClass.class);

    public SeatClassData getSeatClassData(SeatClass seatClass) {
        return sharedData.computeIfAbsent(seatClass, this::createSeatClassData);
    }

    private SeatClassData createSeatClassData(SeatClass seatClass) {
        log("Створення нового спільного об'єкта даних для класу: " + seatClass);
        return switch (seatClass) {
            case ECONOMY -> new SeatClassData(
                    SeatClass.ECONOMY,
                    "Стандартне крісло економічного класу",
                    45.0, 79.0, 100,
                    List.of("ручна поклажа", "відкидний столик", "кишеня для журналу"),
                    "images/economy-seat.png"
            );
            case BUSINESS -> new SeatClassData(
                    SeatClass.BUSINESS,
                    "Крісло бізнес-класу з підвищеним комфортом",
                    56.0, 152.0, 180,
                    List.of("широке крісло", "розкладне ліжко", "персональне живлення", "преміальне меню"),
                    "images/business-seat.png"
            );
        };
    }

    public int getSharedObjectCount() {
        return sharedData.size();
    }

    public Map<SeatClass, SeatClassData> getCreatedObjects() {
        return Map.copyOf(sharedData);
    }

    private void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [SeatClassFactory] %s%n", time, event);
    }
}