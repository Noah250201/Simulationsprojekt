package org.dataprojectNHMT.entitys;

public class GamePublisherEntity {
    //Primary Key
    private int gamePublisherID;

    //ForeignKey
    private int publisherID;
    private int gameID;

    public int getGamePublisherID() {
        return gamePublisherID;
    }

    public void setGamePublisherID(int gamePublisherID) {
        this.gamePublisherID = gamePublisherID;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
