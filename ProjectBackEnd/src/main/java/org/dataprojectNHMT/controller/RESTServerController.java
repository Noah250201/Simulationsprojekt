package org.dataprojectNHMT.controller;

import io.javalin.Javalin;
import org.dataprojectNHMT.services.GoogleTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RESTServerController {

    private static final Logger log = LoggerFactory.getLogger(RESTServerController.class);
    
    private final Javalin server;

    public RESTServerController(int port, DatabaseController db) {
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
        server.get("/", context -> context.result("No Whitelable Error, because Hello World"));
        
        GoogleTrendService googleTrendService = new GoogleTrendService();
        server.get("/trendData",
                context -> context.result(googleTrendService.getTrendsData()));

        server.delete("/stop", context -> context.result("stopping..."));
        server.after("/stop", context -> System.exit(0));
    }
}
