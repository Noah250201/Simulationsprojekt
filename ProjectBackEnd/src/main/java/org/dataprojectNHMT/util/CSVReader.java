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
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static final Logger log = LoggerFactory.getLogger(CSVReader.class);

    private final DatabaseController db;

    public CSVReader(DatabaseController db) {
        this.db = db;
    }

    public void readCsvs(String stockCsv, String gameCsv, List<String> analyticsCsvs) {
        String filesLocation = "../csvs";

        stockCsv = filesLocation + stockCsv;
        gameCsv = filesLocation + gameCsv;
        analyticsCsvs = analyticsCsvs.stream()
                .map(fileName -> filesLocation + fileName).toList();

        saveStockCsvToDB(stockCsv);
        saveGameCsvToDB(gameCsv);
        analyticsCsvs.forEach(this::saveAnalyticsCsvAndPublisherToDB);
    }

    private void saveStockCsvToDB(String uri) {
        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.next(); //skip titles

            while (scanner.hasNext()) {
                StockEntity entity = new StockEntity();
                String[] values = scanner.next().split(",");

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
            scanner.next(); //skip titles

            while (scanner.hasNext()) {
                GameEntity entity = new GameEntity();
                String[] values = scanner.next().split(",");

                entity.setGameID(Integer.parseInt(values[0]));
                entity.setGameName(values[1]);
                entity.setOwner(values[3]);
                double price = Double.parseDouble(values[4].split(" USD")[0]);
                entity.setCurrentPrice(price);
                double discount = Double.parseDouble(values[5].split("%")[0]);
                entity.setInitialPrice(price - (price * (discount/100)));
                entity.setAveragedPlayersForever(Integer.parseInt(values[11]));
                entity.setAveragedPlayersLastTwoWeeks(Integer.parseInt(values[12]));

                this.db.insertGame(entity, db.getPublisherByName(values[2]));
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }
    }

    private void saveAnalyticsCsvAndPublisherToDB(String uri) {
        try (Scanner scanner = new Scanner(new File(uri))) {
            String[] publisherValues = scanner.next().split(",");
            PublisherEntity publisher = new PublisherEntity();

            publisher.setPublisherName(publisherValues[1]);
            db.insertPublisher(publisher);
            publisher = db.getPublisherByName(publisher.getPublisherName());

            while (scanner.hasNext()) {
                AnalyticsEntity entity = new AnalyticsEntity();
                String[] values = scanner.next().split(",");

                entity.setPublisherID(publisher.getPublisherID());
                entity.setSearches(Integer.parseInt(values[1]));
//                entity.setMonth();

                db.insertAnalytics(entity);
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }
    }
}
