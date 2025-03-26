package org.dataprojectNHMT.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class HTMLServerController {

    private static final Logger log = LoggerFactory.getLogger(HTMLServerController.class);

    private final Server server;

    public HTMLServerController(int port) {
        this.server = setupServer(port);
        log.debug("Started Front-End Server on port {}", port);

        startServer(port);

        openBrowser(port);
    }

    private Server setupServer(int port) {
        Server restServer =  new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        restServer.setHandler(context);

        String frontEndLocation = "../Simulationsprojekt/Datenprojekt";
        context.setResourceBase(frontEndLocation);
        context.addServlet(DefaultServlet.class, "/");

        return restServer;
    }

    private void startServer(int port) {
        try {
            server.start();
            log.info("Front-End Server started at http://localhost:{}", port);
        }  catch (Exception e) {
            log.error("Front-End Server failed to start",e);
        }
    }

    private void openBrowser(int port) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(URI.create("http://localhost:" + port));
            log.info("Browser opened at address http://localhost:{}", port);
        } catch (IOException e) {
            log.error("Failed to open browser at http://localhost:{}", port, e);
        }
    }
}
