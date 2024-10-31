package com.pluralsight.dealershipversion2.entity.vehicle;

public interface AddsOn {

    void applyToCar(Car car);
    double getPrice();
    String getDescription();

}
