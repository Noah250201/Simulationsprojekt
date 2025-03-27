package org.dataprojectNHMT.controller;

import io.javalin.Javalin;
import org.dataprojectNHMT.dtos.InputDTO;
import org.dataprojectNHMT.services.AveragePlayersStockPriceService;
import org.dataprojectNHMT.services.PublisherStockAndViewsService;
import org.dataprojectNHMT.services.PricingGamesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RESTServerController {

    private static final Logger log = LoggerFactory.getLogger(RESTServerController.class);
    
    private final Javalin server;
    private final DatabaseController db;

    public RESTServerController(int port, DatabaseController db) {
        this.db = db;
        this.server = setupServer(port);
        log.info("Started Back-End Server on port {}", port);

        setupRestEndpoints();
        log.info("Front-End Server REST-Endpoints have been setup.");
    }

    private Javalin setupServer(int port) {
        Javalin app = Javalin.create(
                config -> config.bundledPlugins.enableCors(
                        cors -> cors.addRule(
                                rule -> rule.allowHost("http://localhost:4200"))));

        app.start(port);
        return app;
    }
    
    private void setupRestEndpoints() {
        //Endpoint to stop Back-End
        server.delete("/stop", context -> context.result("stopping..."));
        server.after("/stop", context -> System.exit(0));

        //Endpoint for Diagram One
        AveragePlayersStockPriceService averagePlayersStockPriceService = new AveragePlayersStockPriceService(db);
        server.post("/averagePlayersStockPrice",
                context -> context.result(
                        averagePlayersStockPriceService.getDiagram(
                                context.bodyAsClass(InputDTO.class))));

        //Endpoint for Diagram Two
        PricingGamesService pricingGamesService = new PricingGamesService(db);
        server.post("/pricingGames",
                context -> context.result(
                        pricingGamesService.getDiagram(
                                context.bodyAsClass(InputDTO.class))));

        //Endpoint for Diagram Three
        PublisherStockAndViewsService publisherStockAndViewsService = new PublisherStockAndViewsService(db);
        server.post("/publisherStockAndViews",
                context -> context.result(
                        publisherStockAndViewsService.getDiagram(
                                context.bodyAsClass(InputDTO.class))));
    }
}
