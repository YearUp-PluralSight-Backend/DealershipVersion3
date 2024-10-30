package com.pluralsight.dealershipversion2.service;


import com.pluralsight.dealershipversion2.entity.Car.Car;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventory {

    private final List<Car> inventory = new ArrayList<>();
    // Optional: Method to get the total number of vehicles
    // Implementation here
    @Getter
    private int totalOfVehicles;

    public List<Car> getVehiclesByPrice(double min, double max) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getPrice() >= min && vehicle.getPrice() <= max)
                .toList();
    }

    // Method to get vehicles by make and model
    public List<Car> getVehiclesByMakeModel(String make, String model) {
        // Implementation here
        return inventory.stream()
                .filter(vehicle -> vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model))
                .toList();
    }

    // Method to get vehicles by year range
    public List<Car> getVehiclesByYear(int min, int max) {
        // Implementation here
        return inventory.stream()
                .filter(vehicle -> vehicle.getYear() >= min && vehicle.getYear() <= max)
                .toList();
    }

    // Method to get vehicles by color
    public List<Car> getVehiclesByColor(String color) {
        // Implementation here
        return inventory.stream()
                .filter(vehicle -> vehicle.getColor().equalsIgnoreCase(color))
                .toList();
    }

    // Method to get vehicles by mileage range
    public List<Car> getVehiclesByMileage(double min, double max) {
        // Implementation here
        return inventory.stream()
                .filter(vehicle -> vehicle.getOdometer() >= min && vehicle.getOdometer() <= max)
                .toList();
    }

    // Method to get vehicles by type
    public List<Car> getVehiclesByType(String vehicleType) {
        // Implementation here
        return inventory.stream()
                .filter(vehicle -> vehicle.getVehicleType().equalsIgnoreCase(vehicleType))
                .toList();
    }

    // Method to get all vehicles in inventory
    public List<Car> getAllVehicles() {
        // Implementation here
        return inventory;
    }

    // Method to add a vehicle to the inventory
    public void addVehicle(Car vehicle) {
        // Implementation here
        inventory.add(vehicle);
        DealershipFileManager.updateAndSaveDealership(CommandLineInterface.getDealership());
        totalOfVehicles++;
    }

    // Method to remove a vehicle from the inventory
    public boolean removeVehicle(Car vehicle) {
        // Implementation here
        totalOfVehicles--;
        DealershipFileManager.updateAndSaveDealership(CommandLineInterface.getDealership());
        return inventory.remove(vehicle);
    }

    public Car removeVehicleById(int vin) {
        // Implementation here
        totalOfVehicles--;
        DealershipFileManager.updateAndSaveDealership(CommandLineInterface.getDealership());
        return inventory.remove(vin);
    }

    public void setTotalOfVehicles() {
        this.totalOfVehicles = inventory.size();
    }


}
