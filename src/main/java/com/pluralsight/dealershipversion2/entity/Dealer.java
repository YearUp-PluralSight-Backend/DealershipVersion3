package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.*;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer extends User{

    private String name;
    private String Address;
    private String phone;
    private VehicleInventory inventory;
    private List<java.lang.Record> recordList;


}
