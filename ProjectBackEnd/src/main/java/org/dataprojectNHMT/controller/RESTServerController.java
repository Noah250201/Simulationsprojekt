package org.dataprojectNHMT.controller;

import io.javalin.Javalin;
import org.dataprojectNHMT.dtos.in.InputOneDTO;
import org.dataprojectNHMT.dtos.in.InputThreeDTO;
import org.dataprojectNHMT.dtos.in.InputTwoDTO;
import org.dataprojectNHMT.services.DiagramOneService;
import org.dataprojectNHMT.services.DiagramThreeService;
import org.dataprojectNHMT.services.DiagramTwoService;
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
        DiagramOneService serviceOne = new DiagramOneService(db);
        server.post("/diagramOne",
                context -> context.result(
                        serviceOne.getDiagram(
                                context.bodyAsClass(InputOneDTO.class))));

        //Endpoint for Diagram Two
        DiagramTwoService serviceTwo = new DiagramTwoService(db);
        server.post("/diagramTwo",
                context -> context.result(
                        serviceTwo.getDiagram(
                                context.bodyAsClass(InputTwoDTO.class))));

        //Endpoint for Diagram Three
        DiagramThreeService serviceThree = new DiagramThreeService(db);
        server.post("diagramThree",
                context -> context.result(
                        serviceThree.getDiagram(
                                context.bodyAsClass(InputThreeDTO.class))));
    }
}
