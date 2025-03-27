package org.dataprojectNHMT.services;

import org.dataprojectNHMT.Main;
import org.dataprojectNHMT.controller.DatabaseController;
import org.dataprojectNHMT.dtos.InputDTO;
import org.dataprojectNHMT.dtos.out.DiagramDTO;
import org.dataprojectNHMT.dtos.out.AveragePlayersStockPriceDTO;
import org.dataprojectNHMT.entitys.GameEntity;
import org.dataprojectNHMT.entitys.PublisherEntity;
import org.dataprojectNHMT.entitys.StockEntity;
import org.dataprojectNHMT.util.JsonMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AveragePlayersStockPriceService {

    private final DatabaseController db;

    public AveragePlayersStockPriceService(DatabaseController db) {
        this.db = db;
    }

    public String getDiagram(InputDTO input) {
        List<String> values = input.getValues();
        String interval = values.get(1);

        String publisherName = values.get(0);
        PublisherEntity publisher = db.getPublisherByName(publisherName);

        values = values.stream()
                .filter(value -> !value.isEmpty()).toList();
        values = values.subList(2, values.size());
        
        List<GameEntity> gameList = values.stream()
                .map(db::getGameByName).toList();

        LocalDate lastDate = Main.LAST_DATE_ON_RECORD;
        List<DiagramDTO> dtoList = new ArrayList<>();

        for (GameEntity game : gameList) {
            AveragePlayersStockPriceDTO dto = new AveragePlayersStockPriceDTO();
            StockEntity stock = db.getStockByDateAndPublisher(lastDate,publisher);
            dto.setGame(game.getGameName());
            dto.setAveragePlayers(game.getAveragedPlayersLastTwoWeeks());
            dto.setDate(lastDate);
            dto.setStockPrice(stock.getPrice());
            dtoList.add(dto);

            lastDate = switch (interval.toLowerCase()) {
                case "wöchentlich" -> lastDate.minusWeeks(1);
                case "monatlich" -> lastDate.minusMonths(1);
                default -> lastDate.minusDays(1);
            };
        }

        return JsonMapper.mapDTOsToArray(dtoList);
    }
}
