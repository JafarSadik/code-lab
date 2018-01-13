package codelab.springboot.scheduled;

import codelab.springboot.model.CurrencyPair;
import codelab.springboot.model.Price;
import codelab.springboot.service.price.PriceResolver;
import codelab.springboot.service.time.TimeService;
import codelab.springboot.util.builders.PriceBuilder;
import codelab.springboot.util.console.GenericConsole;
import codelab.springboot.util.console.RecordingPrintStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static codelab.springboot.model.CurrencyPairs.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PriceTickerTest {
    @Mock
    PriceResolver priceResolverMock;

    @Mock
    TimeService timeServiceMock;

    RecordingPrintStream consoleOutput;
    PriceTicker priceTicker;

    @Before
    public void setup() {
        priceTicker = new PriceTicker(priceResolverMock, timeServiceMock);
        consoleOutput = RecordingPrintStream.record();
        priceTicker.setConsole(new GenericConsole(consoleOutput));
    }

    @Test
    public void emptyOutputWhenTickerNotUpdated() {
        assertThat(consoleOutput.getLines()).isEmpty();
    }

    @Test
    public void priceDisplayedAfterTickerUpdate() {
        // Given PriceResolver configured to return a single currency pair and its price
        float LAST_PRICE = 111, VOLUME = 15100;
        Mockito.when(priceResolverMock.getSupportedCurrencyPairs()).thenReturn(asList(BTCUSD));
        Mockito.when(priceResolverMock.getLatestPrice(BTCUSD)).thenReturn(
                new PriceBuilder()
                        .setLastPrice(LAST_PRICE)
                        .setVolume(VOLUME)
                        .build()
        );

        // and TimeService set with a known, predefined time
        Date CURRENT_TIME = new Date();
        Mockito.when(timeServiceMock.getTime()).thenReturn(CURRENT_TIME);

        // After invoking price update
        priceTicker.updateTicker();

        // We should expect 3 lines in output
        List<String> lines = consoleOutput.getLines();
        assertThat(lines).hasSize(3);

        // First line that indicates current time
        assertThat(lines.get(0))
                .startsWith("Time: ").contains(CURRENT_TIME.toString());

        // Second line is currency pair, price and volume
        assertThat(lines.get(1))
                .startsWith("btcusd")
                .contains("last price").contains(String.valueOf(LAST_PRICE))
                .contains("volume").contains(String.valueOf(VOLUME));

        // And the third line left empty
        assertThat(lines.get(2)).isEmpty();
    }

    @Test
    public void displayMultiplePricesAfterTickerUpdate() {
        // Given PriceResolver configured to return 3 currency pairs and their prices
        List<CurrencyPair> currencyPairs = asList(BTCUSD, BTCEUR, BTCGBP);
        Mockito.when(priceResolverMock.getSupportedCurrencyPairs()).thenReturn(currencyPairs);
        Price anyPrice = new PriceBuilder().setLastPrice(10.0f).setVolume(100).build();
        Mockito.when(priceResolverMock.getLatestPrice(BTCUSD)).thenReturn(anyPrice);
        Mockito.when(priceResolverMock.getLatestPrice(BTCEUR)).thenReturn(anyPrice);
        Mockito.when(priceResolverMock.getLatestPrice(BTCGBP)).thenReturn(anyPrice);

        // and TimeService set with a known, predefined time
        Mockito.when(timeServiceMock.getTime()).thenReturn(new Date());

        // After invoking price update
        priceTicker.updateTicker();

        // We expect 1 line for the time, 1 line for every currency pair and 1 empty line.
        assertThat(consoleOutput.getLines()).hasSize(5);
    }
}