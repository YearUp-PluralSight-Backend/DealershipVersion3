package com.pluralsight.dealershipversion2.entity.vehicle;

public class SplashGuards implements AddsOn{
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
