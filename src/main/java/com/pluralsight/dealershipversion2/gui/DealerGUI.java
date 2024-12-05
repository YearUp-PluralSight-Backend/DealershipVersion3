package com.pluralsight.dealershipversion2.gui;


import com.pluralsight.dealershipversion2.entity.document.LeaseContract;
import com.pluralsight.dealershipversion2.entity.document.SalesContract;
import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.Getter;

import java.util.List;

import static com.pluralsight.dealershipversion2.utils.InputOutput.*;

public class DealerGUI {

    @Getter
    private static final String USER_NAME  = "Harry";
    private static final String PASSWORD  = "12345";
    private static Dealer dealership;
    private static DealerGUI dealerGUI;
    private static VehicleInventory carInventory;
    private List<Car> carList;
    private List<Contract> contractList;

    private DealerGUI(){
        dealership = DealershipFileManager.getDealer();
        carInventory = dealership.getInventory();
        this.carList = carInventory.getInventory();
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

    public void homeScreen() {

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
                    case "8" -> processGetAllVehicle();     // Display all vehicles
                    case "9" -> processAddVehicle();        // Add a new vehicle
                    case "10" -> processRemoveVehicle();     // Remove a vehicle
                    case "11" -> processRemoveVehicleById(); // Remove a vehicle
                    case "12" -> sellOrLease();              // sell or lease
                    case "13" -> AdminInterface();           // Admin interface
                    case "0" -> flag = false;                // Exit the application
                    default -> System.out.println("Invalid Option. Please choose a number between 0 and 10.\n");
                }
            } catch (Exception e) {
                System.out.println("Wrong command or option!");
                e.printStackTrace();
            }
        }

    }

    private void display() {
        loadingAnimation();
        vehicleHeader();
        carList.forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processGetByPrice(){
        Double min = promptForDouble("Enter the min price: ");
        Double max = promptForDouble("Enter the max price: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByPrice(min, max).forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();

    }

    public void processGetByMakeModel(){
        String make = promptForString("Enter the Make: ");
        String model = promptForString("Enter the Model: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByMakeModel(make,model).forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processGetByYear(){
        int startYear = promptForInteger("Enter the start year: ");
        int endYear = promptForInteger("Enter the end year: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByYear(startYear, endYear).forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processGetByColor(){
        String color = promptForString("Enter the color: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByColor(color).forEach(System.out::println);
        vehicleFooter(carList);

        endingAnimation();

    }

    public void processGetByMileage(){
        double min = promptForDouble("Enter the min mileage: ");
        double max = promptForDouble("Enter the max mileage: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByMileage(min, max).forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processGetByVehicleType(){
        String vehicleType = promptForString("Enter the vehicle type: ");
        loadingAnimation();
        vehicleHeader();
        carInventory.getVehiclesByType(vehicleType).forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processGetAllVehicle(){
        loadingAnimation();
        vehicleHeader();
        carList.forEach(System.out::println);
        vehicleFooter(carList);
        endingAnimation();
    }

    public void processAddVehicle(){
        loadingAnimation();
        Car car = carObject();
        carList.add(car);
        endingAnimation();

    }

    public void processRemoveVehicle(){
        loadingAnimation();
        Car car = carObject();
        boolean removeVehicle = carInventory.removeVehicle(car);

        if (!removeVehicle) {
            formatOutput("Your data is not correct!");
        }
        formatOutput("You have removed vehicle successfully!");
        endingAnimation();

    }

    private void processRemoveVehicleById() {
         Car car = null;
        loadingAnimation();
        int vin = promptForInteger("Enter the vin number: ");
        boolean deleted = carInventory.removeVehicleById(vin);
        if (deleted) {
            formatOutput("You have successfully removed the car from the inventory.");
            } else  {
            formatOutput("Your VIN number is not correct!");

        }
        endingAnimation();
    }

    private void AdminInterface() {
        boolean flag = login();
        while (flag) {
            try {
                printAdminInterfaceMenu();  // Display the car dealership menu
                String command = promptForString(" (Dealership) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "1" -> getAllContracts();                 // display all contracts
                    case "2" -> getLastTenContracts();             // display ten contracts
                    case "0" -> flag = false;                      // Exit the application
                    default -> System.out.println("Invalid Option. Please choose a number 0 to 2.\n");
                }
            } catch (Exception e) {
                System.out.println("Wrong command or option!");
                e.printStackTrace();
            }
        }
    }

    private boolean login() {

        int status = 0;
        do {
            String userName = promptForString("Enter your user name: ");
            if (!userName.equalsIgnoreCase(USER_NAME)) formatOutput("Your username does not matched the database.");
            String password = promptForString("Enter your password: ");
            if (!password.equalsIgnoreCase(PASSWORD)) formatOutput("Your password does not matched the database");

            if (password.equalsIgnoreCase(PASSWORD) && userName.equalsIgnoreCase(USER_NAME)) {
                status = 1;
            }
        } while (status == 0);

        return  true;
    }

    private void sellOrLease() {

        loadingAnimation();
        Contract contract = null;
        int vin = promptForInteger("Enter the vin number: ");
        String date = promptForString("Enter the date(YYYYMMDD) :");
        String customerName = promptForString("Enter your name: ");
        String address = promptForString("Enter the address:");
        String s = promptForString("Enter the phone number: ");

        String option = promptForString("Would you like to lease or finance? ");

        if (option.equalsIgnoreCase("lease")) {
            contract = new LeaseContract(date, customerName, address, carInventory.getVehicleById(vin).get());
        } else if (option.equalsIgnoreCase("finance")) {
            contract = new SalesContract(date, customerName, address, carInventory.getVehicleById(vin).get(), true);
        } else {
            formatOutput("Invalid option!");
        }

        if (contract != null) {
            contractList.add(contract);
            carInventory.getVehicleById(vin).ifPresentOrElse(
                    vehicle -> carInventory.removeVehicle(vehicle),
                    () -> System.out.println("Vehicle with VIN " + vin + " not found")
            );
            DealershipFileManager.updateAndSaveDealer(dealership);
            ContractFileManager.updateAndSaveDealer(dealership, "contracts.csv");
            formatOutput(contract.toString());
        }
        endingAnimation();
    }

    private void getAllContracts() {
        if (contractList.isEmpty()) {
            System.out.println("Contract list is empty!");
            return;
        }

        loadingAnimation();
        contractList.parallelStream().forEach(System.out::println);
        endingAnimation();
    }

    private void getLastTenContracts() {
        contractList.stream().limit(10).forEach(System.out::println);
    }

    private void printAdminInterfaceMenu() {
        String info =
                """
                %s|%s|%s
                Welcome to the Car Dealership Inventory System
                Please select an option:
                1. Display all contracts
                2. Display ten contracts
                0. Exit
                """.formatted(dealership.getName(), dealership.getAddress(), dealership.getPhone());

        System.out.println(info);

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
                11. Remove a vehicle from the inventory by vin
                12. Sell or Lease vehicle
                13. Admin Interface
                0. Exit
                """.formatted(dealership.getName(), dealership.getAddress(), dealership.getPhone());

        System.out.println(info);
    }

}
