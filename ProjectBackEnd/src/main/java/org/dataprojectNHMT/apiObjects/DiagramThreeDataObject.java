package org.dataprojectNHMT.apiObjects;

import java.time.LocalDate;

public class DiagramThreeDataObject implements DataObject{
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
