/*
 * Mortgage Calculator
 *
 * Calculate the monthly repayment for a mortgage given the loan principal, annual interest and loan term in years.
 */
package uk.landerson;

public class Main {
    private final static byte PERCENT = 100;
    private final static byte MONTHS_IN_YEAR = 12;

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float yearlyInterest = (float) Console.readNumber("Annual interest rate (%): ", 0, 30) / PERCENT;
        byte numYears = (byte) Console.readNumber("Loan terms in years: ", 1, 30);

        double monthlyRepayment = caclulateMortgage(principal, yearlyInterest, numYears);
        int totalNumPayments = calculateTotalNumPayments(numYears);
        double monthlyInterest = calculateMonthlyInterest(yearlyInterest);

        MortgageReport.printMonthlyRepayment(monthlyRepayment);
        MortgageReport.printPaymentPlan(totalNumPayments, principal, monthlyInterest);
    }

    private static int calculateTotalNumPayments(byte numYears) {
        return (MONTHS_IN_YEAR * numYears);
    }

    private static double calculateMonthlyInterest(double yearlyInterest) {
        return yearlyInterest / MONTHS_IN_YEAR;
    }

    static double calculateRemainingBalance(int principal, double monthlyInterest, int totalNumPayments, int numPaymentsMade) {
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

}