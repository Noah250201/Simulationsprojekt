package org.dataprojectNHMT.entitys;

import java.time.LocalDate;

public class CourseEntity {
    //PrimaryKey
    private int courseID;

    private double price;
    private LocalDate date;
//    private Object symbol; //???

    //ForeignKey
    private int publisherID;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

//    public Object getSymbol() {
//        return symbol;
//    }
//
//    public void setSymbol(Object symbol) {
//        this.symbol = symbol;
//    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }
}
