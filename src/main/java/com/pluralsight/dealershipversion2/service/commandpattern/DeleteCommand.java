package com.pluralsight.dealershipversion2.service.commandpattern;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

public class DeleteCommand implements Command{

    private VehicleInventory inventory;
    private Car car;

    public DeleteCommand(VehicleInventory inventory, Car car) {
        this.inventory = inventory;
        this.car = car;
    }

    /**
     *
     */
    @Override
    public void execute() {
        boolean deleted = inventory.removeVehicle(car);
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
        boolean undo = inventory.addVehicle(car);

        if (undo) {
            System.out.println("Successfully undo the delete operation ");
        } else {
            System.out.println("failed undo the delete operation ");
        }


    }
}
