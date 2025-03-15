package org.dataprojectNHMT.dtos.in;

import org.dataprojectNHMT.util.Interval;

public class InputOneDTO {
    private String publisher;
    private String[] games;
    private Interval interval;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getGames() {
        return games;
    }

    public void setGames(String[] games) {
        this.games = games;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
