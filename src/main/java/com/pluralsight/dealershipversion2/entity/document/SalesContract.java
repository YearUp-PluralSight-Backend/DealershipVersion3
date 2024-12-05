package com.pluralsight.dealershipversion2.entity.document;

import com.pluralsight.dealershipversion2.entity.vehicle.Vehicle;
import com.pluralsight.dealershipversion2.utils.BankCalculation;
import lombok.Data;

@Data
public class SalesContract extends Contract {

    private double salesTax;
    private double totalPrice;
    private double recordingFee;
    private double processingFee;
    private double monthlyPayment;
    private boolean isFinance;

    @Override
    public String toString() {
        return String.format(
                "SALE    | %-10s | %-15s | %-20s | %-6d | %-6d | %-10s | %-10s | %-6s | %-6s | %,10d | $%,.1f | $%,.1f | $%,.1f | $%,.1f | $%,.1f | %-3s | $%,.1f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(), carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getSaleTax(), this.getRecordingFee(), this.getProceesingFee(), this.getTotalPrice(), this.isFinance(), this.getMonthlypayment()
        );
    }

    /**
     * return a format contract string
     * @return contract
     */
    public String toStringToFile() {
        return String.format(
                "SALE|%s|%s|%s|%d|%d|%-10s|%-10s|%-4s|%-10s|%,10d|$%,.2f|$%,.2f|$%,.2f|$%,.2f|$%,.2f|%-3s|$%,.2f",
                super.getDate(), super.getName(), super.getEmail(), carSold.getVin(), carSold.getYear(),
                carSold.getMake(), carSold.getModel(), carSold.getVehicleType(), carSold.getColor(),
                carSold.getOdometer(), carSold.getPrice(), this.getSaleTax(), this.getRecordingFee(),
                this.getProceesingFee(), this.getTotalPrice(), this.isFinance(), this.getMonthlypayment()
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