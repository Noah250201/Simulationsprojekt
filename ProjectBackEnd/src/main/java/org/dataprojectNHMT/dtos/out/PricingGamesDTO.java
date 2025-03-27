package org.dataprojectNHMT.dtos.out;

public class PricingGamesDTO implements DiagramDTO {
    private String game;
    private double currentPrice;
    private double initialPrice;

    public String toJson() {
        return "{" +
                "\"game\": \"" + game + "\"" +
                "\"currentPrice\": \"" + currentPrice + "\"" +
                "\"initialPrice\": \"" + initialPrice + "\"" +
                "}";
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }
}
