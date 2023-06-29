package uk.landerson.reporting;

import uk.landerson.calculation.MortgageCalculator;

import java.text.NumberFormat;

public class MortgageReport {

    private final MortgageCalculator calculator;
    private final NumberFormat currency;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getCurrencyInstance();
    }

    public void printPaymentPlan() {
        printHeader("PAYMENT SCHEDULE");
        System.out.println("Payment plan:");

        for(double balance : calculator.getRemainingBalances()) {
            System.out.println(currency.format(balance));
        }
    }

    public void printMonthlyRepayment() {
        printHeader("MORTGAGE");
        System.out.printf("Monthly Payment: %s\n", currency.format(calculator.calculateMonthlyPayment()));
    }

    private void printHeader(String message) {
        System.out.printf("\n%s\n%s\n", message, "-".repeat(message.length()));
    }
}
