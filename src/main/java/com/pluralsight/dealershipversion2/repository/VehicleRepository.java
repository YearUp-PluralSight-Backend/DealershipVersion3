package com.pluralsight.dealershipversion2.repository;

import java.util.List;

public interface VehicleRepository<Vehicle> {

    List<Vehicle> getVehicleByPriceRange(double minPrice, double maxPrice);
    List<Vehicle> getVehicleByMake(String make);
    List<Vehicle> getVehicleByModel(String model);
    List<Vehicle> getVehicleByYearRange(int minYear, int maxYear);
    List<Vehicle> getVehicleByType(String type);
    List<Vehicle> getVehicleByColor(String color);
    List<Vehicle> getVehicleByMileageRange(int minMileage, int maxMileage);
}