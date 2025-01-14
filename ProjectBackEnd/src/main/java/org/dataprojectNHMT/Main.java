package org.dataprojectNHMT;

import io.javalin.Javalin;
import org.dataprojectNHMT.controller.GoogleTrendController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().get("/", ctx -> ctx.result("No Whitelable Error, because Hello World")).start(8080);
        setup(app);
    }

    private static void setup(Javalin app){
        GoogleTrendController googleTrendController = new GoogleTrendController();
        app.get("/trendData", ctx -> ctx.result(googleTrendController.getTrendsData()));
    }
}