package com.pluralsight.dealershipversion2.utils;

public class BankCalculation {
    public static double getMonthlyPayment(double p, double r, int n) {
        // p = principal, r = monthly rate, n = month of terms
        return p * (r * Math.pow(1 + r, n)) / (Math.pow(1 - (1 + r), n) - 1);
    }
}
