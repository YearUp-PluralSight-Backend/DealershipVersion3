package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Car;
import com.pluralsight.dealershipversion2.utils.BankCalculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Represents a lease contract for a car.
 */
public class LeaseContract extends Contract {

    // The expected ending value of the car at the end of the lease
    private double exceptedEndingValue;

    // The fee associated with the lease
    private double leaseFee;

    /**
     * Constructs a LeaseContract with the specified details.
     *
     * @param date          the date of the contract
     * @param name          the name of the lessee
     * @param email         the email of the lessee
     * @param carSold       the car being leased
     * @param totalPrice    the total price of the lease
     * @param monthlyPayment the monthly payment amount
     */
    public LeaseContract(String date, String name, String email, Car carSold, double exceptedEndingValue, double leaseFee, double totalPrice, double monthlyPayment) {
        super(date, name, email, carSold, totalPrice, monthlyPayment);
        this.exceptedEndingValue = carSold.getPrice() * 0.5;  // carSold.getPrice() * 0.5;
        this.leaseFee = carSold.getPrice() * 0.07; // carSold.getPrice() * 0.07;
    }


    public LeaseContract(String date, String name, String email, Car carSold) {
        super(date, name, email, carSold);
        this.exceptedEndingValue = carSold.getPrice() * 0.5;  // carSold.getPrice() * 0.5;
        this.leaseFee = carSold.getPrice() * 0.07; // carSold.getPrice() * 0.07;
    }

    /**
     * Default constructor for LeaseContract.
     */
    public LeaseContract() {
    }

    /**
     *
     * @return ending value of the vehicle
     */
    public double getExceptedEndingValue() {
        return carSold.getPrice() * 0.5;
    }

    /**
     *
     * @return lease fee
     */
    public double getLeaseFee() {
        return carSold.getPrice() * 0.07;
    }

    /**
     * Calculates and returns the total price of the lease contract.
     *
     * @return the total price of the lease contract
     */
    @Override
    public double getTotalPrice() {
        return getMonthlypayment() * 36;
    }

    /**
     * Calculates and returns the monthly payment amount for the lease.
     *
     * @return the monthly payment amount for the lease
     */
    @Override
    public double getMonthlypayment() {

        double p = carSold.getPrice();
        double r = 0.04 / 12;
        int n = 36;
        return BankCalculation.getMonthlyPayment(p, r, n);

//        BigDecimal p = BigDecimal.valueOf(carSold.getPrice());
//        BigDecimal r = BigDecimal.valueOf(0.04 / 12);
//        int n = 36;
//        MathContext mc = new MathContext(10);
//        return Double.parseDouble(price.multiply(monthlyRate, mc)
//                .divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(monthlyRate, mc).pow(-leaseTerm, mc), mc), mc).toString());
    }

    /**
     * Return a formatted contract string.
     * @return contract
     */
    @Override
    public String toString() {
        return String.format(
                "LEASE   | %-10s | %-15s | %-15s | %-6s | %-6s | %-10s | %-10s | %-6s | %-6s | %,10d | $%,.2f | $%,.2f | $%,.2f | $%,.2f | $%,.2f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.exceptedEndingValue, this.leaseFee,
                this.getTotalPrice(), this.getMonthlypayment()
        );
    }


    public String toStringToFile() {
        return String.format(
                "LEASE|%s|%s|%s|%d|%d|%-10s|%-10s|%-4s|%-10s|%,10d|$%,.2f|$%,.2f|$%,.2f|$%,.2f|$%,.2f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.exceptedEndingValue, this.leaseFee,
                this.getTotalPrice(), this.getMonthlypayment()
        );
    }

}