package org.dataprojectNHMT;

import org.dataprojectNHMT.controller.HTMLServerController;
import org.dataprojectNHMT.controller.RESTServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        RESTServerController rest = new RESTServerController(8080);
        log.debug("Finished creating RESTServerController.");
        HTMLServerController html = new HTMLServerController(4200);
        log.debug("Finished creating HTMLServerController.");
    }
}