package com.mortgageCalculator;

public class Main {

    public static void main(String[] args) {

        // Take required user input
        int principal = (int) Console.readNumberInput("Principal (£1K - £1M): ", 1000, 1_000_000);
        // ^^ we need to cast the right side to int because readNumberInput returns a double
        float annualInterest = (float) Console.readNumberInput("Annual Interest Rate: ", 1, 30);
        int period = (int) Console.readNumberInput("Period (years): ", 1, 30);

        var mortgage1 = new Mortgage(principal, annualInterest, period);
        var report1 = new Report(mortgage1);

        report1.generateReport();
    }


}