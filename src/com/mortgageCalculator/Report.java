package com.mortgageCalculator;

import java.text.NumberFormat;

public class Report {
    /* This class is entirely responsible for presentation.
    * All calculation concerns have been moved to other classes.*/
    private Mortgage mortgage;
    private final NumberFormat currencyInstance; // final - can't reassign new values after it's been assigned in the constructor


    public Report(Mortgage mortgage) {
        this.mortgage = mortgage;
        currencyInstance = NumberFormat.getCurrencyInstance();
    }


    public void generateReport() {
        printMortgage();
        printPaymentSchedule();
    }

    private void printMortgage() {
        String mortgageFormatted = currencyInstance.format(mortgage.calculateMortgage());
        // right side returns a formatted string ^^
        System.out.println("\nMORTGAGE\n--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private void printPaymentSchedule() {
        String balanceFormatted;
        System.out.println("\nPAYMENT SCHEDULE\n--------");
        for (double balance : mortgage.getRemainingBalances()){
            balanceFormatted = currencyInstance.format(balance);
            System.out.println(balanceFormatted);
        }
    }
}
