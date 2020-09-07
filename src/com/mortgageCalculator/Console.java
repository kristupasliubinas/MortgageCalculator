package com.mortgageCalculator;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    public static double readNumberInput(String prompt, int minValue, int maxValue) {
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
