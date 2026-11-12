package org.example.task3;

public class GateBoard extends FlightBoard {
    public GateBoard(Equipment equipment) {
        super(equipment);
    }

    @Override
    public void show() {
        equipment.displayInfo("Табло виходу на посадку", "Вихід №4 відчинено");
    }
}
