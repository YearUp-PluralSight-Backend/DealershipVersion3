package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.utils.BankCalculation;
import lombok.Getter;

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


    public SalesContract(String date, String name, String email, Car carSold, boolean isFinance) {
        super(date, name, email, carSold);
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
        if (!isFinance) return carSold.getPrice() + saleTax  + recordingFee + proceesingFee;
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
        double p = this.carSold.getPrice();
        double r = p >= 10000 ? 0.045 / 12: 0.0525 / 12;
        int n = p >= 10000 ? 48 : 24;
        return BankCalculation.getMonthlyPayment(p, r, n);
//        monthlyPayment = (p * r) / Math.pow(1 - (1 + r), n);
//        return monthlyPayment;
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

    /**
     * return a format contract string
     * @return contract
     */
    @Override
    public String toString() {
        return String.format(
                "SALE    | %-10s | %-15s | %-20s | %-6d | %-6d | %-10s | %-10s | %-6s | %-6s | %,10d | $%,.1f | $%,.1f | $%,.1f | $%,.1f | $%,.1f | %-3s | $%,.1f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(), carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getSaleTax(), this.getRecordingFee(), this.getProceesingFee(), this.getTotalPrice(), this.isFinance(), this.getMonthlypayment()
        );
    }


    /**
     * return a format contract string
     * @return contract
     */
    public String toStringToFile() {
        return String.format(
                "SALE|%s|%s|%s|%d|%d|%-10s|%-10s|%-4s|%-10s|%,10d|$%,.2f|$%,.2f|$%,.2f|$%,.2f|$%,.2f|%-3s|$%,.2f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getSaleTax(), this.getRecordingFee(),
                this.getProceesingFee(), this.getTotalPrice(), this.isFinance(), this.getMonthlypayment()
        );
    }



}