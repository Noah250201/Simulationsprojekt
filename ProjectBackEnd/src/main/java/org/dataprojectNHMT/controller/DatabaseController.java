package org.dataprojectNHMT.controller;

import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        statement.execute("Create Table publisher( " +
                "publisherID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "publisherName VARCHAR(100) Not Null);"
        );
        statement.execute("Create Table games(" +
                "gameID INTEGER PRIMARY KEY," +
                "gameName VARCHAR(100)," +
                "initialPrice DOUBLE," +
                "currentPrice DOUBLE," +
                "averagedPlayersForever INTEGER," +
                "avgPlayerLastTwoWeeks INTEGER," +
                "publisherID INTEGER," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID));"
        );
        statement.execute("Create Table stock(" +
                "stockID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "publisherID INTEGER,"+
                "date DATE NOT NULL," +
                "price DOUBLE," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID));"
        );
        statement.execute("Create Table analytics(" +
                "analyticsID INTEGER AUTO_INCREMENT PRIMARY KEY," +
                "date DATE NOT NULL," +
                "searches INTEGER," +
                "publisherID INTEGER," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID));"
        );

        statement.close();
    }

    public void insertAnalytics(AnalyticsEntity entity){
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO analytics (date,searches,publisherID) VALUES(?,?,?);")) {
            statement.setDate(1, Date.valueOf(entity.getDate()));
            statement.setInt(2,entity.getSearches());
            statement.setInt(3,entity.getPublisherID());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Failed to create new AnalyticsEntity",e);
        }
    }

    public void insertGame(GameEntity game) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO games VALUES(?,?,?,?,?,?,?);")) {
            statement.setInt(1,game.getGameID());
            statement.setString(2, game.getGameName());
            statement.setDouble(3,game.getInitialPrice());
            statement.setDouble(4,game.getCurrentPrice());
            statement.setInt(5,game.getAveragedPlayersForever());
            statement.setInt(6,game.getAveragedPlayersLastTwoWeeks());
            statement.setInt(7,game.getPublisherID());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Failed to create new GameEntity",e);
        }
    }

    public void insertPublisher(PublisherEntity publisher) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO publisher (publisherName) VALUES(?);")) {
            statement.setString(1, publisher.getPublisherName());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Failed to create new PublisherEntity",e);
        }
    }

    public void insertStock(StockEntity entity) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO stock (price, date, publisherID) VALUES (?, ?, ?);")) {
            statement.setDouble(1, entity.getPrice());
            statement.setString(2, entity.getDate().toString());
            statement.setInt(3, entity.getPublisherID());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Failed to create new PublisherEntity",e);
        }
    }

    public PublisherEntity getPublisherByName(String publisherSearchName) {
      PublisherEntity publisherEntity = new PublisherEntity();

      try(
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
              "SELECT * From publisher WHERE publisherName = '" + publisherSearchName + "'")
      ) {

          if (resultSet.next()) {
              publisherEntity.setPublisherID( resultSet.getInt("publisherID"));
              publisherEntity.setPublisherName(resultSet.getString("publisherName"));
          }
      } catch (SQLException e) {
         log.error("Failed to get Publisher with name {}", publisherSearchName,e);
      }

        return publisherEntity;
    }

    public GameEntity getGameByName(String gameSearchName) {
        GameEntity gameEntity= new GameEntity();

        try(
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM games WHERE gameName= '" + gameSearchName + "'")
        ) {
            if (resultSet.next()) {
                gameEntity.setGameID(resultSet.getInt("gameID"));
                gameEntity.setGameName(resultSet.getString("gameName"));
                gameEntity.setAveragedPlayersForever(resultSet.getInt("averagedPlayersForever"));
                gameEntity.setAveragedPlayersLastTwoWeeks(resultSet.getInt("avgPlayerLastTwoWeeks"));
                gameEntity.setCurrentPrice(resultSet.getDouble("currentPrice"));
                gameEntity.setInitialPrice(resultSet.getInt("initialPrice"));
                gameEntity.setPublisherID(resultSet.getInt("publisherID"));

            }
        } catch (SQLException e) {
            log.error("Failed to get Game with name {}", gameSearchName,e);
        }
        return gameEntity;
    }

    public AnalyticsEntity getAnalyticsByDateAndPublisher(LocalDate analyticsSearchDate, PublisherEntity searchPublisher) {
        AnalyticsEntity entity = new AnalyticsEntity();
        List<AnalyticsEntity> analyticsList = new ArrayList<>();

        try( Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM analytics WHERE publisherID=" + searchPublisher.getPublisherID())
        ) {
            while (resultSet.next()){
                AnalyticsEntity analyticsEntity=new AnalyticsEntity();

                analyticsEntity.setAnalyticsID( resultSet.getInt("analyticsID"));
                analyticsEntity.setDate( LocalDate.parse(resultSet.getString("date")));
                analyticsEntity.setSearches(resultSet.getInt("searches"));
                analyticsEntity.setPublisherID(resultSet.getInt("publisherID"));

                analyticsList.add(analyticsEntity);
            }

            Optional<AnalyticsEntity> optional = analyticsList.stream().filter(
                    analyticsEntity -> analyticsEntity.getDate().getMonthValue() ==
                            analyticsSearchDate.getMonthValue()).findFirst();

            if (optional.isPresent()) {
                entity = optional.get();
            }

        } catch (SQLException e){
        log.error("Failed to get Game with Publisher {}", searchPublisher,e);
        }

        return entity;
    }

    public StockEntity getStockByDateAndPublisher(LocalDate stockSearchDate, PublisherEntity searchPublisher) {
        StockEntity stockEntity = new StockEntity();

        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM stock WHERE date='" + stockSearchDate +
                            "' AND publisherID=" + searchPublisher.getPublisherID())
        ) {
            if(resultSet.next()){
                stockEntity.setStockID(resultSet.getInt("stockID"));
                stockEntity.setDate(resultSet.getDate("date").toLocalDate());
                stockEntity.setPublisherID(resultSet.getInt("publisherID"));
                stockEntity.setPrice(resultSet.getDouble("price"));
        }
        } catch (SQLException e) {
            log.error("Failed to get Stock with Publisher {}", searchPublisher,e);
        }

        return stockEntity;
    }
}
