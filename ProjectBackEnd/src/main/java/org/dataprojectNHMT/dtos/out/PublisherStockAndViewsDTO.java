package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class PublisherStockAndViewsDTO implements DiagramDTO {
    private double stockPrice;
    private LocalDate date;
    private long googleViewCount;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockPrice + "\"," +
                "\"date\": \"" + date + "\"," +
                "\"googleViewCount\": \"" + googleViewCount + "\"" +
                "}";
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getGoogleViewCount() {
        return googleViewCount;
    }

    public void setGoogleViewCount(long googleViewCount) {
        this.googleViewCount = googleViewCount;
    }
}
