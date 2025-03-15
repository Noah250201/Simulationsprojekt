package org.dataprojectNHMT.dtos.out;

import java.time.LocalDate;

public class DiagramThreeDTO implements DiagramDTO {
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
