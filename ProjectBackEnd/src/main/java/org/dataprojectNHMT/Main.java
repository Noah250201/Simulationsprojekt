package org.dataprojectNHMT;

import org.dataprojectNHMT.controller.HTMLServerController;
import org.dataprojectNHMT.controller.RESTServerController;
import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.util.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static final LocalDate LAST_DATE_ON_RECORD = LocalDate.parse("2025-03-16");

    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();
        CSVReader csvReader = new CSVReader(db);
        csvReader.readCsvs("dailyStockData","SteamSpyGames",
                List.of("trendsBandaiNamco","trendsEA","trendsNetEase","trendsSquareEnix","trendsUbisoft"));
        RESTServerController rest = new RESTServerController(8080, db);
        log.debug("Finished creating RESTServerController.");
        HTMLServerController html = new HTMLServerController(4200);
        log.debug("Finished creating HTMLServerController.");
    }
}