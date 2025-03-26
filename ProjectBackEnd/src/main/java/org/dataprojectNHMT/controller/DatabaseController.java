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
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO analytics (date,searches,publisherID) VALUES(" +
                    "'" + entity.getDate().toString() + "'," +
                    entity.getSearches() + "," +
                    entity.getPublisherID() + ");");
        } catch (SQLException e) {
            log.error("Failed to create new AnalyticsEntity",e);
        }
    }

    public void insertGame(GameEntity game, PublisherEntity publisher) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO games VALUES(" +
                    game.getGameID() + "," +
                    "'" + game.getGameName() + "'," +
                    game.getInitialPrice() + "," +
                    game.getCurrentPrice() + "," +
                    game.getAveragedPlayersForever() + "," +
                    game.getAveragedPlayersLastTwoWeeks() + "," +
                    game.getPublisherID() + ");");
        } catch (SQLException e) {
            log.error("Failed to create new GameEntity",e);
        }
    }

    public void insertPublisher(PublisherEntity publisher) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO publisher (publisherName) VALUES('" +
                    publisher.getPublisherName() + "');");
        } catch (SQLException e) {
            log.error("Failed to create new PublisherEntity",e);
        }
    }

    public void insertStock(StockEntity entity) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO stock (price,date,publisherID) VALUES(" +
                    entity.getPrice() + "," +
                    "'" + entity.getDate().toString() + "'," +
                    entity.getPublisherID() + ");");
        } catch (SQLException e) {
            log.error("Failed to create new PublisherEntity",e);
        }
    }

    public PublisherEntity getPublisherByName(String publisherSearchName) {

      PublisherEntity publisherEntity = new PublisherEntity();
        String selectPublisherByName = "SELECT * From publisher WHERE publisherName = '" + publisherSearchName + "'" ;

      try(
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(selectPublisherByName)
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
        String selectGameByName = "SELECT * FROM games WHERE gameName= '" + gameSearchName + "'";

        try(
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectGameByName)
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
        String selectAnalyticsByName = "SELECT * FROM analytics WHERE publisherID=" + searchPublisher.getPublisherID();

        try( Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAnalyticsByName)
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
        String selectStockByName = "SELECT * FROM stock WHERE date='" + stockSearchDate +
                "' AND publisherID=" + searchPublisher.getPublisherID();
        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectStockByName)
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
