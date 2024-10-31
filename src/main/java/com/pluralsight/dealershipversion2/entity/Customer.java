package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends User{


    public Customer() {
    }

    public Customer(String account, String password) {
        super(account, password);
    }

    public Customer(String name, String address, String phone) {
        super(name, address, phone);
    }

    public Customer(String account, String password, String name, String address, String phone) {
        super(account, password, name, address, phone);
    }

    public Customer(String account, String password, String name, String address, String phone, List<Contract> contractList, List<Car> carList) {
        super(account, password, name, address, phone, contractList, carList);
    }
}
