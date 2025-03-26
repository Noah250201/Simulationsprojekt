package org.dataprojectNHMT.entitys;

import java.time.LocalDate;

public class AnalyticsEntity {
    //PrimaryKey
    private int analyticsID;

    private LocalDate date;
    private int searches;

    //ForeignKeys
    private int publisherID;

    public int getAnalyticsID() {
        return analyticsID;
    }

    public void setAnalyticsID(int analyticsID) {
        this.analyticsID = analyticsID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSearches() {
        return searches;
    }

    public void setSearches(int searches) {
        this.searches = searches;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }
}
