package codelab.springboot.util.builders;

import codelab.springboot.model.Price;

/**
 * A builder for {@link Price} class
 */
public class PriceBuilder {
    private Price price = new Price();

    public PriceBuilder setMid(float mid) {
        price.setMid(mid);
        return this;
    }

    public PriceBuilder setBid(float bid) {
        price.setBid(bid);
        return this;
    }

    public PriceBuilder setAsk(float ask) {
        price.setAsk(ask);
        return this;
    }

    public PriceBuilder setLastPrice(float lastPrice) {
        price.setLastPrice(lastPrice);
        return this;
    }

    public PriceBuilder setLow(float low) {
        price.setLow(low);
        return this;
    }

    public PriceBuilder setHigh(float high) {
        price.setHigh(high);
        return this;
    }

    public PriceBuilder setVolume(float volume) {
        price.setVolume(volume);
        return this;
    }

    public PriceBuilder setTimestamp(float timestamp) {
        price.setTimestamp(timestamp);
        return this;
    }

    public Price build() {
        return price;
    }
}
