package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class DiagramThreeDTO implements DiagramDTO {
    private double stockprice;
    private LocalDate date;
    private long googleViewCount;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockprice + "\"" +
                "\"date\": \"" + date + "\"" +
                "\"googleViewCount\": \"" + googleViewCount + "\"" +
                "}";
    }

    public double getStockprice() {
        return stockprice;
    }

    public void setStockprice(double stockprice) {
        this.stockprice = stockprice;
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
