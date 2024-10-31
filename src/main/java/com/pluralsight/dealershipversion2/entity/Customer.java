package com.pluralsight.dealershipversion2.entity;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.LeaseContract;
import com.pluralsight.dealershipversion2.entity.document.SalesContract;
import com.pluralsight.dealershipversion2.repository.ContractFileManager;
import com.pluralsight.dealershipversion2.service.VehicleInventory;
import com.pluralsight.dealershipversion2.utils.InputOutput;
import lombok.Getter;
import java.util.List;

/**
 * Represents a customer in the dealership system.
 */
@Getter
public class Customer extends User {

    /**
     * Default constructor for Customer.
     * Initializes the inventory and contract list for the customer.
     */
    public Customer() {
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());
    }

    /**
     * Constructs a Customer with the specified details.
     * Initializes the inventory and contract list for the customer.
     *
     * @param account  the account identifier for the customer
     * @param password the password for the customer's account
     * @param name     the name of the customer
     * @param address  the address of the customer
     * @param phone    the phone number of the customer
     */
    public Customer(String account, String password, String name, String address, String phone) {
        super(account, password, name, address, phone);
        super.setInventory(VehicleInventory.getInstance());
        super.setContractList(ContractFileManager.getContract());
    }

    /**
     * Facilitates the purchase of a car by the customer from the dealer.
     *
     * @param customer the customer purchasing the car
     * @param dealer   the dealer selling the car
     * @return the contract for the purchase or lease of the car
     */
    public Contract purchaseCar(Customer customer, Dealer dealer) {

        Contract contract = null;
        String s = InputOutput.promptForString("Would you like finance or lease");
        String date = InputOutput.promptForString("Enter the date: (year month day without spaces): ");
        String name = InputOutput.promptForString("Enter your name: ");
        String email = InputOutput.promptForString("Enter your Email: ");
        Car car = InputOutput.carObject();
        customer.getCarList().add(car);
        dealer.getCarList().add(car);

        dealer.getInventory().removeVehicle(car);
        if (s.equalsIgnoreCase("sale")) {
            // SALE_TAX | RECORDING_FEE | processingFee |  total price | isFinance | monthly payment
            Double saleTax = InputOutput.promptForDouble("Enter the Sale_Tax:");
            Double recordingFee = InputOutput.promptForDouble("Enter the recording fee: ");
            Double processingFee = InputOutput.promptForDouble("Enter the processing fee:");
            Double totalPrice = InputOutput.promptForDouble("Enter the total price: ");
            boolean isFinance = InputOutput.promptForBoolean("Is financed? (Yes or No):  ");
            Double monthlyPayment = InputOutput.promptForDouble("Enter the monthly payment; ");
            contract = new SalesContract(date, name, email, car, saleTax, recordingFee, processingFee, totalPrice, isFinance, monthlyPayment);

        } else {
            //          ending value | processing fee| total price | monthly payment
            Double endingValue = InputOutput.promptForDouble("Enter the Sale_Tax:");
            Double processingFee = InputOutput.promptForDouble("Enter the processing fee:");
            Double totalPrice = InputOutput.promptForDouble("Enter the total price: ");
            Double monthlyPayment = InputOutput.promptForDouble("Enter the monthly payment; ");
            contract = new LeaseContract(date, name, email, car, endingValue, processingFee, totalPrice, monthlyPayment);
        }
        customer.getContractList().add(contract);
        dealer.getContractList().add(contract);
        return contract;
    }
}