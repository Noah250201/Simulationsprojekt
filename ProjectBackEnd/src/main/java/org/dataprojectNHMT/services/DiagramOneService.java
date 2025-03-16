package org.dataprojectNHMT.services;

import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.in.InputOneDTO;

public class DiagramOneService {

    private final DatabaseController db;

    public DiagramOneService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputOneDTO input) {
        return "";
    }
}
