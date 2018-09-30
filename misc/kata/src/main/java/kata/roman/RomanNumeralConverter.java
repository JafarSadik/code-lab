package kata.roman;

import java.util.regex.Pattern;

import static kata.roman.RomanSymbol.romanSymbol;

/**
 * Roman numeral converter
 */
public class RomanNumeralConverter {
    private final static Pattern validRomanNumeralRegex = Pattern.compile(
            "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    public int convert(String romanNumeral) {
        ensureValidRomanNumeral(romanNumeral);

        int sum = 0, previousValue = 0;
        romanNumeral = romanNumeral.toUpperCase();

        for (int index = romanNumeral.length() - 1; index >= 0; index--) {
            int value = romanSymbol(romanNumeral.charAt(index)).toDecimal();

            if (value >= previousValue) sum += value;
            else sum -= value;

            previousValue = value;
        }
        return sum;
    }

    private boolean isValidRomanNumeral(String romanNumeral) {
        romanNumeral = romanNumeral.toUpperCase();
        return romanNumeral.trim().length() > 0 &&
                validRomanNumeralRegex.matcher(romanNumeral).matches();
    }

    private void ensureValidRomanNumeral(String romanNumeral) {
        if (!isValidRomanNumeral(romanNumeral)) {
            throw new NumberFormatException("Not a valid roman numeral: " + romanNumeral);
        }
    }
}
