package com.mortgageCalculator;

public class Mortgage {
    private int principal;
    private float annualInterest;
    private int period;


    public Mortgage(int principal, float annualInterest, int period) {
        setPrincipal(principal);
        setAnnualInterest(annualInterest);
        setPeriod(period);
    }


    public int getPrincipal() {
        return principal;
    }

    private void setPrincipal(int principal) {
        if (principal <= 0)
            throw new IllegalArgumentException("Principal cannot be 0 or less");
        this.principal = principal;
    }

    private void setAnnualInterest(float annualInterest) {
        if (annualInterest <= 0)
            throw new IllegalArgumentException("Principal cannot be 0 or less");
        this.annualInterest = annualInterest;
    }

    private void setPeriod(int period) {
        if (period <= 0)
            throw new IllegalArgumentException("Principal cannot be 0 or less");
        this.period = period;
    }

    private float calculateMonthlyInterest() {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        return (annualInterest / PERCENT) / MONTHS_IN_YEAR;
    }

    private int calculateNumberOfPayments(){
        final byte MONTHS_IN_YEAR = 12;
        return period * MONTHS_IN_YEAR;
    }

    public double calculateMortgage() {
        // Intermediate calculations
        float monthlyInterest = calculateMonthlyInterest();
        int numberOfPayments = calculateNumberOfPayments();

        // Monthly mortgage payment according to the formula
        double mortgage = principal * ((monthlyInterest * Math.pow(1+monthlyInterest, numberOfPayments)) /
                (Math.pow(1+monthlyInterest, numberOfPayments) - 1));

        return mortgage;
    }

    public double calculateBalance(byte madePayments) {
        // Intermediate calculations
        float monthlyInterest = calculateMonthlyInterest();
        int numberOfPayments = calculateNumberOfPayments();

        // Remaining loan balance according to the formula
        double balance = principal * (Math.pow(1+monthlyInterest, numberOfPayments) -
                Math.pow(1+monthlyInterest, madePayments)) /
                (Math.pow(1+monthlyInterest, numberOfPayments) - 1);

        return balance;
    }
}
