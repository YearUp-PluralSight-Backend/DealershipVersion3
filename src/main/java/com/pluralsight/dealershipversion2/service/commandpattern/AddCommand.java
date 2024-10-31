package com.pluralsight.dealershipversion2.service.commandpattern;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

public class AddCommand implements Command{
    private VehicleInventory inventory;
    private Car car;

    public AddCommand(VehicleInventory inventory, Car car) {
        this.inventory = inventory;
        this.car = car;
    }

    /**
     *
     */
    @Override
    public void execute() {
        boolean deleted = inventory.addVehicle(car);
        if (deleted) {
            System.out.println("You have deleted the Car " + car);
        } else {
            System.out.println("You failed to delete the Car " + car);
        }
    }

    /**
     *
     */
    @Override
    public void undo() {
        boolean undo = inventory.removeVehicle(car);

        if (undo) {
            System.out.println("Successfully undo the delete operation ");
        } else {
            System.out.println("failed undo the delete operation ");

        }


    }
}
