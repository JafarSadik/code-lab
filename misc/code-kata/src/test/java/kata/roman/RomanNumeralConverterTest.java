package kata.roman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class RomanNumeralConverterTest {
    private RomanNumeralConverter romanNumeralConverter;

    @Before
    public void setup() {
        this.romanNumeralConverter = new RomanNumeralConverter();
    }

    @Test
    public void convertsRomanSymbols() {
        assertRomanNumeral(1, "I");
        assertRomanNumeral(5, "V");
        assertRomanNumeral(10, "X");
        assertRomanNumeral(50, "L");
        assertRomanNumeral(100, "C");
        assertRomanNumeral(500, "D");
        assertRomanNumeral(1000, "M");
    }

    @Test
    public void convertsSimpleRomanNumeralsWithMultipleSymbols() {
        assertRomanNumeral(6, "VI");
        assertRomanNumeral(11, "XI");
        assertRomanNumeral(20, "XX");
        assertRomanNumeral(30, "XXX");
        assertRomanNumeral(60, "LX");
        assertRomanNumeral(61, "LXI");
        assertRomanNumeral(80, "LXXX");
        assertRomanNumeral(70, "LXX");
        assertRomanNumeral(300, "CCC");
        assertRomanNumeral(721, "DCCXXI");
        assertRomanNumeral(1011, "MXI");
        assertRomanNumeral(3000, "MMM");
        assertRomanNumeral(3555, "MMMDLV");
    }

    @Test
    public void convertsRomanNumeralsWithRepeatingSymbols() {
        assertRomanNumeral(2, "II");
        assertRomanNumeral(3, "III");
        assertRomanNumeral(7, "VII");
        assertRomanNumeral(8, "VIII");
        assertRomanNumeral(12, "XII");
        assertRomanNumeral(13, "XIII");
        assertRomanNumeral(17, "XVII");
        assertRomanNumeral(18, "XVIII");
        assertRomanNumeral(20, "XX");
        assertRomanNumeral(30, "XXX");
        assertRomanNumeral(200, "CC");
        assertRomanNumeral(300, "CCC");
        assertRomanNumeral(800, "DCCC");
        assertRomanNumeral(2000, "MM");
        assertRomanNumeral(3000, "MMM");
        assertRomanNumeral(3333, "MMMCCCXXXIII");
    }

    @Test
    public void convertsMixedCaseRomanNumerals() {
        assertRomanNumeral(1, "i");
        assertRomanNumeral(5, "v");
        assertRomanNumeral(10, "x");
        assertRomanNumeral(50, "l");
        assertRomanNumeral(100, "c");
        assertRomanNumeral(500, "d");
        assertRomanNumeral(1000, "m");
        assertRomanNumeral(6, "vi");
        assertRomanNumeral(11, "xI");
        assertRomanNumeral(20, "Xx");
        assertRomanNumeral(30, "Xxx");
        assertRomanNumeral(60, "lx");
        assertRomanNumeral(61, "lxI");
        assertRomanNumeral(80, "lxxx");
        assertRomanNumeral(70, "lxx");
        assertRomanNumeral(300, "Ccc");
        assertRomanNumeral(721, "DccXXI");
        assertRomanNumeral(1011, "mXI");
        assertRomanNumeral(3000, "mmm");
        assertRomanNumeral(3555, "MMMdlv");
    }

    @Test
    public void convertsRomanNumeralsWithSubtraction() {
        assertRomanNumeral(4, "IV");
        assertRomanNumeral(9, "IX");
        assertRomanNumeral(19, "XIX");
        assertRomanNumeral(40, "XL");
        assertRomanNumeral(59, "LIX");
        assertRomanNumeral(90, "XC");
        assertRomanNumeral(190, "CXC");
        assertRomanNumeral(3999, "MMMCMXCIX");
    }

    @Test(expected = NumberFormatException.class)
    public void failForUnknownRomanSymbol() {
        String invalidRomanSymbol = "F";
        romanNumeralConverter.convert(invalidRomanSymbol);
    }

    @Test(expected = NumberFormatException.class)
    public void failWhenAtLeastOneSymbolUnknown() {
        String invalidRomanNumeral = "MMMDXF";
        romanNumeralConverter.convert(invalidRomanNumeral);
    }

    @Test(expected = NumberFormatException.class)
    public void failWhenRomanNumeralEmpty() {
        String emptyRomanNumeral = "";
        romanNumeralConverter.convert(emptyRomanNumeral);
    }

    @Test(expected = NumberFormatException.class)
    public void failWhenAtLeastOneWhiteSpace() {
        String romanNumeralWithWhitespace = " MMDI ";
        romanNumeralConverter.convert(romanNumeralWithWhitespace);
    }

    @Test
    public void failWhenInvalidSubtraction() {
        conversionShouldFailForAll(
                "IL", "LC", "DM",
                "VM", "XM", "IM"
        );
    }

    @Test
    public void failWhenTooManySybolsInRow() {
        conversionShouldFailForAll(
                "IIII", "IIIII",
                "XXXX", "XXXXX",
                "CCCC", "CCCCC",
                "MMMMM", "MMMMMM");
    }

    @Test
    public void failWhenInvalidSymbolsInRow() {
        conversionShouldFailForAll(
                "VV", "VVV",
                "LL", "LLL",
                "DD", "DDD");
    }

    @Test(expected = NullPointerException.class)
    public void failWhenNullRomanNumeral() {
        romanNumeralConverter.convert(null);
    }

    private void conversionShouldFailForAll(String... romanNumerals) {
        for (String romanNumeral : romanNumerals) {
            conversionShouldFail(romanNumeral);
        }
    }

    private void conversionShouldFail(String romanNumeral) {
        try {
            romanNumeralConverter.convert(romanNumeral);
            fail("Expected to fail conversion for " + romanNumeral);
        } catch (Exception e) {
            //ignore expected exception
        }
    }

    private void assertRomanNumeral(int expectedArabicNumeral, String romanNumeral) {
        assertEquals(expectedArabicNumeral, romanNumeralConverter.convert(romanNumeral));
    }
}

