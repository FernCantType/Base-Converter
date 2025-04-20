import java.util.Locale;
import java.util.Scanner;

/**
 * A class that converts a number from one base to another, supporting bases up to 36.
 * Handles both numeric and letter digits (0-9, A-Z).
 * 
 * Example: BaseConverter(16, 2, "2F") → 2F in base 16 is 101111 in base 2.
 * 
 * @author Lorenzo Canali
 * @version 9/20/2024
 */
public class BaseConverter {
    private final String value1;
    private String value2;
    private final int base1;
    private final int base2;
    private long decimalValue1;

    public BaseConverter(int base1Type, int resultBaseType, String firstValue) {
        this.base1 = base1Type;
        this.base2 = resultBaseType;
        this.value1 = firstValue.toUpperCase(Locale.ROOT);
        this.value2 = "";
        this.decimalValue1 = 0;

        if (!isValid()) {
            System.out.println("Invalid input: value does not match base " + base1);
            return;
        }

        value1ToDecimal();
        decimalToBaseValue();
        System.out.println(this);
    }
    //Converts the value from the original base to decimal 
    private void value1ToDecimal() {
        for(int i = 0; i < value1.length(); i++) {
            char ch = value1.charAt(i);
            int digitValue = Character.isDigit(ch) ? ch - '0' : ch - 'A' + 10;
            decimalValue1 += digitValue * (long) Math.pow(base1, value1.length() - 1 - i);
        }
    }
    //Converts the decimal value to the desired output base 
    private void decimalToBaseValue() {
        long n = decimalValue1;
        if(n == 0) {
            value2 = "0";
            return;
        }

        while(n > 0) {
            int remainder = (int) (n % base2);
            value2 = (remainder >= 10 ? (char) (remainder + 55) : String.valueOf(remainder)) + value2;
            n /= base2;
        }
    }
    //Validates that the characters in the input string are valid for the base 
    private boolean isValid() {
        for(char ch : value1.toCharArray()) {
            int digitValue = Character.isDigit(ch) ? ch - '0' : ch - 'A' + 10;
            if(digitValue >= base1) return false;
        }
        return true;
    }
    //Returns a summary string of the conversion 
    @Override
    public String toString() {
        return value1 + " in base " + base1 + " is equal to " + value2 + 
               " in base " + base2 + ". They both equal " + decimalValue1 + " in decimal.";
    }

    /**
     * Main method (THIS WAS MADE 4/19/2025 TO ADD TO MY GITHUB COLLECTIONS)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The parameters of the Class Constructor are int base1Type, int resultBaseType, " + 
        "String firstValue");
        System.out.println("Please provide input in that order, ensuring input is valid (no error checker yet)");
        int base1Type = sc.nextInt();
        int resultBaseType = sc.nextInt();
        String firstValue = sc.next();

        @SuppressWarnings("unused")
        BaseConverter bc = new BaseConverter(base1Type, resultBaseType, firstValue);
        sc.close();
    }
}