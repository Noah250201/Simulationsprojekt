package org.dataprojectNHMT.dtos;

public class DiagramTwoDTO implements DiagramDTO {
    String game;
    int supportedLanguages;
    double currentPrice;
    double initialPrice;

    public String toJson() {
        return "{" +
                "\"game\": \"" + game + "\"" +
                "\"supported_languages\": \"" + supportedLanguages + "\"" +
                "\"current_Price\": \"" + currentPrice + "\"" +
                "\"initial_Price\": \"" + initialPrice + "\"" +
                "}";
    }
}
