package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.Car.Car;

public class LeaseContract extends Contract{


    public LeaseContract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
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
