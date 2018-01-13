package codelab.springboot.service.price.bitfinex;

import codelab.springboot.model.CurrencyPairs;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitfinexApiURLTest {
    @Test
    public void verifyPriceEndpoint() {
        assertEquals("https://api.bitfinex.com/v1/pubticker/btceur", BitfinexApiURL.price(CurrencyPairs.BTCEUR));
        assertEquals("https://api.bitfinex.com/v1/pubticker/btcusd", BitfinexApiURL.price(CurrencyPairs.BTCUSD));
    }
}