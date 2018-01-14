package codelab.springboot.util.builders;

import codelab.springboot.model.Price;

/**
 * A builder for {@link Price} class
 */
public class PriceBuilder {
    private Price price = new Price();

    public PriceBuilder mid(float mid) {
        price.setMid(mid);
        return this;
    }

    public PriceBuilder bid(float bid) {
        price.setBid(bid);
        return this;
    }

    public PriceBuilder ask(float ask) {
        price.setAsk(ask);
        return this;
    }

    public PriceBuilder lastPrice(float lastPrice) {
        price.setLastPrice(lastPrice);
        return this;
    }

    public PriceBuilder low(float low) {
        price.setLow(low);
        return this;
    }

    public PriceBuilder high(float high) {
        price.setHigh(high);
        return this;
    }

    public PriceBuilder volume(float volume) {
        price.setVolume(volume);
        return this;
    }

    public PriceBuilder timestamp(float timestamp) {
        price.setTimestamp(timestamp);
        return this;
    }

    public Price build() {
        return price;
    }
}
