package codelab.springboot.service.price;

import codelab.springboot.exceptions.RestClientRuntimeException;
import codelab.springboot.model.CurrencyPair;
import codelab.springboot.model.Price;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Responsible for fetching currency pairs and their prices.
 */
public interface PriceResolver {
    /**
     * Gets the latest price for the provided currency pair
     *
     * @param currencyPair mandatory currency pair
     * @return price of the provided currency pair
     * @throws RestClientException thrown in case of I/O exception, API invocation limits or invalid currency pairs
     */
    Price getLatestPrice(CurrencyPair currencyPair) throws RestClientRuntimeException;

    /**
     * Returns all supported currency pairs
     *
     * @throws RestClientException thrown in case of I/O exception
     */
    List<CurrencyPair> getSupportedCurrencyPairs() throws RestClientRuntimeException;
}
