package org.dataprojectNHMT.dtos;

import java.time.LocalDate;

public class DiagramOneDTO implements diagramDTO {
    double stockprice;
    LocalDate date;
    long googleViewCount;

    public String toJson() {
        return "{" +
                "\"stockprice\": \"" + stockprice + "\"" +
                "\"date\": \"" + date + "\"" +
                "\"googleViewCount\": \"" + googleViewCount + "\"" +
                "}";
    }
}
