package com.pluralsight.dealershipversion2.gui;

import com.pluralsight.dealershipversion2.entity.Customer;
import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.Dealer;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.repository.DealershipFileManager;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.Getter;

import java.util.List;

import static com.pluralsight.dealershipversion2.utils.InputOutput.*;

public class CustomerGUI {
    @Getter
    private Dealer dealership;
    private static CustomerGUI customerGUI;
    private Customer customer;
    private static VehicleInventory carInventory;
    private List<Car> carList;
    private List<Contract> contractList;

    private CustomerGUI(){
        dealership = DealershipFileManager.getDealer();
        customer = new Customer();
        carInventory = dealership.getInventory();
        carList = customer.getCarList();
        homeScreen();
    }

    public static CustomerGUI getInstance() {
        if(customerGUI == null) {
            customerGUI = new CustomerGUI();
            return customerGUI;
        }
        return customerGUI;
    }


    public void homeScreen() {

        boolean flag = true;
        while (flag) {
            try {
                printMenu();  // Display the car dealership menu
                String command = promptForString(" (Dealership) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "1" -> display();                         // Display all vehicles
                    case "2" -> buyCar();
                    case "3" -> processGetByPrice();        // Search by price range
                    case "4" -> processGetByMakeModel();    // Search by make and model
                    case "5" -> processGetByYear();         // Search by year
                    case "6" -> processGetByColor();        // Search by color
                    case "7" -> processGetByMileage();      // Search by mileage
                    case "8" -> processGetByVehicleType();  // Search by vehicle type
                    case "9" -> processGetAllVehicle();    // Display all vehicles
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
                Welcome to the Customer Dashboard
                Please select an option:
                1. Buy the vehicle based on nin number
                2. Display all vehicles
                3. Search vehicles by price range
                4. Search vehicles by make and model
                5. Search vehicles by year
                6. Search vehicles by color
                6. Search vehicles by mileage
                8. Search vehicles by vehicle type
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

    private void buyCar() {
        Contract contract = customer.purchaseCar(customer, dealership);

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

}
