/*
 * Mortgage Calculator
 */
package uk.landerson;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final byte PERCENT = 100;

        int loanPrincipal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float yearlyInterest = (float) readNumber("Annual interest rate (%): ", 0, 30) / PERCENT;
        byte termInYears = (byte) readNumber("Loan terms in years: ", 1, 30);

        double monthlyRepayment = caclulateMortgage(loanPrincipal, yearlyInterest, termInYears);

        String formattedRepayment = NumberFormat.getCurrencyInstance().format(monthlyRepayment);
        System.out.println("Mortgage: " + formattedRepayment);
    }

    public static double readNumber(String prompt, double min, double max) {
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

    public static double caclulateMortgage(int principal, double yearlyInterest, byte years) {

        final byte MONTHS_IN_YEAR = 12;
        int termInMonths = years * MONTHS_IN_YEAR;

        if (yearlyInterest == 0.0) {
            return (double) principal / termInMonths;
        } else {
            double monthlyInterest = yearlyInterest / MONTHS_IN_YEAR;
            return principal
                    * (((monthlyInterest * Math.pow((1 + monthlyInterest), termInMonths))
                    / (Math.pow(1 + monthlyInterest, termInMonths) - 1)));
        }
    }

}