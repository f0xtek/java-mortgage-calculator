# Java Mortgage Calculator

Simple command-line mortgage calculator that outputs the monthly repayment amount to the console, formatted in the current system locale's currency, given the following information:

- Loan principal
- Annual interest rate (%)
- Loan term in years

## Building the project

The project uses Maven for the build system. To compile & run the project:

```bash
mvn clean package
java -cp target/MortgageCalculator-1.0-SNAPSHOT.jar uk.landerson.Main
```
