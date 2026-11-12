package org.example.task3;


public abstract class FlightBoard {
    protected Equipment equipment;

    public FlightBoard(Equipment equipment) {
        this.equipment = equipment;
    }


    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public abstract void show();
}