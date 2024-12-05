package com.pluralsight.dealershipversion2.repository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository<Contract> {

    List<Contract> getContractByCustomerName(String customerName);
    List<Contract> getContractByDate(String date);
    List<Contract> getContractByDealershipId(int dealershipId);
    List<Contract> getContractByCustomerEmail(String email);
    List<Contract> getPriceRange(double minPrice, double maxPrice);
    List<Contract> getContractByModel(String model);
    List<Contract> getContractByMake(String make);
    List<Contract> getContractByYearRange(int minYear, int maxYear);
    List<Contract> getContractByType(String type);
    List<Contract> getContractByColor(String color);
    List<Contract> getContractByMileageRange(int minMileage, int maxMileage);
    Optional<Contract> getContractByVin(int vin);
}