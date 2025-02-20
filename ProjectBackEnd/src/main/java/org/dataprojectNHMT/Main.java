package org.dataprojectNHMT;

import io.javalin.Javalin;
import org.dataprojectNHMT.controller.GoogleTrendController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create().get("/", ctx -> ctx.result("No Whitelable Error, because Hello World")).start(8080);
        log.info("Javalin has been initialized.");
        setup(app);
        initializeHtml();
    }

    private static void setup(Javalin app){
        GoogleTrendController googleTrendController = new GoogleTrendController();
        app.get("/trendData", ctx -> ctx.result(googleTrendController.getTrendsData()));

        log.info("javalin REST-Endpoints have been setup.");
    }

    private static void initializeHtml(){
        try {
            File htmlFile = new File("../Datenprojekt/index.html");

            if (!Desktop.isDesktopSupported()) {
                log.error("Desktop-Funktionalität wird nicht unterstützt.");
            } else if (!htmlFile.exists()) {
                log.error("File at this location not found: \"{}\"", htmlFile.getAbsolutePath());
            } else {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(htmlFile);
                log.info("HTML-File has been opened");
            }
        } catch (IOException e) {
            log.error("Desktop failed to open file.",e);
        }
    }
}