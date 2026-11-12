package org.example.task1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Демонстрація шаблону Composite для розрахунку завантаження літака.
 */
public final class Task1Demo {
    private Task1Demo() {
    }

    public static void run() {
        Locale.setDefault(Locale.ROOT);
        log("Розпочато складання структури вантажу літака");

        Aircraft aircraft = new Aircraft("Літак");

        BaggageCompartment compartment1 = new BaggageCompartment("Багажний відсік 1");
        BaggageCompartment compartment2 = new BaggageCompartment("Багажний відсік 2");

        CargoContainer container1 = new CargoContainer("Контейнер 1");
        container1.add(new Suitcase("Валіза 1", 18.5));
        container1.add(new Suitcase("Валіза 2", 21.0));
        container1.add(new Parcel("Посилка 1", 7.5));

        CargoContainer container2 = new CargoContainer("Контейнер 2");
        container2.add(new Suitcase("Валіза 3", 16.0));
        container2.add(new Parcel("Посилка 2", 9.0));

        CargoContainer container3 = new CargoContainer("Контейнер 3");
        container3.add(new Suitcase("Валіза 4", 23.0));

        Pallet pallet = new Pallet("Палета 1");
        pallet.add(new Parcel("Посилка 3", 12.0));
        pallet.add(new Parcel("Посилка 4", 14.5));
        container3.add(pallet);

        CargoContainer container4 = new CargoContainer("Контейнер 4");
        container4.add(new Suitcase("Валіза 5", 19.5));
        container4.add(new Parcel("Посилка 5", 6.0));

        compartment1.add(container1);
        compartment1.add(container2);
        compartment2.add(container3);
        compartment2.add(container4);

        aircraft.add(compartment1);
        aircraft.add(compartment2);

        System.out.println("\n=== СТРУКТУРА ЗАВАНТАЖЕННЯ ЛІТАКА ===");
        aircraft.printTree("");

        System.out.println("\n=== ПІДСУМКИ ===");
        printSummary(aircraft);
        printSummary(compartment1);

        System.out.println("\n=== ПЕРЕВІРКА ОДНАКОВОГО ІНТЕРФЕЙСУ ===");
        Cargo selectedNode = compartment2;
        System.out.printf(
                "Виклик getWeight() для вузла \"%s\": %.2f кг%n",
                selectedNode.getName(), selectedNode.getWeight()
        );
        log("Завершення демонстрації Завдання 1");
    }

    private static void log(String event) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("[%s] [Task1Demo] %s%n", time, event);
    }

    private static void printSummary(Cargo cargo) {
        System.out.printf(
                Locale.ROOT,
                "%s — загальна вага: %.2f кг; кількість місць: %d%n",
                cargo.getName(), cargo.getWeight(), cargo.getPieceCount()
        );
    }
}