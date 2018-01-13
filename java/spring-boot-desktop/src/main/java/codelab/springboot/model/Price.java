package codelab.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Currency pair price at a given time.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private float mid;
    private float bid;
    private float ask;
    private float lastPrice;
    private float low;
    private float high;
    private float volume;
    private float timestamp;

    @JsonProperty("mid")
    public float getMid() {
        return mid;
    }

    public void setMid(float mid) {
        this.mid = mid;
    }

    @JsonProperty("bid")
    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    @JsonProperty("ask")
    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    @JsonProperty("last_price")
    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    @JsonProperty("low")
    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    @JsonProperty("high")
    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    @JsonProperty("volume")
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @JsonProperty("timestamp")
    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Price{" +
                "mid=" + mid +
                ", bid=" + bid +
                ", ask=" + ask +
                ", lastPrice=" + lastPrice +
                ", low=" + low +
                ", high=" + high +
                ", volume=" + volume +
                ", timestamp=" + timestamp +
                '}';
    }
}
