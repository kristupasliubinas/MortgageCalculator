package com.mortgageCalculator;

import java.text.NumberFormat;

public class Report {
    private Mortgage mortgage;


    public Report(Mortgage mortgage) {
        this.mortgage = mortgage;
    }


    public void generateReport() {
        printMortgage();
        printPaymentSchedule();
    }

    private void printMortgage() {
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage.calculateMortgage());
        // right side returns a formatted string ^^
        System.out.println("\nMORTGAGE\n--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private void printPaymentSchedule() {
        String balanceFormatted;
        System.out.println("\nPAYMENT SCHEDULE\n--------");
        double balance = mortgage.getPrincipal();
        byte madePayments = 0;
        while (balance != 0) {
            madePayments++;
            balance = mortgage.calculateBalance(madePayments);
            balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(balanceFormatted);
        }
    }
}
