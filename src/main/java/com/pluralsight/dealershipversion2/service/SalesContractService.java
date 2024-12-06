package com.pluralsight.dealershipversion2.service;

import com.pluralsight.dealershipversion2.config.DataBase;
import com.pluralsight.dealershipversion2.repository.imple.SalesContractDao;

public class ContractService {

    private static ContractService contractService;
    private final SalesContractDao contractDao = new SalesContractDao(DataBase.getInstance());

    private ContractService() {
    }

    public static ContractService getInstance() {
        if (contractService != null) {
            return contractService;
        }
        contractService = new ContractService();
        return contractService;
    }




}
