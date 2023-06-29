/*
 * Mortgage Calculator
 *
 * Calculate the monthly repayment for a mortgage given the loan principal, annual interest and loan term in years.
 */
package uk.landerson;

import uk.landerson.calculation.MortgageCalculator;
import uk.landerson.io.Console;
import uk.landerson.reporting.MortgageReport;

public class Main {

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) Console.readNumber("Annual interest rate (%): ", 0, 30);
        byte numYears = (byte) Console.readNumber("Loan terms in years: ", 1, 30);

        MortgageCalculator calculator = new MortgageCalculator(principal, annualInterest, numYears);
        MortgageReport report = new MortgageReport(calculator);
        report.printMonthlyRepayment();
        report.printPaymentPlan();
    }

}