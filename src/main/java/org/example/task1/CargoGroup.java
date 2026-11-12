package org.example.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Composite: група, що містить інші об'єкти Cargo.
 * Вага та кількість місць обчислюються рекурсивно.
 */
public abstract class CargoGroup implements Cargo {
    private final String name;
    private final List<Cargo> children = new ArrayList<>();

    protected CargoGroup(String name) {
        this.name = Objects.requireNonNull(name, "Назва групи не може бути null").trim();
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("Назва групи не може бути порожньою");
        }
    }

    @Override
    public final String getName() {
        return name;
    }

    public final void add(Cargo cargo) {
        Objects.requireNonNull(cargo, "Не можна додати null");

        if (cargo == this) {
            throw new IllegalArgumentException("Групу не можна додати саму до себе");
        }
        if (containsIdentity(cargo, this, Collections.newSetFromMap(new IdentityHashMap<>()))) {
            throw new IllegalArgumentException("Додавання створило б цикл у структурі вантажу");
        }
        for (Cargo child : children) {
            if (child == cargo) {
                throw new IllegalArgumentException("Цей об'єкт уже доданий до групи");
            }
        }
        children.add(cargo);
        log("Додано вузол '" + cargo.getName() + "' до групи '" + this.getName() + "'");
    }

    public final boolean remove(Cargo cargo) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) == cargo) {
                children.remove(i);
                log("Вилучено вузол '" + cargo.getName() + "' з групи '" + this.getName() + "'");
                return true;
            }
        }
        return false;
    }

    public final List<Cargo> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public final double getWeight() {
        double total = 0.0;
        for (Cargo child : children) {
            total += child.getWeight();
        }
        return total;
    }

    @Override
    public final int getPieceCount() {
        int total = 0;
        for (Cargo child : children) {
            total = Math.addExact(total, child.getPieceCount());
        }
        return total;
    }

    @Override
    public final void printTree(String indent) {
        System.out.printf(
                "%s+ %s | вага: %.2f кг | місць: %d%n",
                indent, name, getWeight(), getPieceCount()
        );
        for (Cargo child : children) {
            child.printTree(indent + "  ");
        }
    }

    private void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [CargoGroup] %s%n", time, event);
    }

    private static boolean containsIdentity(
            Cargo current,
            Cargo target,
            Set<Cargo> visited
    ) {
        if (current == target) {
            return true;
        }
        if (!(current instanceof CargoGroup group) || !visited.add(current)) {
            return false;
        }
        for (Cargo child : group.children) {
            if (containsIdentity(child, target, visited)) {
                return true;
            }
        }
        return false;
    }
}