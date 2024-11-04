package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public abstract class Contract {


    @Getter
    @Setter
    String date;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    Car carSold;
    @Setter
    double totalPrice;
    @Setter
    double monthlyPayment;

    public Contract(String date, String name, String email, Car carSold, double totalPrice, double monthlyPayment) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.carSold = carSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public Contract(String date, String name, String email, Car carSold) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.carSold = carSold;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlypayment();

    public double getPrice() {
        return  0;
    }
}
