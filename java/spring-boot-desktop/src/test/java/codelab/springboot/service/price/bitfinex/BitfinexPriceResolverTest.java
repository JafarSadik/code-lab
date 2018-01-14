package codelab.springboot.service.price.bitfinex;

import codelab.springboot.exceptions.RestClientRuntimeException;
import codelab.springboot.model.Price;
import codelab.springboot.util.builders.PriceBuilder;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static codelab.springboot.model.CurrencyPairs.BTCEUR;
import static codelab.springboot.model.CurrencyPairs.BTCUSD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class BitfinexPriceResolverTest {
    final RestTemplate restTemplate = new RestTemplate();
    final MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
    final BitfinexPriceResolver bitfinexPriceResolver = new BitfinexPriceResolver(restTemplate);

    @Test
    public void getLatestPriceTest() {
        // Given mock server configured to successfully return price details for price API call.
        String PRICE_JSON = ("{'mid':'100.4','bid':'97.0','ask':'110.', 'last_price':'100.0', 'low':'95.0', 'high':'105.0', " +
                "'volume':'150000','timestamp':'140000000'}").replace("'", "\"");
        mockServer.expect(requestTo(BitfinexApiURL.price(BTCUSD))).andRespond(withSuccess(PRICE_JSON, MediaType.APPLICATION_JSON));

        // When retrieving latest price for BTC-USD currency pair
        Price price = bitfinexPriceResolver.getLatestPrice(BTCUSD);

        // We expect it to match data returned from the server
        assertThat(price).isEqualToComparingFieldByField(new PriceBuilder().mid(100.4f).bid(97).ask(110).lastPrice(100)
                .low(95).high(105).volume(150000).timestamp(140000000).build()
        );
        mockServer.verify();
    }

    @Test(expected = RestClientRuntimeException.class)
    public void getLatestPriceShouldFailOnServerError() {
        mockServer.expect(anything()).andRespond(withServerError());
        bitfinexPriceResolver.getLatestPrice(BTCUSD);
    }

    @Test
    public void getSupportedCurrencyPairsTest() {
        assertThat(bitfinexPriceResolver.getSupportedCurrencyPairs())
                .containsExactlyInAnyOrder(BTCUSD, BTCEUR);
    }
}