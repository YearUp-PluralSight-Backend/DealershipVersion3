package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the dealership system.
 */
@Data
public class User {

    // The account identifier for the user
    private String account;

    // The password for the user's account
    private String password;

    // The name of the user
    private String name;

    // The address of the user
    private String Address;

    // The phone number of the user
    private String phone;

    // The list of contracts associated with the user
    private List<Contract> contractList;

    // The list of cars associated with the user
    private List<Car> carList;

    // The vehicle inventory associated with the user
    private VehicleInventory inventory;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructs a User with the specified details.
     *
     * @param account  the account identifier for the user
     * @param password the password for the user's account
     * @param name     the name of the user
     * @param address  the address of the user
     * @param phone    the phone number of the user
     */
    public User(String account, String password, String name, String address, String phone) {
        this.account = account;
        this.password = password;
        this.name = name;
        Address = address;
        this.phone = phone;
        this.carList = new ArrayList<>();
    }
}