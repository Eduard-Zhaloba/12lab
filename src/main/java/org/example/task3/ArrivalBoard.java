package org.example.task3;

public class ArrivalBoard extends FlightBoard {
    public ArrivalBoard(Equipment equipment) {
        super(equipment);
    }

    @Override
    public void show() {
        equipment.displayInfo("Прилітне табло", "Рейс WAR-202 з Варшави: Прибув");
    }
}