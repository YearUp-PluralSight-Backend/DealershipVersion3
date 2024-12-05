package com.pluralsight.dealershipversion2.entity.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    @Override
    public String toString() {
        return String.format(
                "%-10d %-6d %-15s %-15s %-12s %-10s %,10d $%,15.2f",
                vin, year, make, model, vehicleType, color, odometer, price
        );
    }

}
