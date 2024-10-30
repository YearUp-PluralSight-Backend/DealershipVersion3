package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.Record;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer extends User{

    private String name;
    private String Address;
    private String phone;
    private VehicleInventory inventory;
    private List<Record> recordList;
    private List<Contract> contractList;


}
