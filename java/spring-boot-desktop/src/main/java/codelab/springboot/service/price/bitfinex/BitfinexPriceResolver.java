package codelab.springboot.service.price.bitfinex;

import codelab.springboot.exceptions.RestClientRuntimeException;
import codelab.springboot.model.CurrencyPair;
import codelab.springboot.model.Price;
import codelab.springboot.service.price.PriceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static codelab.springboot.model.CurrencyPairs.BTCEUR;
import static codelab.springboot.model.CurrencyPairs.BTCUSD;

/**
 * Bitfinex specific implementation of {@link PriceResolver}. Makes use of a public Bitfenix REST API to access
 * currency pairs and prices.
 */
@Component
public class BitfinexPriceResolver implements PriceResolver {
    private final RestTemplate restTemplate;

    @Autowired
    public BitfinexPriceResolver(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Price getLatestPrice(CurrencyPair currencyPair) throws RestClientRuntimeException {
        try {
            return restTemplate.getForObject(BitfinexApiURL.price(currencyPair), Price.class);
        } catch (Exception e) {
            throw new RestClientRuntimeException("Failed to obtain price for " + currencyPair, e);
        }
    }

    @Override
    public List<CurrencyPair> getSupportedCurrencyPairs() throws RestClientRuntimeException {
        return Arrays.asList(BTCEUR, BTCUSD);
    }
}
