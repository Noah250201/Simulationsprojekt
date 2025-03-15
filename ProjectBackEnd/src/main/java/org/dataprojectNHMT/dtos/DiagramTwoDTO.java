package org.dataprojectNHMT.dtos;

public class DiagramTwoDTO implements DiagramDTO {
    String game;
    int supportedLanguages;
    double currentPrice;
    double initialPrice;

    public String toJson() {
        return "{" +
                "\"game\": \"" + game + "\"" +
                "\"supportedLanguages\": \"" + supportedLanguages + "\"" +
                "\"currentPrice\": \"" + currentPrice + "\"" +
                "\"initialPrice\": \"" + initialPrice + "\"" +
                "}";
    }
}
