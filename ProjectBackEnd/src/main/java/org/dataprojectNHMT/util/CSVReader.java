package org.dataprojectNHMT.util;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.entitys.AnalyticsEntity;
import org.dataprojectNHMT.entitys.GameEntity;
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

    public List<StockEntity> readStockCsv(String uri) {
        List<StockEntity> entities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.next(); //skip titles

            while (scanner.hasNext()) {
                StockEntity entity = new StockEntity();
                String[] values = scanner.next().split(",");

                entity.setDate(LocalDate.parse(values[0]));
                int publisherID = db.getPublisherByName(values[1]).getPublisherID();
                entity.setPublisherID(publisherID);
                entity.setPrice(Double.parseDouble(values[2]));

                entities.add(entity);
            }
        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }

        return entities;
    }

    public List<GameEntity> readGameCsv(String uri) {
        List<GameEntity> entities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.next(); //skip titles

            while (scanner.hasNext()) {
                GameEntity entity = new GameEntity();
                String[] values = scanner.next().split(",");

                entity.setGameName(values[1]);
//                entity.set</

                entities.add(entity);
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }

        return entities;
    }

    public List<AnalyticsEntity> readAnalyticsCsv(String uri) {
        List<> entities = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(uri))) {
            scanner.next(); //skip titles

            while (scanner.hasNext()) {

                String[] values = scanner.next().split(",");


                entities.add(entity);
            }

        } catch (FileNotFoundException e) {
            log.error("Could not find File at location: {}", uri, e);
        }

        return entities;
    }
}
