package org.dataprojectNHMT.dtos;

import java.time.LocalDate;

public class DiagramThreeDTO implements diagramDTO {
    double stockprice;
    LocalDate date;
    String game;
    int averagePlayers;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockprice + "\"" +
                "\"date\": \"" + date + "\"" +
                "\"game\": \"" + game + "\"" +
                "\"averagePlayers\": \"" + averagePlayers + "\"" +
                "}";
    }
}
