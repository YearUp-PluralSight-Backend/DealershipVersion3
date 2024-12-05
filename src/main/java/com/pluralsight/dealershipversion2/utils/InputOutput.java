package com.pluralsight.dealershipversion2.utils;


import com.pluralsight.dealershipversion2.entity.vehicle.Car;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class InputOutput {


    private static final Scanner scanner = new Scanner(System.in);

    private static final Consumer<String> println = System.out::println;

    public static void formatOutput(String prompt) {

        String info =
                """
                ----------------------------------------------------------------------------------------------------
                %s
                ----------------------------------------------------------------------------------------------------

                """.formatted(prompt);
        System.out.println(info);
    }

    public static boolean promptForBoolean(String prompt) {
        String value = promptForString(prompt);
        if (value.equalsIgnoreCase("Yes")) {
            return true;
        } else {
            return  false;
        }
    }


    public static String promptForString(String prompt) {
        System.out.print(prompt);
        return promptForString();
    }

    /**
     * Prompt for a string value from the user without a prompt message.
     *
     * @return The input value
     */
    public static String promptForString() {
        String value = "";
        try {
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Prompt for an integer value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
    public static int promptForInteger(String prompt) {
        System.out.print(prompt);
        return promptForInteger();
    }

    /**
     * Prompt for an integer value from the user without a prompt message.
     *
     * @return The input value
     */
    public static int promptForInteger() {
        int value = 0;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Prompt for a double value from the user.
     *
     * @param prompt Message to show the user
     * @return The input value
     */
    public static Double promptForDouble(String prompt) {
        System.out.println(prompt);
        double value = 0;
        try {
            String stringValue = scanner.nextLine().trim();
            if (stringValue.isBlank() && stringValue.isEmpty()) {
                value = 0;
            } else {
                value = Double.parseDouble(stringValue);
            }
        } catch (Exception e) {
            System.out.println("Number Format issue! Please enter the right format!");
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Prompt for a double value from the user with a default value.
     *
     * @param prompt Message to show the user
     * @param defaultValue The default value if input is empty
     * @return The input value
     */
    public static Double promptForDouble(String prompt, double defaultValue) {
        System.out.println(prompt);
        double value = 0;
        try {
            String stringValue = scanner.nextLine().trim();
            if (stringValue.isBlank() && stringValue.isEmpty()) {
                value = defaultValue;
            }
        } catch (Exception e) {
            System.out.println("Number Format issue! Please enter the right format!");
            e.printStackTrace();
        }
        return value;
    }



    public static void endingAnimation() {

        formatOutput("Press enter/return to exit");
        scanner.nextLine();
    }


    public static void loadingAnimation() {

        try {
            System.out.print("Loading.");
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }


    public static Car carObject() {

        int vin = promptForInteger("Enter Vehicle Identification Number (VIN): ");
        int year = promptForInteger("Enter the manufacturing year: ");
        String make = promptForString("Enter the make (e. g., Toyota, Honda): ");
        String model = promptForString("Enter the model(e. g., Rav4, CR-V: ");
        String vehicleType = promptForString("Enter the vehicle type (e.g., sedan, SUV): ");
        String color = promptForString("Enter the color: ");
        int odometer = promptForInteger("Enter the odometer reading: ");
        double price = promptForDouble("Enter the price: ");

        return new Car(vin, year, make, model, vehicleType, color, odometer, price);
    }

    public static void vehicleHeader() {
        System.out.println();
        System.out.printf("          %-10s %-6s %-15s %-15s %-12s %-10s %-15s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------");

    }


    public static void vehicleFooter(List<Car> carList) {
        double totalValue = carList.parallelStream().mapToDouble(car -> car.getPrice()).sum();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("Total Cars: %-10d Total Inventory Value: $%,.2f%n", carList.size(), totalValue);

    }
}
