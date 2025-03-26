package org.dataprojectNHMT.entitys;

public class GameEntity {
    //PrimaryKey
    private int gameID;

    private int averagedPlayersForever;
    private int averagedPlayersLastTwoWeeks;
    private String gameName;
    private double initialPrice;
    private double currentPrice;

    //Foreign Key
    private int publisherID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getAveragedPlayersForever() {
        return averagedPlayersForever;
    }

    public void setAveragedPlayersForever(int averagedPlayersForever) {
        this.averagedPlayersForever = averagedPlayersForever;
    }

    public int getAveragedPlayersLastTwoWeeks() {
        return averagedPlayersLastTwoWeeks;
    }

    public void setAveragedPlayersLastTwoWeeks(int averagedPlayersLastTwoWeeks) {
        this.averagedPlayersLastTwoWeeks = averagedPlayersLastTwoWeeks;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }
}
