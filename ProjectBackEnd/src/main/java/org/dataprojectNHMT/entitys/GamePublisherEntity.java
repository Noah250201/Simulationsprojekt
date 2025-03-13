package org.dataprojectNHMT.entitys;

public class GamePublisherEntity {
    //ForeignKey / one PrimaryKey
    private int publisherID;
    private int gameID;

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
