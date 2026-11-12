package org.example.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Базова реалізація спільних властивостей окремого вантажу.
 */
public abstract class AbstractCargo implements Cargo {
    private final String name;
    private final double weight;

    protected AbstractCargo(String name, double weight) {
        this.name = Objects.requireNonNull(name, "Назва вантажу не може бути null").trim();
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Назва вантажу не може бути порожньою");
        }
        if (!Double.isFinite(weight) || weight < 0) {
            throw new IllegalArgumentException("Вага має бути скінченним невід'ємним числом");
        }
        this.weight = weight;
        log("Створено окремий вантаж: " + this.name + " (" + this.weight + " кг)");
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public final int getPieceCount() {
        return 1;
    }

    @Override
    public void printTree(String indent) {
        System.out.printf(
                Locale.ROOT,
                "%s- %s | вага: %.2f кг | місць: %d%n",
                indent, name, getWeight(), getPieceCount()
        );
    }

    private void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [AbstractCargo] %s%n", time, event);
    }
}