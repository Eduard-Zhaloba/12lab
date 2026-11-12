package org.example.task3;

public class DepartureBoard extends FlightBoard {
    public DepartureBoard(Equipment equipment) {
        super(equipment);
    }

    @Override
    public void show() {
        equipment.displayInfo("Вилітне табло", "Рейс BUK-101 до Києва: Посадка триває");
    }
}