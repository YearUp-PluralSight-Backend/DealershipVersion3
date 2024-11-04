package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.repository.ContractFileManager;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.*;
import lombok.Data;

/**
 * Represents a dealer in the dealership system.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Dealer extends User {

    /**
     * Default constructor for Dealer.
     * Initializes the inventory and contract list for the dealer.
     */
    public Dealer() {
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());
    }

    /**
     * Constructs a Dealer with the specified details.
     * Initializes the inventory and contract list for the dealer.
     *
     * @param account  the account identifier for the dealer
     * @param password the password for the dealer's account
     * @param name     the name of the dealer
     * @param address  the address of the dealer
     * @param phone    the phone number of the dealer
     */
    public Dealer(String account, String password, String name, String address, String phone) {
        super(account, password, name, address, phone);
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());
    }






}