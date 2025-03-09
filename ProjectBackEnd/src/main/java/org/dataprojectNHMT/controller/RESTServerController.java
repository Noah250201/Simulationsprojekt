package org.dataprojectNHMT.controller;

import io.javalin.Javalin;
import org.dataprojectNHMT.services.GoogleTrendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RESTServerController {

    private static final Logger log = LoggerFactory.getLogger(RESTServerController.class);
    
    private final Javalin server;

    public RESTServerController(int port) {
        server = Javalin.create(
                config -> config.bundledPlugins.enableCors(
                        cors -> cors.addRule(
                                rule -> rule.allowHost("http://localhost:4200"))));

        server.start(port);
        log.info("Started Back-End Server on port {}", port);

        setupRestEndpoints();
        log.info("Front-End Server REST-Endpoints have been setup.");
    }
    
    private void setupRestEndpoints() {
        server.get("/", endpoint -> endpoint.result("No Whitelable Error, because Hello World"));
        
        GoogleTrendService googleTrendService = new GoogleTrendService();
        server.get("/trendData", endpoint -> endpoint.result(googleTrendService.getTrendsData()));
    }
}
