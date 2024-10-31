package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.Car.Car;

/**
 * Represents a sales contract for a car.
 */
public class SalesContract extends Contract {

    // Constants for sale tax and recording fee
    private final double SALE_TAX = 0.95;
    private final double RECORDING_FEE = 100;
    private double proceesingFee;
    private boolean isFinance;

    /**
     * Constructs a SalesContract with the specified details.
     *
     * @param date          the date of the contract
     * @param name          the name of the buyer
     * @param email         the email of the buyer
     * @param carSold       the car being sold
     * @param totalPrice    the total price of the car
     * @param monthlyPayment the monthly payment amount
     * @param isFinance     whether the purchase is financed
     */
    public SalesContract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment, boolean isFinance) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.proceesingFee = carSold.getPrice() > 10000 ? 495 : 295;
        this.isFinance = isFinance;
    }

    /**
     * Constructs a SalesContract with the specified finance option.
     *
     * @param isFinance whether the purchase is financed
     */
    public SalesContract(boolean isFinance) {
        this.isFinance = isFinance;
    }

    /**
     * Calculates and returns the total price of the contract.
     *
     * @return the total price of the contract
     */
    @Override
    public double getTotalPrice() {
        if (!isFinance) return 0;
        return carSold.getPrice() >= 10000 ? getMonthlypayment() * 48 : getMonthlypayment() * 24;
    }

    /**
     * Calculates and returns the monthly payment amount.
     *
     * @return the monthly payment amount
     */
    @Override
    public double getMonthlypayment() {
        if (!isFinance) return 0;
        double monthlyPayment = 0;
        if (carSold.getPrice() >= 10000) {
            monthlyPayment = (carSold.getPrice() * (0.0425 / 12) / Math.pow(1 - (1 + 0.0425 / 12), 48));
        } else {
            monthlyPayment = (carSold.getPrice() * (0.0525 / 12) / Math.pow(1 - (1 + 0.0525 / 12), 24));
        }
        return monthlyPayment;
    }

    /**
     * Returns the price of the contract.
     *
     * @return the price of the contract
     */
    @Override
    public double getPrice() {
        return 1;
    }
}