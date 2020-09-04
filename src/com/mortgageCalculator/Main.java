package com.mortgageCalculator;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Take required user input
        int principal = (int) readNumberInput("Principal (£1K - £1M): ", 1000, 1_000_000);
        // ^^ we need to cast the right side to int because readNumberInput returns a double
        float annualInterest = (float) readNumberInput("Annual Interest Rate: ", 1, 30);
        int period = (int) readNumberInput("Period (years): ", 1, 30);

        printMortgage(principal, annualInterest, period);
        printPaymentSchedule(principal, annualInterest, period);
    }


    public static void printMortgage(int principal, float annualInterest, int period) {
        double mortgage = calculateMortgage(principal, annualInterest, period);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        // right side returns a formatted string ^^
        System.out.println("\nMORTGAGE\n--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public static void printPaymentSchedule(int principal, float annualInterest, int period) {
        String balanceFormatted;
        System.out.println("\nPAYMENT SCHEDULE\n--------");
        double balance = principal;
        byte madePayments = 0;
        while (balance != 0) {
            madePayments++;
            balance = calculateBalance(principal, annualInterest, period, madePayments);
            balanceFormatted = NumberFormat.getCurrencyInstance().format(balance);
            System.out.println(balanceFormatted);
        }
    }

    public static double readNumberInput(String prompt, int minValue, int maxValue) {
        Scanner scanner = new Scanner(System.in);
        double input;

        while (true) {
            System.out.print(prompt);
            input = scanner.nextDouble();
            if (input >= minValue && input <= maxValue)
                break;
            // we can omit the else statement because control flow will go to the next statement either way
            System.out.println("Enter a number between " + minValue + " and " + maxValue);
        }
        return input;
    }

    public static float calculateMonthlyInterest(float annualInterest) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        return (annualInterest / PERCENT) / MONTHS_IN_YEAR;
    }

    public static int calculateNumberOfPayments(int period){
        final byte MONTHS_IN_YEAR = 12;
        return period * MONTHS_IN_YEAR;
    }

    public static double calculateMortgage(int principal, float annualInterest, int period) {
        // Intermediate calculations
        float monthlyInterest = calculateMonthlyInterest(annualInterest);
        int numberOfPayments = calculateNumberOfPayments(period);

        // Monthly mortgage payment according to the formula
        double mortgage = principal * ((monthlyInterest * Math.pow(1+monthlyInterest, numberOfPayments)) /
                (Math.pow(1+monthlyInterest, numberOfPayments) - 1));

        return mortgage;
    }

    public static double calculateBalance(int principal, float annualInterest, int period, byte madePayments) {
        // Intermediate calculations
        float monthlyInterest = calculateMonthlyInterest(annualInterest);
        int numberOfPayments = calculateNumberOfPayments(period);

        // Remaining loan balance according to the formula
        double balance = principal * (Math.pow(1+monthlyInterest, numberOfPayments) -
                Math.pow(1+monthlyInterest, madePayments)) /
                (Math.pow(1+monthlyInterest, numberOfPayments) - 1);

        return balance;
    }
}