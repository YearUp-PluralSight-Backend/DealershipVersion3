package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

import java.util.Optional;

/**
 * Represents a lease contract for a car.
 */
public class LeaseContract extends Contract {

    // The expected ending value of the car at the end of the lease
    private double exceptedEndingValue;

    // The fee associated with the lease
    private double leaseFee;

    private Car car;

    private VehicleInventory vehicleInventory;
    /**
     * Constructs a LeaseContract with the specified details.
     *
     * @param date          the date of the contract
     * @param name          the name of the lessee
     * @param email         the email of the lessee
     * @param vin       the car being leased
     * @param totalPrice    the total price of the lease
     * @param monthlyPayment the monthly payment amount
     */
    public LeaseContract(String date, String name, String email, int vin, double exceptedEndingValue, double leaseFee, double totalPrice, double monthlyPayment) {
        super(date, name, email, vin, totalPrice, monthlyPayment);
        this.exceptedEndingValue = exceptedEndingValue;  // carSold.getPrice() * 0.5;
        this.leaseFee = leaseFee; // carSold.getPrice() * 0.07;
        vehicleInventory = VehicleInventory.getInstance();
    }

    /**
     * Default constructor for LeaseContract.
     */
    public LeaseContract() {
    }


    public Car getCar() {
        return vehicleInventory.getVehicleById(super.vin).get();
    }

    /**
     *
     * @return ending value of the vehicle
     */
    public double getExceptedEndingValue() {
        return getCar().getPrice() * 0.5;
    }

    /**
     *
     * @return lease fee
     */
    public double getLeaseFee() {
        return getCar().getPrice() * 0.07;
    }

    /**
     * Calculates and returns the total price of the lease contract.
     *
     * @return the total price of the lease contract
     */
    @Override
    public double getTotalPrice() {
        return getMonthlypayment() * 36;
    }

    /**
     * Calculates and returns the monthly payment amount for the lease.
     *
     * @return the monthly payment amount for the lease
     */
    @Override
    public double getMonthlypayment() {
        double monthlyPayment = 0;
        monthlyPayment = (getCar().getPrice() * (0.04 / 12) / Math.pow(1 - (1 + 0.04 / 12), 36));
        return monthlyPayment;
    }
}