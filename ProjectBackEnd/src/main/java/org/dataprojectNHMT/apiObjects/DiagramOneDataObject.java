package org.dataprojectNHMT.apiObjects;

import java.time.LocalDate;

public class DiagramOneDataObject implements DataObject{
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
