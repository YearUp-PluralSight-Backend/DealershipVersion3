package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.*;

import java.util.List;

import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
public class Dealer extends User{

    private VehicleInventory inventory;

    public Dealer(VehicleInventory inventory) {
        this.inventory = inventory;
    }

    public Dealer(String account, String password, VehicleInventory inventory) {
        super(account, password);
        this.inventory = inventory;
    }

    public Dealer(String name, String address, String phone, VehicleInventory inventory) {
        super(name, address, phone);
        this.inventory = inventory;
    }

    public Dealer(String account, String password, String name, String address, String phone, VehicleInventory inventory) {
        super(account, password, name, address, phone);
        this.inventory = inventory;
    }

    public Dealer(String account, String password, String name, String address, String phone, List<Contract> contractList, List<Car> carList, VehicleInventory inventory) {
        super(account, password, name, address, phone, contractList, carList);
        this.inventory = inventory;
    }
}
