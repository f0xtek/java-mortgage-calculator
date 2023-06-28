/*
 * Mortgage Calculator
 *
 * Calculate the monthly repayment for a mortgage given the loan principal, annual interest and loan term in years.
 */
package uk.landerson;

public class Main {

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) Console.readNumber("Annual interest rate (%): ", 0, 30);
        byte numYears = (byte) Console.readNumber("Loan terms in years: ", 1, 30);

        MortgageReport report = new MortgageReport(principal, annualInterest, numYears);
        report.printMonthlyRepayment();
        report.printPaymentPlan();
    }

}