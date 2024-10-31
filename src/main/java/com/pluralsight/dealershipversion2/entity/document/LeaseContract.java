package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.Car.Car;

public class LeaseContract extends Contract{

    private double exceptedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.exceptedEndingValue = carSold.getPrice() * 0.5;
        this.leaseFee = carSold.getPrice() * 0.07;

    }

    public LeaseContract() {

    }

    /**
     * @return
     */
    @Override
    public double getTotalPrice() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public double getMonthlypayment() {
        return 0;
    }
}
