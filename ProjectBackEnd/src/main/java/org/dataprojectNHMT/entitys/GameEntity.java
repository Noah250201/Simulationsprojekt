package org.dataprojectNHMT.entitys;

public class GameEntity {
    //PrimaryKey
    private int gameID;

    private long scoreRank;
    private int averagedPlayersForever;
    private String owner;
    private int averagedPlayersLastTwoWeeks;
    private String gameName;
    private double initialPrice;
    private int supportedLanguage;
    private double currentPrice;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public long getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(long scoreRank) {
        this.scoreRank = scoreRank;
    }

    public int getAveragedPlayersForever() {
        return averagedPlayersForever;
    }

    public void setAveragedPlayersForever(int averagedPlayersForever) {
        this.averagedPlayersForever = averagedPlayersForever;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public int getSupportedLanguage() {
        return supportedLanguage;
    }

    public void setSupportedLanguage(int supportedLanguage) {
        this.supportedLanguage = supportedLanguage;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
