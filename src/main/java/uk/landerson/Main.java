/*
 * Mortgage Calculator
 */
package uk.landerson;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        System.out.print("Principal: ");
        int loanPrincipal = scanner.nextInt();

        System.out.print("Annual interest rate (%): ");
        float monthlyInterestPercent = (scanner.nextFloat() / PERCENT) / MONTHS_IN_YEAR;

        System.out.print("Loan terms in years: ");
        int termInMonths = scanner.nextByte() * MONTHS_IN_YEAR;

        double monthlyRepayment = loanPrincipal
                * (((monthlyInterestPercent * Math.pow((1 + monthlyInterestPercent), termInMonths))
                / (Math.pow(1 + monthlyInterestPercent, termInMonths) - 1)));

        String formattedRepayment = NumberFormat.getCurrencyInstance().format(monthlyRepayment);
        System.out.println("Mortgage: " + formattedRepayment);
    }

}