package com.pluralsight.dealershipversion2.entity.document;



import lombok.Data;

/**
 * Represents a lease contract in the dealership system.
 */

@Data
public class LeaseContract extends Contract {

    private double exceptedEndingValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;
    private int leaseDuration;


    @Override
    public String toString() {
        return String.format(
                "LEASE   | %-10s | %-15s | %-20s | %-6d | %-6d | %-10s | %-10s | %-6s | %-6s | %,10d | $%,.1f | $%,.1f | $%,.1f | $%,.1f | $%,.1f",
                super.getContractDate(), super.getCustomerName(), super.getCustomerEmail(), super.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getExceptedEndingValue(), this.getLeaseFee(),
                this.getTotalPrice(), this.getMonthlyPayment()
        );
    }

    public String toStringToFile() {
        return String.format(
                "LEASE|%s|%s|%s|%d|%d|%-10s|%-10s|%-4s|%-10s|%,10d|$%,.2f|$%,.2f|$%,.2f|$%,.2f|$%,.2f",
                super.getContractDate(), super.getCustomerName(), super.getCustomerEmail(), super.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getExceptedEndingValue(), this.getLeaseFee(),
                this.getTotalPrice(), this.getMonthlyPayment()
        );
    }

    /**
     * @return  a vehicle by vin
     */
    @Override
    public String findVehicleByVin() {
        return "";
    }
}