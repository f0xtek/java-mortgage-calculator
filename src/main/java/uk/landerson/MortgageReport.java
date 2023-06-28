package uk.landerson;

import java.text.NumberFormat;

public class MortgageReport {

    private final MortgageCalculator calculator;

    MortgageReport(int principal, float annualInterest, byte numYears) {
        this.calculator = new MortgageCalculator(principal, annualInterest, numYears);
    }

    void printPaymentPlan() {
        printHeader("PAYMENT SCHEDULE");
        System.out.println("Payment plan:");

        short numPaymentsMade = 0;
        int totalPayments = calculator.calculateTotalNumPayments();
        double monthlyInterest = calculator.calculateMonthlyInterest();

        while (numPaymentsMade < totalPayments) {
            numPaymentsMade++;
            double remainingBalance = calculator.calculateRemainingBalance(monthlyInterest, totalPayments, numPaymentsMade);
            System.out.println(convertToCurrency(remainingBalance));
        }
    }

    void printMonthlyRepayment() {
        printHeader("MORTGAGE");
        System.out.printf("Monthly Payment: %s\n", convertToCurrency(calculator.calculateMonthlyPayment()));
    }

    private void printHeader(String message) {
        System.out.printf("\n%s\n%s\n", message, "-".repeat(message.length()));
    }

    private String convertToCurrency(double amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }
}
