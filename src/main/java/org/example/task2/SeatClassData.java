package org.example.task2;

import java.util.List;
import java.util.Objects;

/**
 * Flyweight: незмінні спільні характеристики всіх місць одного класу.
 * Об'єкт створюється фабрикою один раз для кожного класу обслуговування.
 */
public final class SeatClassData {
    private final SeatClass seatClass;
    private final String description;
    private final double seatWidthCm;
    private final double seatPitchCm;
    private final int reclineDegrees;
    private final List<String> amenities;
    private final String imageResource;

    SeatClassData(
            SeatClass seatClass,
            String description,
            double seatWidthCm,
            double seatPitchCm,
            int reclineDegrees,
            List<String> amenities,
            String imageResource
    ) {
        this.seatClass = Objects.requireNonNull(seatClass);
        this.description = Objects.requireNonNull(description);
        if (seatWidthCm <= 0 || seatPitchCm <= 0 || reclineDegrees < 0) {
            throw new IllegalArgumentException("Некоректні характеристики крісла");
        }
        this.seatWidthCm = seatWidthCm;
        this.seatPitchCm = seatPitchCm;
        this.reclineDegrees = reclineDegrees;
        this.amenities = List.copyOf(amenities);
        this.imageResource = Objects.requireNonNull(imageResource);
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public String getDescription() {
        return description;
    }

    public double getSeatWidthCm() {
        return seatWidthCm;
    }

    public double getSeatPitchCm() {
        return seatPitchCm;
    }

    public int getReclineDegrees() {
        return reclineDegrees;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String describe() {
        return """
                Клас обслуговування: %s
                Опис: %s
                Ширина крісла: %.1f см
                Крок крісел: %.1f см
                Нахил спинки: %d°
                Зручності: %s
                Ресурс зображення: %s""".formatted(
                seatClass, description, seatWidthCm, seatPitchCm,
                reclineDegrees, String.join(", ", amenities), imageResource
        );
    }
}
