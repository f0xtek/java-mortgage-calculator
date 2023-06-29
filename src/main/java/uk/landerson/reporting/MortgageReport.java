package uk.landerson.reporting;

import uk.landerson.calculation.MortgageCalculator;

import java.text.NumberFormat;

public class MortgageReport {

    private final MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
    }

    public void printPaymentPlan() {
        printHeader("PAYMENT SCHEDULE");
        System.out.println("Payment plan:");

        for(double balance : calculator.getRemainingBalances()) {
            System.out.println(convertToCurrency(balance));
        }
    }

    public void printMonthlyRepayment() {
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
