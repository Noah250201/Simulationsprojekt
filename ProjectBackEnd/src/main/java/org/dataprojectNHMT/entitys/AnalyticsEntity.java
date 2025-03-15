package org.dataprojectNHMT.entitys;

public class AnalyticsEntity {
    //PrimaryKey
    private int analyticsID;

    private String month;
    private int searches;

    //ForeignKeys
    private int gameID;
    private int publisherID;

    public int getAnalyticsID() {
        return analyticsID;
    }

    public void setAnalyticsID(int analyticsID) {
        this.analyticsID = analyticsID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSearches() {
        return searches;
    }

    public void setSearches(int searches) {
        this.searches = searches;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }
}
