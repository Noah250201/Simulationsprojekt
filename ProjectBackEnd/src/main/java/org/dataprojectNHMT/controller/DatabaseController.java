package org.dataprojectNHMT.controller;

import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseController {
    private static final Logger log = LoggerFactory.getLogger(DatabaseController.class);

    private Connection connection;

    private final String selectPublisher="SELECT * From publisher WHERE publisherName =";
    private final String selectGame="SELECT * FROM game WHERE gameName=";
    private final String selectAnalytics="SELECT * FROM analytics WHERE date=";
    private final String selectStock ="SELECT * FROM stock WHERE date=";



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
                "stockID INTEGER PRIMARY KEY," +
                "publisherID INTEGER,"+
                "symbol VARCHAR(20) NOT NULL," +
                "date DATE NOT NULL," +
                "price DOUBLE," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID));"
        );
        statement.execute("Create Table publisher( " +
                "publisherID INTEGER PRIMARY KEY," +
                "publisherName VARCHAR(100) Not Null,"
              );
        statement.execute("Create Table publisher_games(" +
                "publisher_gamesID INTEGER PRIMARY KEY," +
                "publisherID INTEGER," +
                "gameID INTEGER," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID)," +
                "FOREIGN KEY (gameID) REFERENCES game(gameID));"
        );
        statement.execute("Create Table analytics(" +
                "analyticsID INTEGER PRIMARY KEY," +
                "month String NOT NULL," +
                "searches INTEGER," +
                "gameID INTEGER," +
                "publisherID INTEGER," +
                "FOREIGN KEY (gameID) REFERENCES game(gameID)," +
                "FOREIGN KEY (publisherID) REFERENCES publisher(publisherID));"
        );
        statement.execute("Create Table games(" +
                "gameID INTEGER PRIMARY KEY," +
                "gameName VARCHAR(100)," +
                "owner VARCHAR(100)," +
                "initialPrice DOUBLE," +
                "currentPrice DOUBLE," +
                "averagedPlayersForever INTEGER," +
                "avgPlayerLastTwoWeeks INTEGER ," +
                "supportedLanguage INTEGER,"+
                "scoreRank INTEGER);"
                );

        statement.close();
    }

    public PublisherEntity getPublisherByName(String publisherSearchName) {

      PublisherEntity publisherEntity = new PublisherEntity();
      String selectPublisherByName = selectPublisher + "'" + publisherSearchName + "'" ;

      try(
      PreparedStatement preparedStatement = connection.prepareStatement(selectPublisherByName);
      ResultSet resultSet = preparedStatement.executeQuery()
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
        String selectGameByName = selectGame + "'" + gameSearchName + "'";

        try(
        PreparedStatement preparedStatement = connection.prepareStatement(selectGameByName);
        ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                gameEntity.setGameID(resultSet.getInt("gameID"));
                gameEntity.setGameName(resultSet.getString("gameName"));
                gameEntity.setOwner(resultSet.getString("owner"));
                gameEntity.setAveragedPlayersForever(resultSet.getInt("averagedPlayersForever"));
                gameEntity.setAveragedPlayersLastTwoWeeks(resultSet.getInt("averagedPlayersLastTwoWeeks"));
                gameEntity.setCurrentPrice(resultSet.getDouble("currentPrice"));
                gameEntity.setInitialPrice(resultSet.getInt("initialPrice"));
                gameEntity.setScoreRank(resultSet.getLong("scoreRank"));
                gameEntity.setSupportedLanguage(resultSet.getInt("supportedLanguage"));

            }
        } catch (SQLException e) {
            log.error("Failed to get Game with name {}", gameSearchName,e);
        }
        return gameEntity;
    }

    public AnalyticsEntity getAnalyticsByDateAndPublisher(LocalDate analyticsSearchDate, PublisherEntity searchPublisher) {

        AnalyticsEntity analyticsEntity=new AnalyticsEntity();
        String selectAnalyticsByName = selectAnalytics + "'" + analyticsSearchDate + "' AND publisherID=" + searchPublisher.getPublisherID();

        try( PreparedStatement preparedStatement = connection.prepareStatement(selectAnalyticsByName);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if(resultSet.next()){
                analyticsEntity.setAnalyticsID( resultSet.getInt("analyticsID"));
                analyticsEntity.setMonth( resultSet.getString("month"));
                analyticsEntity.setSearches(resultSet.getInt("searches"));
                analyticsEntity.setGameID(resultSet.getInt("gameID"));
                analyticsEntity.setPublisherID(resultSet.getInt("publisherID"));
            }
        } catch (SQLException e){
        log.error("Failed to get Game with Publisher {}", searchPublisher,e);
    }

        return analyticsEntity;
    }

    public StockEntity getStockByDateAndPublisher(LocalDate stockSearchDate, PublisherEntity searchPublisher) {

        StockEntity stockEntity = new StockEntity();
        String selectStockByName = selectStock + "'" + stockSearchDate + "' AND publisherID=" + searchPublisher.getPublisherID();
        try (
            PreparedStatement preparedStatement = connection.prepareStatement(selectStockByName);
            ResultSet resultSet = preparedStatement.executeQuery()
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
