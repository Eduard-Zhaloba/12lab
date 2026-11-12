package org.example.task3;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Демонстраційний сценарій Завдання 3 ---");

        Equipment ledPanel = new LedPanel();
        Equipment lcdScreen = new LcdScreen();

        FlightBoard departureBoard = new DepartureBoard(ledPanel);
        departureBoard.show();

        departureBoard.setEquipment(lcdScreen);
        departureBoard.show();

        System.out.println("-------------------------------------------");


        Equipment mobileApp = new MobileApp();
        FlightBoard gateBoard = new GateBoard(mobileApp);
        gateBoard.show();

        System.out.println("-------------------------------------------");


        Equipment projector = new Projector();
        FlightBoard arrivalBoard = new ArrivalBoard(projector);
        arrivalBoard.show();
    }
}