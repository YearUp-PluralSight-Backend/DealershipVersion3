package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
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

    public abstract double getTotalPrice();
    public abstract double getMonthlypayment();

    public double getPrice() {
        return  0;
    }
}
