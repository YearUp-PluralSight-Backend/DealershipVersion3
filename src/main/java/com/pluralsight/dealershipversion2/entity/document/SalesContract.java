package com.pluralsight.dealershipversion2.entity.document;


import com.pluralsight.dealershipversion2.entity.Car.Car;

public class SalesContract extends Contract{

    private final double SALE_TAX = 0.95;
    private final double RECORDING_FEE = 100;
    private double proceesingFee;
    private boolean isFinance;

    public SalesContract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment, boolean isFinance) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.proceesingFee = carSold.getPrice() > 10000? 495 : 295;
        this.isFinance = isFinance;
    }

    public SalesContract(boolean isFinance) {
        this.isFinance = isFinance;
    }


    /*

     */
    /**
     * @return
     */
    @Override
    public double getTotalPrice() {
        if(!isFinance) return  0;
        return carSold.getPrice() >= 10000? getMonthlypayment() * 48: getMonthlypayment() * 24;
    }

    /**
     * @return
     */
    @Override
    public double getMonthlypayment() {
        if(!isFinance) return  0;
        double monthlyPayment = 0;
        if (carSold.getPrice() >= 10000) {
            monthlyPayment = (carSold.getPrice() * (0.0425 / 12) /  Math.pow(1 - (1 + 0.0425 / 12), 48));
        } else {
            monthlyPayment = (carSold.getPrice() * (0.0525 / 12) /  Math.pow(1 - (1 + 0.0525 / 12), 24));

        }
        return monthlyPayment;
    }


    @Override
    public double getPrice() {
        return  1;
    }
}
