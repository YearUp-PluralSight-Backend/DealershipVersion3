package com.pluralsight.dealershipversion2.entity;

import lombok.Data;

/**
 * Represents a user in the dealership system.
 */
@Data
public class Dealer {

    // The id of the user
    private int dealershipId;
    // The name of the user
    private String name;

    // The address of the user
    private String Address;

    // The phone number of the user
    private String phone;


}