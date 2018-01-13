package codelab.springboot.model;

/**
 * Constants commonly used currency pairs
 */
public class CurrencyPairs {
    // Bitcoin - US Dollar pair
    public final static CurrencyPair BTCUSD = new CurrencyPair("btc", "usd");

    // Bitcoin - EURO pair
    public final static CurrencyPair BTCEUR = new CurrencyPair("btc", "eur");

    // Bitcoin - GBP pair
    public final static CurrencyPair BTCGBP = new CurrencyPair("btc", "gbp");
}
