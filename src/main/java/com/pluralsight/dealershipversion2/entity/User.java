package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.Record;
import com.pluralsight.dealershipversion2.service.VehicleInventory;

import java.util.List;

public class User {

    private String account;
    private String password;
    private String name;
    private String Address;
    private String phone;
    private VehicleInventory inventory;
    private List<Contract> contractList;
}
