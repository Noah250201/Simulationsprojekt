package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class DiagramOneDTO implements DiagramDTO {
    private double stockprice;
    private LocalDate date;
    private String game;
    private int averagePlayers;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockprice + "\"" +
                "\"date\": \"" + date + "\"" +
                "\"game\": \"" + game + "\"" +
                "\"averagePlayers\": \"" + averagePlayers + "\"" +
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
