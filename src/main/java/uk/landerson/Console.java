package uk.landerson;

import java.util.Scanner;

public class Console {
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
}
