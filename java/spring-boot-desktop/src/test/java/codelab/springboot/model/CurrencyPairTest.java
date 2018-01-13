package codelab.springboot.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CurrencyPairTest {
    @Test
    public void pairSymbolTest() {
        assertEquals("usdbtc", new CurrencyPair("usd", "btc").symbol());
        assertEquals("btcusd", new CurrencyPair("btc", "usd").symbol());
        assertEquals("btceur", new CurrencyPair("btc", "eur").symbol());
    }

    @Test
    public void toStringTest() {
        String string = new CurrencyPair("usd", "btc").toString();
        assertTrue(string.contains("usd"));
        assertTrue(string.contains("btc"));
    }
}