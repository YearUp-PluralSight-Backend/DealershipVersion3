package com.pluralsight.dealershipversion2.repository;

import com.pluralsight.dealershipversion2.utils.InputOutput;
import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.Dealer;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

import java.io.*;
import java.util.List;

public class DealershipFileManager {

    private static final String FILE_NAME = "inventory.csv";

    public static Dealer getDealer() {
        VehicleInventory vehicleInventory = VehicleInventory.getInstance();
        List<Car> vehicles = vehicleInventory.getAllVehicles();
        Dealer dealer = null;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {

            String[] firstLineData = bufferedReader.readLine().trim().split("\\|");
            dealer = new Dealer(firstLineData[0], firstLineData[1], firstLineData[2], vehicleInventory);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] data = line.trim().split("\\|");

                /**

                private int vin;
                private int year;
                private String make;
                private String model;
                private VehicleType vehicleType;
                private ColorCodes color;
                private int odometer;
                private double price;
                 */
                    Car vehicle = new Car(
                            Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            Integer.parseInt(data[6]),
                            Double.parseDouble(data[7]));
                    vehicles.add(vehicle);

                }
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputOutput.formatOutput("You have successfully read data from file:  " + FILE_NAME + "\nTotal of vehicles is: " + vehicles.size());
        vehicles.forEach(System.out::println);
        return dealer;
    }

    public static void updateAndSaveDealer(Dealer Dealer) {

        List<Car> allVehicles = Dealer.getInventory().getAllVehicles();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME))){

            bufferedWriter.write(new StringBuffer()
                    .append(Dealer.getName()).append("|")
                    .append(Dealer.getAddress()).append("|")
                    .append(Dealer.getPhone()).toString()
            );

            for (Car vehicle: allVehicles) {
                /**

                 private int vin;
                 private int year;
                 private String make;
                 private String model;
                 private VehicleType vehicleType;
                 private ColorCodes color;
                 private int odometer;
                 private double price;
                 */
                bufferedWriter.write(new StringBuilder()
                        .append(vehicle.getVin()).append("|")
                        .append(vehicle.getYear()).append("|")
                        .append(vehicle.getMake()).append("|")
                        .append(vehicle.getModel()).append("|")
                        .append(vehicle.getVehicleType()).append("|")
                        .append(vehicle.getColor()).append("|")
                        .append(vehicle.getOdometer()).append("|")
                        .append(vehicle.getPrice()).toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        InputOutput.formatOutput("You have successfully update data and save from file:  " + FILE_NAME + "\nTotal of vehicles is: " + allVehicles.size());

    }
}
