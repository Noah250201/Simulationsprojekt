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

    private final int port;

    private final Server server;

    public HTMLServerController(int port) {
        this.port = port;

        this.server = new Server(port);
        log.debug("Started Front-End Server on port {}", port);

        setupServer();
        startServer();
        openBrowser();
    }

    private void setupServer() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        String resourceBase = "../Datenprojekt";
        context.setResourceBase(resourceBase);
        context.addServlet(DefaultServlet.class, "/");
    }

    private void startServer() {
        try {
            server.start();
            log.info("Front-End Server started at http://localhost:{}", port);
        }  catch (Exception e) {
            log.error("Front-End Server failed to start",e);
        }
    }

    private void openBrowser() {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(URI.create("http://localhost:" + port));
            log.info("Browser opened at address http://localhost:{}", port);

        } catch (IOException e) {
            log.error("Failed to open browser at http://localhost:{}", port, e);
        }
    }
}
