package codelab.springboot.service.price.bitfinex;

import codelab.springboot.model.CurrencyPair;

/**
 * Bitfinex URL utility
 */
class BitfinexApiURL {
    private final static String API_BASE_URL = "https://api.bitfinex.com/v1/pubticker/";

    /**
     * Creates a price API URL for a given currency pair.
     */
    static String price(CurrencyPair currencyPair) {
        return API_BASE_URL + currencyPair.symbol();
    }
}
