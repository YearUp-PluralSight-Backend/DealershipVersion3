package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a sales contract for a car.
 */
public class SalesContract extends Contract {

    // Constants for sale tax and recording fee
    @Getter
    private  double saleTax;
    @Getter
    private double recordingFee = 100;
    @Getter
    private double proceesingFee;
    @Getter
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
    public SalesContract(String date, String name, String email, Car carSold, double saleTax, double recordingFee, double proceesingFee, double totalPrice, boolean isFinance, double monthlyPayment) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.saleTax = saleTax * 0.05;
        this.recordingFee = 100;
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
        return carSold.getPrice() >= 10000 ? getMonthlypayment() * 48 + saleTax + recordingFee + proceesingFee: getMonthlypayment() * 24 + saleTax + recordingFee + proceesingFee;
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

        /*
        P = Loan principal (amount borrowed)

        r = monthly interest rate (as a decimal; e.g., 13% = 0.13 / 12)

        n = Loan term in months (e.g., for a 5-year loan, n=60)
         */
        double p = carSold.getPrice();
        double r = p >= 10000 ? 0.045 / 12: 0.0525 / 12;
        double n = p >= 10000 ? 48 : 24;
        monthlyPayment = (p * r) / Math.pow(1 - (1 + r), n);
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