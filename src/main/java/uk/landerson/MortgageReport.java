package uk.landerson;

import java.text.NumberFormat;

public class MortgageReport {
    public static void printPaymentPlan(int totalNumPayments, int principal, double monthlyInterest) {
        printHeader("PAYMENT SCHEDULE");
        System.out.println("Payment plan:");

        short numPaymentsMade = 0;

        while (numPaymentsMade < totalNumPayments) {
            numPaymentsMade++;
            double remainingBalance = Main.calculateRemainingBalance(principal, monthlyInterest, totalNumPayments, numPaymentsMade);
            printCurrency(remainingBalance);
        }
    }

    public static void printMonthlyRepayment(double monthlyRepayment) {
        printHeader("MORTGAGE");

        System.out.print("Monthly Payment: ");

        printCurrency(monthlyRepayment);
    }

    public static void printHeader(String message) {
        System.out.println("\n" + message);

        for (int i = 0; i < message.length(); i++)
            System.out.print("-");

        System.out.println();
    }

    public static void printCurrency(double amount) {
        System.out.println(NumberFormat.getCurrencyInstance().format(amount));
    }
}
