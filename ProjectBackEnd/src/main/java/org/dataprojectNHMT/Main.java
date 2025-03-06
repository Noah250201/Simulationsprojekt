package org.dataprojectNHMT;

import io.javalin.Javalin;
import org.dataprojectNHMT.controller.GoogleTrendController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Javalin app = Javalin.create().get("/", ctx -> ctx.result("No Whitelable Error, because Hello World")).start(8080);
        log.info("Javalin has been initialized.");
        setup(app);
        startingJetty();
    }

    private static void setup(Javalin app){
        GoogleTrendController googleTrendController = new GoogleTrendController();
        app.get("/trendData", ctx -> ctx.result(googleTrendController.getTrendsData()));

        log.info("javalin REST-Endpoints have been setup.");
    }

    private static void startingJetty() throws Exception {
        int port = 4200;

        log.debug("Creating a Jetty server on port {}", port);
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        String resourceBase = "../Datenprojekt";
        context.setResourceBase(resourceBase);
        context.addServlet(DefaultServlet.class, "/");

        server.start();
        log.info("Server started at http://localhost:{}", port);

        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create("http://localhost:" + port));
        log.info("Browser opened at address http://localhost:{}",port);
    }
}