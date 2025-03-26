package org.dataprojectNHMT.util;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static final Logger log = LoggerFactory.getLogger(CSVReader.class);

    private final DatabaseController db;

    public CSVReader(DatabaseController db) {
        this.db = db;
    }

    public void readCsvs(String stockCsv, String gameCsv, List<String> analyticsCsvs) {
        String filesLocation = "../csvs/";

        stockCsv = filesLocation + stockCsv;
        gameCsv = filesLocation + gameCsv;
        analyticsCsvs = analyticsCsvs.stream()
                .map(fileName -> filesLocation + fileName).toList();

        analyticsCsvs.forEach(this::saveAnalyticsCsvAndPublisherToDB);
        saveStockCsvToDB(stockCsv);
        saveGameCsvToDB(gameCsv);
    }

    private void saveAnalyticsCsvAndPublisherToDB(String uri) {
        try (Scanner scanner = new Scanner(new File(uri))) {
            String[] publisherValues = scanner.nextLine().split(";");
            PublisherEntity publisher = new PublisherEntity();

            publisher.setPublisherName(publisherValues[1]);
            db.insertPublisher(publisher);
            publisher = db.getPublisherByName(publisher.getPublisherName());

            while (scanner.hasNextLine()) {
                AnalyticsEntity entity = new AnalyticsEntity();
                String[] values = scanner.nextLine().split(";");

                entity.setDate(LocalDate.parse(values[0]));
                entity.setPublisherID(publisher.getPublisherID());
                entity.setSearches(Integer.parseInt(values[1]));

                db.insertAnalytics(entity);
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }
    }

    private void saveStockCsvToDB(String uri) {
        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.nextLine(); //skip titles

            while (scanner.hasNextLine()) {
                StockEntity entity = new StockEntity();
                String[] values = scanner.nextLine().split(",");

                entity.setDate(LocalDate.parse(values[0]));
                int publisherID = db.getPublisherByName(values[1]).getPublisherID();
                entity.setPublisherID(publisherID);
                entity.setPrice(Double.parseDouble(values[2]));

                db.insertStock(entity);
            }
        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }
    }

    private void saveGameCsvToDB(String uri) {
        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.nextLine(); //skip titles
            List<Integer> gameIDs = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(";");
                int gameID = Integer.parseInt(values[0]);

                if (!gameIDs.contains(gameID)) {
                    GameEntity entity = new GameEntity();
                    gameIDs.add(gameID);

                    entity.setGameID(gameID);
                    entity.setGameName(values[1]);
                    int publisherID = db.getPublisherByName(values[2]).getPublisherID();
                    entity.setPublisherID(publisherID);
                    double price = Double.parseDouble(values[4].split(" USD")[0].replace(',', '.'));
                    entity.setCurrentPrice(price);
                    double prozentSatz = 100 - Double.parseDouble(values[5].split("%")[0]);
                    entity.setInitialPrice((price / prozentSatz) * 100);
                    entity.setAveragedPlayersForever(Integer.parseInt(values[11]));
                    entity.setAveragedPlayersLastTwoWeeks(Integer.parseInt(values[12]));

                    this.db.insertGame(entity);
                }
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }
    }
}
