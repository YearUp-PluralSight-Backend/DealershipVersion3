package com.pluralsight.dealershipversion2.entity.Car;

public class CargoTray implements AddsOn {

    /**
     * @param car
     */
    @Override
    public void applyToCar(Car car) {

    }

    /**
     * @return
     */
    @Override
    public double getPrice() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public String getDescription() {
        return "";
    }
}
