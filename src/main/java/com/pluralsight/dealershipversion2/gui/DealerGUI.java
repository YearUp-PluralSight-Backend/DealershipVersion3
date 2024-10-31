package com.pluralsight.dealershipversion2.gui;


import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.entity.Dealer;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.Record;
import com.pluralsight.dealershipversion2.repository.DealershipFileManager;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.Getter;

import java.util.List;

import static com.pluralsight.dealershipversion2.utils.InputOutput.*;

public class DealerGUI {

    @Getter
    private static Dealer dealership;
    private static DealerGUI dealerGUI;
    private static VehicleInventory carInventory;
    private List<Car> carList;
    private List<Record> recordList;
    private List<Contract> contractList;

    private DealerGUI(){
        dealership = DealershipFileManager.getDealer();
        carInventory = dealership.getInventory();
        this.carList = carInventory.getAllVehicles();
        this.recordList = dealership.getRecordList();
        this.contractList = dealership.getContractList();
        homeScreen();
    }

    public static DealerGUI getInstance() {

        if(dealerGUI == null) {
            dealerGUI = new DealerGUI();
            return dealerGUI;
        }
        return dealerGUI;
    }


    private void homeScreen() {

        boolean flag = true;
        while (flag) {
            try {
                printMenu();  // Display the car dealership menu
                String command = promptForString(" (Dealership) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "1" -> display();                         // Display all vehicles
                    case "2" -> processGetByPrice();        // Search by price range
                    case "3" -> processGetByMakeModel();    // Search by make and model
                    case "4" -> processGetByYear();         // Search by year
                    case "5" -> processGetByColor();        // Search by color
                    case "6" -> processGetByMileage();      // Search by mileage
                    case "7" -> processGetByVehicleType();  // Search by vehicle type
                    case "8" -> processGetAllVehicle();    // Display all vehicles
                    case "9" -> processAddVehicle();        // Add a new vehicle
                    case "10" -> processRemoveVehicle();     // Remove a vehicle
                    case "11" -> processRemoveVehicleById();     // Remove a vehicle

                    case "0" -> flag = false;                      // Exit the application
                    default -> System.out.println("Invalid Option. Please choose a number between 0 and 10.\n");
                }
            } catch (Exception e) {
                System.out.println("Wrong command or option!");
                e.printStackTrace();
            }
        }

    }

    private void printMenu() {
        String info =
                """
                %s|%s|%s
                Welcome to the Car Dealership Inventory System
                Please select an option:
                1. Display all vehicles
                2. Search vehicles by price range
                3. Search vehicles by make and model
                4. Search vehicles by year
                5. Search vehicles by color
                6. Search vehicles by mileage
                7. Search vehicles by vehicle type
                8. Display all vehicles
                9. Add a new vehicle to the inventory
                10. Remove a vehicle from the inventory
                0. Exit
                """.formatted(dealership.getName(), dealership.getAddress(), dealership.getPhone());

        System.out.println(info);
    }


    private void display() {
        loadingAnimation();
        header();
        carList.forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processGetByPrice(){
        Double min = promptForDouble("Enter the min price: ");
        Double max = promptForDouble("Enter the max price: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByPrice(min, max).forEach(System.out::println);
        footer(carList);
        printEndingPrompt();

    }
    public void processGetByMakeModel(){
        String make = promptForString("Enter the Make: ");
        String model = promptForString("Enter the Model: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByMakeModel(make,model).forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processGetByYear(){
        int startYear = promptForInteger("Enter the start year: ");
        int endYear = promptForInteger("Enter the end year: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByYear(startYear, endYear).forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processGetByColor(){
        String color = promptForString("Enter the color: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByColor(color).forEach(System.out::println);
        footer(carList);

        printEndingPrompt();

    }

    public void processGetByMileage(){
        double min = promptForDouble("Enter the min mileage: ");
        double max = promptForDouble("Enter the max mileage: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByMileage(min, max).forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processGetByVehicleType(){
        String vehicleType = promptForString("Enter the vehicle type: ");
        loadingAnimation();
        header();
        carInventory.getVehiclesByType(vehicleType).forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processGetAllVehicle(){
        loadingAnimation();
        header();
        carList.forEach(System.out::println);
        footer(carList);
        printEndingPrompt();
    }

    public void processAddVehicle(){
        loadingAnimation();
        Car car = carObject();
        carList.add(car);
        printEndingPrompt();

    }

    public void processRemoveVehicle(){
        loadingAnimation();
        Car car = carObject();
        boolean removeVehicle = carInventory.removeVehicle(car);

        if (!removeVehicle) {
            formatOutput("Your data is not correct!");
        }
        formatOutput("You have removed vehicle successfully!");
        printEndingPrompt();

    }

    private void processRemoveVehicleById() {
        loadingAnimation();
        int vin = promptForInteger("Enter the vin number: ");
        Car car = carInventory.removeVehicleById(vin);
        if (car != null) {
            formatOutput("Your vin number is not correct!");
        }
        formatOutput("You have removed vehicle successfully!");
        printEndingPrompt();


    }


}
