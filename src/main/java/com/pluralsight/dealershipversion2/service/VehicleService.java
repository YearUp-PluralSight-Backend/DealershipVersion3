package com.pluralsight.dealershipversion2.service;


import com.pluralsight.dealershipversion2.config.DataBase;
import com.pluralsight.dealershipversion2.entity.vehicle.Vehicle;
import com.pluralsight.dealershipversion2.entity.vehicle.Vehicle;
import com.pluralsight.dealershipversion2.repository.imple.VehicleDao;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class VehicleInventory {

    private static VehicleInventory vehicleInventory;
    private final VehicleDao vehicleDao = new VehicleDao(DataBase.getInstance());

    private VehicleInventory() {
    }

    public static VehicleInventory getInstance() {
        if (vehicleInventory != null) {
            return vehicleInventory;
        }
        vehicleInventory = new VehicleInventory();
        return vehicleInventory;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return vehicleDao.getVehicleByPriceRange(min, max);
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return vehicleDao.getVehicleByMakeModel(make, model);
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return vehicleDao.getVehicleByYearRange(min, max);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return vehicleDao.getVehicleByColor(color);
    }

    public List<Vehicle>getVehiclesByMileage(double min, double max) {
        return vehicleDao.getVehicleByMileageRange(min, max);
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return vehicleDao.getVehicleByType(vehicleType);
    }

    public List<Vehicle> getVehicleByMake(String make) {
        return vehicleDao.getVehicleByMake(make);
    }

    public List<Vehicle> getVehicleByModel(String model) {
        return vehicleDao.getVehicleByModel(model);
    }

    public List<Vehicle>  getAllVehicles() {
        return vehicleDao.read();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return this.vehicleDao.create(vehicle);

    }

    public Optional<Vehicle> getVehicleById(int id) {

        return vehicleDao.read(id);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return this.vehicleDao.delete(vehicle.getVin());
    }

    public boolean removeVehicleById(int vin) {
        return this.vehicleDao.delete(vin);
    }

}
