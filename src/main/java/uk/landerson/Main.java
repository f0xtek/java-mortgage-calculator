/*
 * Mortgage Calculator
 *
 * Calculate the monthly repayment for a mortgage given the loan principal, annual interest and loan term in years.
 */
package uk.landerson;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    private final static byte PERCENT = 100;
    private final static byte MONTHS_IN_YEAR = 12;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float yearlyInterest = (float) readNumber("Annual interest rate (%): ", 0, 30) / PERCENT;
        byte numYears = (byte) readNumber("Loan terms in years: ", 1, 30);

        double monthlyRepayment = caclulateMortgage(principal, yearlyInterest, numYears);
        int totalNumPayments = calculateTotalNumPayments(numYears);
        double monthlyInterest = calculateMonthlyInterest(yearlyInterest);

        printMonthlyRepayment(monthlyRepayment);
        printPaymentPlan(totalNumPayments, principal, monthlyInterest);
    }

    private static int calculateTotalNumPayments(byte numYears) {
        return (MONTHS_IN_YEAR * numYears);
    }

    private static double calculateMonthlyInterest(double yearlyInterest) {
        return yearlyInterest / MONTHS_IN_YEAR;
    }

    private static double calculateRemainingBalance(int principal, double monthlyInterest, int totalNumPayments, int numPaymentsMade) {
        return (principal *
                (Math.pow((1 + monthlyInterest), totalNumPayments) - Math.pow((1 + monthlyInterest), numPaymentsMade)))
                / ((Math.pow((1 + monthlyInterest), totalNumPayments)) - 1);
    }

    private static double caclulateMortgage(int principal, double yearlyInterest, byte numYears) {
        int termInMonths = calculateTotalNumPayments(numYears);

        if (yearlyInterest == 0.0) {
            return (double) principal / termInMonths;
        }

        double monthlyInterest = calculateMonthlyInterest(yearlyInterest);

        return principal
                * (((monthlyInterest * Math.pow((1 + monthlyInterest), termInMonths))
                / (Math.pow(1 + monthlyInterest, termInMonths) - 1)));
    }

    private static void printPaymentPlan(int totalNumPayments, int principal, double monthlyInterest) {
        printHeader("PAYMENT SCHEDULE");
        System.out.println("Payment plan:");

        short numPaymentsMade = 0;

        while (numPaymentsMade < totalNumPayments) {
            numPaymentsMade++;
            double remainingBalance = calculateRemainingBalance(principal, monthlyInterest, totalNumPayments, numPaymentsMade);
            printCurrency(remainingBalance);
        }
    }

    private static void printMonthlyRepayment(double monthlyRepayment) {
        printHeader("MORTGAGE");

        System.out.print("Monthly Payment: ");

        printCurrency(monthlyRepayment);
    }

    private static void printHeader(String message) {
        System.out.println("\n" + message);

        for (int i = 0; i < message.length(); i++)
            System.out.print("-");

        System.out.println();
    }

    private static void printCurrency(double amount) {
        System.out.println(NumberFormat.getCurrencyInstance().format(amount));
    }

    private static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);

        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();

            if (value >= min && value <= max)
                break;

            System.out.println("Please enter a value between " + min + " and " + max);
        }

        return value;
    }
}