package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.repository.ContractFileManager;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import lombok.*;

import java.util.List;

import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
public class Dealer extends User{

    public Dealer() {
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());

    }

    public Dealer(String account, String password, String name, String address, String phone) {
        super(account, password, name, address, phone);
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());
    }
}
