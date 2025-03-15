package org.dataprojectNHMT.dtos.in;

import org.dataprojectNHMT.util.Interval;

import java.util.List;

public class InputOneDTO {
    private String publisher;
    private List<String> games;
    private Interval interval;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
