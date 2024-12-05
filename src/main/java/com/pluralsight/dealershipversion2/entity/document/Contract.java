package com.pluralsight.dealershipversion2.entity.document;

import lombok.*;

/**
 * Represents a contract in the dealership system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Contract {

    private int dealershipId;
    private int vin;
    private String contractDate;
    private String customerName;
    private String customerEmail;

    /**
     * Finds a vehicle by its VIN.
     *
     * @return the vehicle found by its VIN
     */
    public abstract String findVehicleByVin();
}
