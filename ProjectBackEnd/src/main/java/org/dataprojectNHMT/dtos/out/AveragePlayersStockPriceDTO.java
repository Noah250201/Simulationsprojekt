package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class AveragePlayersStockPriceDTO implements DiagramDTO {
    private double stockPrice;
    private LocalDate date;
    private String game;
    private int averagePlayers;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockPrice + "\"," +
                "\"date\": \"" + date + "\"," +
                "\"game\": \"" + game + "\"," +
                "\"averagePlayers\": \"" + averagePlayers + "\"" +
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getAveragePlayers() {
        return averagePlayers;
    }

    public void setAveragePlayers(int averagePlayers) {
        this.averagePlayers = averagePlayers;
    }
}
