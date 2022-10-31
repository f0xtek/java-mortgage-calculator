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

        int loanPrincipal;
        final int MIN_LOAN_PRINCIPAL = 1000;
        final int MAX_LOAN_PRINCIPAL = 1_000_000;
        final String PRINCIPAL_PROMPT = "Principal (£1k - £1M): ";

        do {
            System.out.print(PRINCIPAL_PROMPT);
            loanPrincipal = scanner.nextInt();
        } while (loanPrincipal < MIN_LOAN_PRINCIPAL || loanPrincipal > MAX_LOAN_PRINCIPAL);

        float yearlyInterest;
        final byte MIN_INTEREST_PERCENT = 0;
        final byte MAX_INTEREST_PERCENT = 30;
        final String INTEREST_PROMPT = "Annual interest rate (%) (0 - 30): ";

        do {
            System.out.print(INTEREST_PROMPT);
            yearlyInterest = scanner.nextFloat();
        } while (yearlyInterest < MIN_INTEREST_PERCENT || yearlyInterest > MAX_INTEREST_PERCENT);

        float yearlyInterestPercent = yearlyInterest / PERCENT;
        float monthlyInterestPercent = yearlyInterestPercent / MONTHS_IN_YEAR;

        byte termInYears;
        final byte MIN_TERM_YEARS = 1;
        final byte MAX_TERM_YEARS = 30;
        final String TERM_PROMPT = "Loan terms in years (1 - 30): ";

        do {
            System.out.print(TERM_PROMPT);
            termInYears = scanner.nextByte();
        } while (termInYears < MIN_TERM_YEARS || termInYears > MAX_TERM_YEARS);

        int termInMonths = termInYears * MONTHS_IN_YEAR;

        double monthlyRepayment = loanPrincipal
                * (((monthlyInterestPercent * Math.pow((1 + monthlyInterestPercent), termInMonths))
                / (Math.pow(1 + monthlyInterestPercent, termInMonths) - 1)));

        String formattedRepayment = NumberFormat.getCurrencyInstance().format(monthlyRepayment);
        System.out.println("Mortgage: " + formattedRepayment);
    }
}