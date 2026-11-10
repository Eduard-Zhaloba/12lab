package org.example.task1;

/**
 * Спільний інтерфейс для окремого вантажу та групи вантажів.
 */
public interface Cargo {
    String getName();

    double getWeight();

    int getPieceCount();

    /**
     * Виводить цей елемент і всі вкладені елементи як дерево.
     */
    void printTree(String indent);
}
