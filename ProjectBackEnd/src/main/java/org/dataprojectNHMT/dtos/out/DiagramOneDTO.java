package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class DiagramOneDTO implements DiagramDTO {
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
