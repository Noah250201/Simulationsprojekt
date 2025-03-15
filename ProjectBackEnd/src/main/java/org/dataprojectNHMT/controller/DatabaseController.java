package org.dataprojectNHMT.controller;

import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.CourseEntity;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseController {
    private static final Logger log = LoggerFactory.getLogger(DatabaseController.class);

    private Connection connection;

    public DatabaseController() {
        String databaseUrl = "jdbc:h2:mem:datenprojekt";
        try {
            connection = DriverManager.getConnection(databaseUrl);
            setup();
        } catch (SQLException e) {
            log.error("Failed to connect to Database with Connection-String {}", databaseUrl, e);
        }
    }

    private void setup() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("Create Table stock(" +
                "stockID integer primary key," +
                "symbol varchar(20) NOT NULL," +
                "date DATE NOT NULL," +
                "price double);");
        statement.execute("Create Table publisher( " +
                "publisherID integer primary key," +
                "publisherName varchar(20) Not Null," +
                "courseID fk" +
                "analytics fk" +
                "publisher_games fk);");
        statement.execute("Create Table publisher_games(" +
                "publisher_gamesID integer pk," +
                "publisherID fk" +
                "gamesID fk);");
        statement.execute("Create Table analytics(" +
                "analyticsID integer primary key," +
                "month Date NOT NULL," +
                "searches integer," +
                "gameID" +
                "publisherID);");
        statement.execute("Create Table games(" +
                "gameID integer primary key," +
                "gameName varchar(20)," +
                "owners varchar(20)," +
                "initialPrice double," +
                "currentPrice double," +
                "avgPlayerForever integer," +
                "avgPlayerLast2Weeks integer ," +
                "scoreRank integer," +
                "publisher_games ID" +
                "analytics ID);");
    }

    public GameEntity getGame(String name) {
        //TODO
        return new GameEntity();
    }

    public PublisherEntity getPublisher(String name) {
        //TODO
        return new PublisherEntity();
    }

    public CourseEntity getCourse(LocalDate date, PublisherEntity publisher) {
        //TODO
        return new CourseEntity();
    }

    public AnalyticsEntity getAnalytics(LocalDate date, PublisherEntity publisher) {
        //TODO
        return new AnalyticsEntity();
    }

    public AnalyticsEntity getAnalytics(LocalDate date, GameEntity game) {
        //TODO
        return new AnalyticsEntity();
    }
}
