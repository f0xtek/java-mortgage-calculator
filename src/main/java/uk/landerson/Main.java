/*
 * Mortgage Calculator
 */
package uk.landerson;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final byte PERCENT = 100;
        final int MIN_LOAN_PRINCIPAL = 1000;
        final int MAX_LOAN_PRINCIPAL = 1_000_000;

        int loanPrincipal;

        do {
            System.out.print("Principal (£1k - £1M): ");
            loanPrincipal = scanner.nextInt();
        } while (loanPrincipal < MIN_LOAN_PRINCIPAL || loanPrincipal > MAX_LOAN_PRINCIPAL);

        float yearlyInterest;
        final byte MIN_INTEREST_PERCENT = 0;
        final byte MAX_INTEREST_PERCENT = 30;

        do {
            System.out.print("Annual interest rate (%) (0 - 30): ");
            yearlyInterest = scanner.nextFloat();
        } while (yearlyInterest < MIN_INTEREST_PERCENT || yearlyInterest > MAX_INTEREST_PERCENT);

        float yearlyInterestPercent = yearlyInterest / PERCENT;

        byte termInYears;
        final byte MIN_TERM_YEARS = 1;
        final byte MAX_TERM_YEARS = 30;

        do {
            System.out.print("Loan terms in years (1 - 30): ");
            termInYears = scanner.nextByte();
        } while (termInYears < MIN_TERM_YEARS || termInYears > MAX_TERM_YEARS);

        double monthlyRepayment = caclulateMortgage(loanPrincipal, yearlyInterestPercent, termInYears);
        String formattedRepayment = NumberFormat.getCurrencyInstance().format(monthlyRepayment);
        System.out.println("Mortgage: " + formattedRepayment);
    }

    public static double caclulateMortgage(int principal, float yearlyInterest, int years) {

        final byte MONTHS_IN_YEAR = 12;

        float monthlyInterestPercent = yearlyInterest / MONTHS_IN_YEAR;
        int termInMonths = years * MONTHS_IN_YEAR;

        return principal
                * (((monthlyInterestPercent * Math.pow((1 + monthlyInterestPercent), termInMonths))
                / (Math.pow(1 + monthlyInterestPercent, termInMonths) - 1)));

    }
}