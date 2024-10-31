package com.pluralsight.dealershipversion2;

import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.SalesContract;

public class Main {

    public static void main(String[] args) {

        Contract contract = new SalesContract(false);
        System.out.println(contract.getPrice());
    }
}
