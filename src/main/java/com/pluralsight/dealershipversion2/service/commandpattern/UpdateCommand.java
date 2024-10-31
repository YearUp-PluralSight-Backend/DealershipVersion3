package com.pluralsight.dealershipversion2.service.commandpattern;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

import java.util.Optional;
import java.util.function.Consumer;

public class UpdateCommand implements Command{

    private VehicleInventory inventory;
    private Car car;
    private int vinNumber;

    public UpdateCommand(VehicleInventory inventory, Car car, int vinNumber) {
        this.inventory = inventory;
        this.car = car;
        this.vinNumber = vinNumber;
    }

    /**
     *
     */
    @Override
    public void execute() {
        Optional<Car> OptionalCar = this.inventory.getVehicleById(vinNumber);
        OptionalCar.ifPresentOrElse(
                car -> {
                    System.out.println("Found car: " + car);
                    Car oldCar = OptionalCar.get();
                    oldCar.setColor(car.getColor());
                    oldCar.setOdometer(car.getOdometer());
                    oldCar.setMake(car.getMake());
                    oldCar.setModel(car.getModel());
                    oldCar.setPrice(car.getPrice());
                    oldCar.setYear(car.getYear());
                    oldCar.setVin(car.getVin());
                    oldCar.setVehicleType(car.getVehicleType());
                },
                () -> System.out.println("Cannot find the car")
        );

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
