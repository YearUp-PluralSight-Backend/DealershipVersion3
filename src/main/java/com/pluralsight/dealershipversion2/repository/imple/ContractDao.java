package com.pluralsight.dealershipversion2.repository.imple;

import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.repository.CRUDOperation;
import com.pluralsight.dealershipversion2.repository.ContractRepository;

import java.util.List;
import java.util.Optional;

public class ContractDao implements ContractRepository, CRUDOperation<Contract> {


    /**
     * @param contract
     * @return
     */
    @Override
    public boolean create(Contract contract) {
        return CRUDOperation.super.create(contract);
    }

    /**
     * @return
     */
    @Override
    public List<Contract> read() {
        return CRUDOperation.super.read();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Contract> read(int id) {
        return CRUDOperation.super.read(id);
    }

    /**
     * @param id
     * @param contract
     * @return
     */
    @Override
    public boolean update(int id, Contract contract) {
        return CRUDOperation.super.update(id, contract);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        return CRUDOperation.super.delete(id);
    }

    /**
     * @param customerName
     * @return
     */
    @Override
    public List<Contract> getContractByCustomerName(String customerName) {
        return List.of();
    }

    /**
     * @param date
     * @return
     */
    @Override
    public List<Contract> getContractByDate(String date) {
        return List.of();
    }

    /**
     * @param dealershipId
     * @return
     */
    @Override
    public List<Contract> getContractByDealershipId(int dealershipId) {
        return List.of();
    }

    /**
     * @param email
     * @return
     */
    @Override
    public List<Contract> getContractByCustomerEmail(String email) {
        return List.of();
    }

    /**
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<Contract> getPriceRange(double minPrice, double maxPrice) {
        return List.of();
    }

    /**
     * @param model
     * @return
     */
    @Override
    public List<Contract> getContractByModel(String model) {
        return List.of();
    }

    /**
     * @param make
     * @return
     */
    @Override
    public List<Contract> getContractByMake(String make) {
        return List.of();
    }

    /**
     * @param minYear
     * @param maxYear
     * @return
     */
    @Override
    public List<Contract> getContractByYearRange(int minYear, int maxYear) {
        return List.of();
    }

    /**
     * @param type
     * @return
     */
    @Override
    public List<Contract> getContractByType(String type) {
        return List.of();
    }

    /**
     * @param color
     * @return
     */
    @Override
    public List<Contract> getContractByColor(String color) {
        return List.of();
    }

    /**
     * @param minMileage
     * @param maxMileage
     * @return
     */
    @Override
    public List<Contract> getContractByMileageRange(int minMileage, int maxMileage) {
        return List.of();
    }

    /**
     * @param vin
     * @return
     */
    @Override
    public Optional<Contract> getContractByVin(int vin) {
        return Optional.empty();
    }
}
