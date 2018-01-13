package codelab.springboot.model;

/**
 * Currency pair and associated symbol.
 */
public class CurrencyPair {
    private final String first;
    private final String second;

    public CurrencyPair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String symbol() {
        return first + second;
    }

    @Override
    public String toString() {
        return "CurrencyPair{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
