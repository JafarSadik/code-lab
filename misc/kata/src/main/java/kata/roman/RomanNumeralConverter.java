package kata.roman;

import java.util.regex.Pattern;

public class RomanNumeralConverter {
    private final static Pattern validRomanNumeralRegex = Pattern.compile(
            "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    public int convert(String romanNumeral) {
        ensureValidRomanNumeral(romanNumeral);

        int sum = 0, previousValue = 0;
        romanNumeral = romanNumeral.toUpperCase();

        for (int index = romanNumeral.length() - 1; index >= 0; index--) {
            RomanSymbol romanSymbol = RomanSymbol.valueOf(romanNumeral, index);
            int value = romanSymbol.toArabNumeral();
            boolean subtraction = value < previousValue;
            sum += subtraction ? -value : value;
            previousValue = value;
        }
        return sum;
    }

    public boolean isValidRomanNumeral(String romanNumeral) {
        romanNumeral = romanNumeral.toUpperCase();
        return romanNumeral.trim().length() > 0 &&
                validRomanNumeralRegex.matcher(romanNumeral).matches();
    }

    private void ensureValidRomanNumeral(String romanNumeral) {
        if (!isValidRomanNumeral(romanNumeral)) {
            throw new NumberFormatException("Not a valid roman numeral: " + romanNumeral);
        }
    }

    private enum RomanSymbol {
        I(1),
        V(5),
        X(10),
        L(50),
        C(100),
        D(500),
        M(1000);

        RomanSymbol(int arabNumeral) {
            this.arabNumeral = arabNumeral;
        }

        public int toArabNumeral() {
            return arabNumeral;
        }

        public static RomanSymbol valueOf(String str, int index) {
            String symbol = str.substring(index, index + 1);
            return RomanSymbol.valueOf(symbol);
        }

        private int arabNumeral;
    }
}
