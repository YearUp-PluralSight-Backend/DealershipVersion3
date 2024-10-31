package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a lease contract for a car.
 */
public class LeaseContract extends Contract {

    // The expected ending value of the car at the end of the lease
    @Getter
    @Setter
    private double exceptedEndingValue;

    // The fee associated with the lease
    @Getter
    @Setter
    private double leaseFee;

    /**
     * Constructs a LeaseContract with the specified details.
     *
     * @param date          the date of the contract
     * @param name          the name of the lessee
     * @param email         the email of the lessee
     * @param carSold       the car being leased
     * @param totalPrice    the total price of the lease
     * @param monthlyPayment the monthly payment amount
     */
    public LeaseContract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.exceptedEndingValue = carSold.getPrice() * 0.5;
        this.leaseFee = carSold.getPrice() * 0.07;
    }

    /**
     * Default constructor for LeaseContract.
     */
    public LeaseContract() {
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
        monthlyPayment = (carSold.getPrice() * (0.04 / 12) / Math.pow(1 - (1 + 0.04 / 12), 36));
        return monthlyPayment;
    }
}