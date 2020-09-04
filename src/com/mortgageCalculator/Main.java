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

        var mortgage1 = new Mortgage(principal, annualInterest, period);
        var report1 = new Report(mortgage1);

        report1.generateReport();
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
}