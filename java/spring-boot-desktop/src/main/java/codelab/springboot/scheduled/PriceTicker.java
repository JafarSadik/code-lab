package codelab.springboot.scheduled;

import codelab.springboot.model.CurrencyPair;
import codelab.springboot.model.Price;
import codelab.springboot.service.price.PriceResolver;
import codelab.springboot.service.time.TimeService;
import codelab.springboot.util.console.Console;
import codelab.springboot.util.console.NullConsole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Prints currency pair price updates at a predefined time interval.
 */
@Component
public class PriceTicker {
    private final PriceResolver priceResolver;
    private final TimeService timeService;
    private Console console = new NullConsole();

    @Autowired
    public PriceTicker(PriceResolver priceResolver, TimeService timeService) {
        this.priceResolver = priceResolver;
        this.timeService = timeService;
    }

    /**
     * Sets {@link Console} to be used for price update notifications.
     * @param console not mandatory, when not provided a default {@link NullConsole} implementation is used
     */
    @Autowired(required = false)
    public void setConsole(Console console) {
        this.console = console;
    }

    /**
     * Fetches current price of all supported currency pairs using {@link PriceResolver} and
     * prints an update to the provided {@link Console}.
     */
    @Scheduled(fixedRate = 30000)
    public void updateTicker() {
        printSupportedCurrencyPairs(priceResolver.getSupportedCurrencyPairs());
    }

    private void printSupportedCurrencyPairs(List<CurrencyPair> supportedPairs) {
        console.println("Time: " + timeService.getTime());
        supportedPairs.forEach(this::printCurrencyPair);
        console.println();
    }

    private void printCurrencyPair(CurrencyPair currencyPair) {
        Price price = priceResolver.getLatestPrice(currencyPair);
        console.println(currencyPair.symbol() + ", last price: " + price.getLastPrice() + ", volume = " + price.getVolume());
    }
}
