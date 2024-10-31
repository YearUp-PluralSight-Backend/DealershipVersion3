package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class User {

    private String account;
    private String password;
    private String name;
    private String Address;
    private String phone;
    private List<Contract> contractList;
    private List<Car> carList;


    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String name, String address, String phone) {
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public User(String account, String password, String name, String address, String phone) {
        this.account = account;
        this.password = password;
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public User(String account, String password, String name, String address, String phone, List<Contract> contractList, List<Car> carList) {
        this.account = account;
        this.password = password;
        this.name = name;
        Address = address;
        this.phone = phone;
        this.contractList = contractList;
        this.carList = carList;
    }
}
