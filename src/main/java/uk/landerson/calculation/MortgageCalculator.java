package uk.landerson.calculation;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    public final static byte PERCENT = 100;
    private final int principal;
    private final byte numYears;
    private final double annualInterest;

    public MortgageCalculator(int principal, double annualInterest, byte numYears) {
        this.principal = principal;
        this.numYears = numYears;
        this.annualInterest = annualInterest / PERCENT;
    }

    public int calculateTotalNumPayments() {
        return MONTHS_IN_YEAR * numYears;
    }

    public double calculateMonthlyInterest() {
        return annualInterest / MONTHS_IN_YEAR;
    }

    public double calculateRemainingBalance(double monthlyInterest, int totalNumPayments, int numPaymentsMade) {
        return (principal *
                (Math.pow((1 + monthlyInterest), totalNumPayments) - Math.pow((1 + monthlyInterest), numPaymentsMade)))
                / ((Math.pow((1 + monthlyInterest), totalNumPayments)) - 1);
    }

    public double calculateMonthlyPayment() {
        int termInMonths = calculateTotalNumPayments();

        if (annualInterest == 0.0) {
            return (double) principal / termInMonths;
        }

        double monthlyInterest = calculateMonthlyInterest();

        return principal
                * (((monthlyInterest * Math.pow((1 + monthlyInterest), termInMonths))
                / (Math.pow(1 + monthlyInterest, termInMonths) - 1)));
    }
}
